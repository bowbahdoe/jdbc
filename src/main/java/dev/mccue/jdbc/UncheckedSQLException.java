package dev.mccue.jdbc;

import java.io.*;
import java.sql.SQLException;

/**
 * Wraps a {@link SQLException} with an unchecked exception.
 */
public class UncheckedSQLException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8134305061645241065L;

    public UncheckedSQLException(String message) {
        super(message);
    }

    public UncheckedSQLException(String message, SQLException cause) {
        super(message, cause);
    }

    public UncheckedSQLException(SQLException cause) {
        super(cause);
    }

    @Override
    public SQLException getCause() {
        return (SQLException) super.getCause();
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
