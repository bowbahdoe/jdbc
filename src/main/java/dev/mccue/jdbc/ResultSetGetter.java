package dev.mccue.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gets a value from a result set.
 * @param <T> The type of value it will get.
 */
@FunctionalInterface
public interface ResultSetGetter<T> {
    /**
     * Extract information from a record component.
     * @param rs The result set to extract from.
     * @return The extracted data.
     */
    T get(ResultSet rs) throws SQLException;
}
