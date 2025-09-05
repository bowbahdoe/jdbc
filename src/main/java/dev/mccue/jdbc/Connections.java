package dev.mccue.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class Connections {
    private Connections() {}

    static Connection rawConnection(Connection connection) {
        try {
            if (connection.isWrapperFor(Connection.class)) {
                return connection.unwrap(Connection.class);
            }
            else {
                return connection;
            }
        } catch (Throwable _t) {
            return connection;
        }
    }

    static <V> V inTransaction(
            Connection connection,
            TransactionOptions options,
            SQLFunction<? super Connection, ? extends V> f
    ) throws SQLException {
        var isolation = options.isolation;
        var readOnly = options.readOnly;
        var rollbackOnly = options.rollbackOnly;
        var oldAutoCommit = connection.getAutoCommit();
        var oldIsolation = connection.getTransactionIsolation();
        var oldReadOnly = connection.isReadOnly();
        var restoreAutoCommit = true;

        if (isolation != null) {
            connection.setTransactionIsolation(isolation);
        }
        if (readOnly != null && readOnly) {
            connection.setReadOnly(true);
        }

        connection.setAutoCommit(false);

        try {
            var result = f.apply(connection);
            if (rollbackOnly) {
                restoreAutoCommit = false;
                connection.rollback();
                restoreAutoCommit = true;
            } else {
                connection.commit();
            }
            return result;
        }
        catch (Throwable t) {
            try {
                restoreAutoCommit = false;
                connection.rollback();
                restoreAutoCommit = true;
            } catch (Throwable rb) {
                // combine both exceptions
                throw new RollbackFailedException(
                        rb, t
                );
            }
            throw t;
        }
        finally {
            if (restoreAutoCommit) {
                try {
                    connection.setAutoCommit(oldAutoCommit);
                } catch (Exception ignored) {}
            }
            if (isolation != null) {
                try {
                    connection.setTransactionIsolation(oldIsolation);
                } catch (Exception ignored) {}
            }
            if (readOnly != null && readOnly) {
                try {
                    connection.setReadOnly(oldReadOnly);
                } catch (Exception ignored) {}
            }
        }
    }

    private enum NestedTransactions {
        ALLOW,
        IGNORE,
        PROHIBIT
    }

    private static final ThreadLocal<NestedTransactions> NESTED_TRANSACTIONS
            = ThreadLocal.withInitial(() -> NestedTransactions.PROHIBIT);

    static final ThreadLocal<Set<Connection>> ACTIVE_TRANSACTION
            = ThreadLocal.withInitial(Set::of);

    public static <T> T transact(
            Connection connection,
            TransactionOptions options,
            SQLFunction<? super Connection, ? extends T> bodyFunction
    ) throws SQLException {
        var raw = rawConnection(connection);
        var activeTx = ACTIVE_TRANSACTION.get();
        var nestedTransactions = NESTED_TRANSACTIONS.get();

        var runBody = (SQLSupplier<T>) () -> {
            Set<Connection> newActiveTx = new HashSet<>(activeTx);
            newActiveTx.add(connection);
            newActiveTx = Set.copyOf(newActiveTx);
            ACTIVE_TRANSACTION.set(newActiveTx);
            try {
                return inTransaction(connection, options, bodyFunction);
            } finally {
                ACTIVE_TRANSACTION.set(activeTx);
            }
        };
        if (
                nestedTransactions == NestedTransactions.IGNORE &&
                !activeTx.contains(raw)
        ) {
            return runBody.get();
        }
        else if (
                nestedTransactions == NestedTransactions.ALLOW ||
                !activeTx.contains(raw)
        ) {
            synchronized (connection) {
                return runBody.get();
            }
        }
        else if (
                nestedTransactions == NestedTransactions.IGNORE
        ) {
            return bodyFunction.apply(connection);
        }
        else /* nestedTransactions == NestedTransactions.PROHIBIT */ {
            throw new NestedTransactionException("Nested transactions are prohibited");
        }
    }

    public static void transact(
            Connection connection,
            TransactionOptions options,
            SQLConsumer<? super Connection> bodyFunction
    ) throws SQLException {
        transact(connection, options, (SQLFunction<? super Connection, ?>) (c) -> { bodyFunction.accept(c); return null; });
    }

    public static <T> T transact(
            Connection connection,
            TransactionOptions options,
            SQLSupplier<? extends T> bodyFunction
    ) throws SQLException {
        return transact(connection, options, (SQLFunction<? super Connection, T>) (c) -> bodyFunction.get() );
    }

    public static void transact(
            Connection connection,
            TransactionOptions options,
            SQLRunnable bodyFunction
    ) throws SQLException {
        transact(
                connection,
                options,
                (SQLConsumer<? super Connection>) (c) -> bodyFunction.run()
        );
    }

    public static <T> T transact(
            Connection connection,
            SQLFunction<? super Connection, ? extends T> bodyFunction
    ) throws SQLException {
        return transact(
                connection,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }
    public static void transact(
            Connection connection,
            SQLConsumer<? super Connection> bodyFunction
    ) throws SQLException {
        transact(
                connection,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }

    public static <T> T transact(
            Connection connection,
            SQLSupplier<? extends T> bodyFunction
    ) throws SQLException {
        return transact(
                connection,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }

    public static void transact(
            Connection connection,
            SQLRunnable bodyFunction
    ) throws SQLException {
        transact(
                connection,
                TransactionOptions.DEFAULT,
                bodyFunction
        );
    }
}
