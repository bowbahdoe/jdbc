package dev.mccue.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class DataSources {
    private DataSources() {}

    public static <T> T transact(
            DataSource dataSource,
            TransactionOptions options,
            SQLFunction<? super Connection, ? extends T> bodyFunction
    ) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var raw = Connections.rawConnection(connection);
            var activeTx = Connections.ACTIVE_TRANSACTION.get();
            Set<Connection> newActiveTx = new HashSet<>(activeTx);
            newActiveTx.add(raw);
            newActiveTx = Set.copyOf(newActiveTx);
            Connections.ACTIVE_TRANSACTION.set(newActiveTx);
            try {
                return Connections.inTransaction(connection, options, bodyFunction);
            } finally {
                Connections.ACTIVE_TRANSACTION.set(activeTx);
            }
        }
    }

    public static void transact(
            DataSource dataSource,
            TransactionOptions options,
            SQLConsumer<? super Connection> bodyFunction
    ) throws SQLException {
        transact(dataSource, options, (SQLFunction<? super Connection, ?>) (c) -> { bodyFunction.accept(c); return null; });
    }

    public static <T> T transact(
            DataSource dataSource,
            SQLFunction<? super Connection, ? extends T> bodyFunction
    ) throws SQLException {
        return transact(
                dataSource,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }
    public static void transact(
            DataSource dataSource,
            SQLConsumer<? super Connection> bodyFunction
    ) throws SQLException {
        transact(
                dataSource,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }

}
