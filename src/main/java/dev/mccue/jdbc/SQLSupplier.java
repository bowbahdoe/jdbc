package dev.mccue.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLSupplier<V> {
    V get() throws SQLException;
}
