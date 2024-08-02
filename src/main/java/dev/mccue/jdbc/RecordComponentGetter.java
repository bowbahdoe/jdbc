package dev.mccue.jdbc;

import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extracts and a value from the given {@link ResultSet}
 * for the {@link RecordComponent}.
 * @param <T> The type of value returned.
 */
@FunctionalInterface
interface RecordComponentGetter<T> {
    /**
     * Extracts and a value from the given {@link ResultSet}
     * for the {@link RecordComponent}.
     *
     * <p>
     *     Should not call any of the mutating methods on {@link ResultSet}.
     * </p>
     * @param rs The {@link ResultSet} to extract a value from.
     * @param recordComponent The record component to extract a value for.
     * @return The extracted value.
     * @throws SQLException If something goes wrong interacting with the {@link ResultSet}.
     */
    T getRecordComponent(
            ResultSet rs,
            RecordComponent recordComponent
    ) throws SQLException;
}
