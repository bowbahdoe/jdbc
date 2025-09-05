package dev.mccue.jdbc;

import java.sql.SQLException;

final class NestedTransactionException extends SQLException {
    NestedTransactionException(String message) {
        super(message);
    }
}
