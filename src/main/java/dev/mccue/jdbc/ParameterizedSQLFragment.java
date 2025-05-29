package dev.mccue.jdbc;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;


/// Represents a fragment of SQL where arguments are yet to be filled in.
///
/// These arguments are provided by name, where names are discovered and replaced
/// in the SQL by the following regex: `:[\w-]+`
///
/// Example:
/// ```
/// ParameterizedSQLFragment selectDog
///      = ParameterizedSQLFragment.of("SELECT * FROM dog WHERE name = :name");
/// SQLFragment fragment = selectDog.apply(Map.of("name", "fido"));
/// ```
///
/// A `SQLFragment` can be directly produced if the arguments are provided inline,
/// in which case this class is just a pass-through to that behavior.
///
/// ```
/// SQLFragment fragment
///      = ParameterizedSQLFragment.of(
///         "SELECT * FROM dog WHERE name = :name",
///         Map.of("name", "fido")
///      );
/// ```
public final class ParameterizedSQLFragment
        implements Function<Map<String, ?>, SQLFragment> {
    private final String parameterizedSQL;

    private ParameterizedSQLFragment(String parameterizedSQL) {
        this.parameterizedSQL = Objects.requireNonNull(parameterizedSQL);
    }

    private static final Pattern REPLACE_PATTERN = Pattern.compile(":([\\w-]+)");

    public static ParameterizedSQLFragment of(String sql) {
        return new ParameterizedSQLFragment(sql);
    }

    public static SQLFragment of(
            String sql,
            Map<String, ?> params
    ) {
        var values = new ArrayList<>();
        var replacedSQL = REPLACE_PATTERN.matcher(sql).replaceAll(matchResult -> {
            var group = matchResult.group(1);
            if (!params.containsKey(group)) {
                throw new IllegalArgumentException("No value for :" + group);
            }
            values.add(params.get(group));
            return "?";
        });

        return SQLFragment.of(replacedSQL, values);
    }

    @Override
    public SQLFragment apply(Map<String, ?> parameters) {
        // TODO: Cache the sql after ?-replacement with a stable value
        return of(parameterizedSQL, parameters);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ParameterizedSQLFragment parameterizedSQLFragment &&
                parameterizedSQL.equals(parameterizedSQLFragment.parameterizedSQL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterizedSQL);
    }

    @Override
    public String toString() {
        return "ParameterizedSQLFragment[sql=" + parameterizedSQL + "]";
    }
}

