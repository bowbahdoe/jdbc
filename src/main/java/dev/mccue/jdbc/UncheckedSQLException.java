package dev.mccue.jdbc;

import java.io.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Wraps a {@link SQLException} with an unchecked exception.
 */
public class UncheckedSQLException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8134305061645241065L;

    public UncheckedSQLException(String message, SQLException cause) {
        super(message, Objects.requireNonNull(cause));
    }

    public UncheckedSQLException(SQLException cause) {
        super(Objects.requireNonNull(cause));
    }

    @Override
    public SQLException getCause() {
        return (SQLException) super.getCause();
    }

    @Override
    public Throwable initCause(Throwable cause) {
        if (!(cause instanceof SQLException))
            throw new IllegalArgumentException("Cause must be an SQLException");
        return super.initCause(cause);
    }

    @Serial
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        Throwable cause = super.getCause();
        if (!(cause instanceof SQLException))
            throw new InvalidObjectException("Cause must be an SQLException");
    }
}
