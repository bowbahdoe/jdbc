package dev.mccue.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a fragment of SQL. Stores both the text of the sql fragment
 * and the values for any placeholders.
 */
public final class SQLFragment {
    private final String sql;
    private final List<Object> parameters;

    private SQLFragment(String sql, List<?> parameters) {
        this.sql = sql;
        this.parameters = Collections.unmodifiableList(new ArrayList<>(parameters));
    }

    public static SQLFragment of(String sql, List<?> parameters) {
        return new SQLFragment(sql, parameters);
    }

    public static SQLFragment of(String sql, Object... parameters) {

      return of(sql, Arrays.asList(parameters));
    }

    public static SQLFragment of(String sql) {
        return new SQLFragment(sql, Collections.unmodifiableList(new ArrayList<>()));
    }

    public String sql() {
        return sql;
    }

    public List<Object> parameters() {
        return parameters;
    }

    /**
     * Returns a {@link SQLFragment} of comma-separated {@code ?}s
     * for each of the items in the parameters list.
     * @param parameters The list of parameters.
     * @return A {@link SQLFragment}.
     */
    public static SQLFragment ofPlaceholders(List<?> parameters) {
        var placeholders = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            placeholders.append("?");
            if (i != parameters.size() - 1) {
                placeholders.append(",");
            }
        }
        return SQLFragment.of(placeholders.toString(), parameters);
    }

    public SQLFragment concat(SQLFragment other) {
        var params = new ArrayList<>(parameters);
        params.addAll(other.parameters);
        return SQLFragment.of(sql + other.sql, params);
    }

    public static SQLFragment join(String separator, Collection<SQLFragment> fragments) {
        var params = new ArrayList<>();
        var fragmentStr = new StringBuilder();

        var iter = fragments.iterator();
        while (iter.hasNext()) {
            var fragment = iter.next();
            params.addAll(fragment.parameters);
            fragmentStr.append(fragment.sql);
            if (iter.hasNext()) {
                fragmentStr.append(separator);
            }
        }
        return SQLFragment.of(fragmentStr.toString(), params);
    }

    public PreparedStatement prepareStatement(Connection connection) throws SQLException {
        var stmt = connection.prepareStatement(sql);
        int i = 1;
        for (var param : parameters) {
            if (param instanceof SettableParameter settableParameter) {
                settableParameter.setParameter(stmt, i);
            }
            else {
                stmt.setObject(i, param);
            }
            i++;
        }
        return stmt;
    }

    @Override
    public String toString() {
        return "SQLFragment[sql=" + sql + ", parameters=" + parameters + "]";
    }
}
