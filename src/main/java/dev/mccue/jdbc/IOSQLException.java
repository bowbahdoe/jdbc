package dev.mccue.jdbc;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.sql.SQLException;
import java.util.Objects;

/// A {@link SQLException} caused by an {@link IOException}
public class IOSQLException extends SQLException {
    @Serial
    private static final long serialVersionUID = 1;

    public IOSQLException(String message, IOException cause) {
        super(message, Objects.requireNonNull(cause));
    }

    public IOSQLException(IOException cause) {
        super(Objects.requireNonNull(cause));
    }

    @Override
    public IOException getCause() {
        return (IOException) super.getCause();
    }

    @Override
    public Throwable initCause(Throwable cause) {
        if (!(cause instanceof IOException))
            throw new IllegalArgumentException("Cause must be an IOException");
        return super.initCause(cause);
    }

    @Serial
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        Throwable cause = super.getCause();
        if (!(cause instanceof IOException))
            throw new InvalidObjectException("Cause must be an IOException");
    }
}