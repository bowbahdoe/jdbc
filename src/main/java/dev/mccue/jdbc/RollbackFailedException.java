package dev.mccue.jdbc;

import java.sql.SQLException;

/// Exception thrown if we fail to rollback a transaction.
final class RollbackFailedException extends SQLException {
    private final Throwable rollback;
    private final Throwable handling;

    RollbackFailedException(Throwable rollback, Throwable handling) {
        super(handling);
        this.rollback = rollback;
        this.handling = handling;
    }

    public Throwable rollback() {
        return rollback;
    }

    public Throwable handling() {
        return handling;
    }

    @Override
    public String getMessage() {
        return "Rollback failed handling \"" + handling.getMessage() + "\"";
    }
}
