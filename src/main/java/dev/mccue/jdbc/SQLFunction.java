package dev.mccue.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLFunction<T, R> {
    R apply(T value) throws SQLException;
}
