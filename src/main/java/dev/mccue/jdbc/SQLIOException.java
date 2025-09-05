package dev.mccue.jdbc;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.sql.SQLException;
import java.util.Objects;

/// An {@link IOException} caused by a {@link SQLException}
public class SQLIOException extends IOException {
    @Serial
    private static final long serialVersionUID = 9;

    public SQLIOException(String message, SQLException cause) {
        super(message, Objects.requireNonNull(cause));
    }

    public SQLIOException(SQLException cause) {
        super(Objects.requireNonNull(cause));
    }

    @Override
    public SQLException getCause() {
        return (SQLException) super.getCause();
    }

    @Override
    public Throwable initCause(Throwable cause) {
        if (!(cause instanceof SQLException))
            throw new IllegalArgumentException("Cause must be a SQLException");
        return super.initCause(cause);
    }

    @Serial
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        Throwable cause = super.getCause();
        if (!(cause instanceof SQLException))
            throw new InvalidObjectException("Cause must be a SQLException");
    }
}