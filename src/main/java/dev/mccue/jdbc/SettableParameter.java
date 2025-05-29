package dev.mccue.jdbc;

import org.intellij.lang.annotations.MagicConstant;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;


/**
 * An object which knows how to set itself as a parameter on a {@link PreparedStatement}.
 * This design comes from <a href="https://github.com/seancorfield/next-jdbc">https://github.com/seancorfield/next-jdbc</a>.
 */
@FunctionalInterface
public interface SettableParameter {
    void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException;

    /**
     * Utility for creating a {@link SettableParameter} as the target of a lambda expression.
     * Works as an identity function otherwise.
     *
     * @param settableParameter The {@link SettableParameter} to return.
     * @return The given {@link SettableParameter}.
     */
    static SettableParameter of(SettableParameter settableParameter) {
        return settableParameter;
    }

    /**
     * @see PreparedStatement#setBoolean(int, boolean)
     * @param value The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBoolean(boolean value) {
        record OfBoolean(boolean value) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBoolean(parameterIndex, value);
            }
        }
        return new OfBoolean(value);
    }

    /**
     * @see PreparedStatement#setByte(int, byte)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofByte(byte x) {
        record OfByte(byte x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setByte(parameterIndex, x);
            }
        }
        return new OfByte(x);
    }

    /**
     * @see PreparedStatement#setShort(int, short)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofShort(short x) {
        record OfShort(short x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setShort(parameterIndex, x);
            }
        }
        return new OfShort(x);
    }

    /**
     * @see PreparedStatement#setInt(int, int)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofInt(int x) {
        record OfInt(int x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setInt(parameterIndex, x);
            }
        }
        return new OfInt(x);
    }

    /**
     * @see PreparedStatement#setLong(int, long)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofLong(long x) {
        record OfLong(long x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setLong(parameterIndex, x);
            }
        }
        return new OfLong(x);
    }

    /**
     * @see PreparedStatement#setFloat(int, float)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofFloat(float x) {
        record OfFloat(float x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setFloat(parameterIndex, x);
            }
        }
        return new OfFloat(x);
    }

    /**
     * @see PreparedStatement#setDouble(int, double)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDouble(double x) {
        record OfDouble(double x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setDouble(parameterIndex, x);
            }
        }
        return new OfDouble(x);
    }

    /**
     * @see PreparedStatement#setURL(int, URL)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofURL(URL x) {
        record OfURL(URL x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setURL(parameterIndex, x);
            }
        }
        return new OfURL(x);
    }

    /**
     * @see PreparedStatement#setArray(int, Array)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofArray(Array x) {
        record OfArray(Array x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setArray(parameterIndex, x);
            }
        }
        return new OfArray(x);
    }

    /**
     * @see PreparedStatement#setTime(int, Time)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTime(Time x) {
        record OfTime(Time x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setTime(parameterIndex, x);
            }
        }
        return new OfTime(x);
    }

    /**
     * @see PreparedStatement#setTime(int, Time, Calendar)
     * @param x The value to set.
     * @param cal The calendar the driver will use.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTime(Time x, Calendar cal) {
        record OfTime(Time x, Calendar cal) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setTime(parameterIndex, x, cal);
            }
        }
        return new OfTime(x, cal);
    }

    /**
     * @see PreparedStatement#setDate(int, Date)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDate(Date x) {
        record OfDate(Date x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setDate(parameterIndex, x);
            }
        }
        return new OfDate(x);
    }

    /**
     * @see PreparedStatement#setDate(int, Date, Calendar)
     * @param x The value to set.
     * @param cal The calendar the driver will use.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDate(Date x, Calendar cal) {
        record OfDate(Date x, Calendar cal) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setDate(parameterIndex, x, cal);
            }
        }
        return new OfDate(x, cal);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream x) {
        record OfBinaryStream(InputStream x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBinaryStream(parameterIndex, x);
            }
        }
        return new OfBinaryStream(x);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream, int)
     * @param x The value to set.
     * @param length The length of the binary stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream x, int length) {
        record OfBinaryStream(InputStream x, int length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBinaryStream(parameterIndex, x, length);
            }
        }
        return new OfBinaryStream(x, length);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream, long)
     * @param x The value to set.
     * @param length The length of the binary stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream x, long length) {
        record OfBinaryStream(InputStream x, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBinaryStream(parameterIndex, x, length);
            }
        }
        return new OfBinaryStream(x, length);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader) {
        record OfCharacterStream(Reader reader) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setCharacterStream(parameterIndex, reader);
            }
        }
        return new OfCharacterStream(reader);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader, int)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader, int length) {
        record OfCharacterStream(Reader reader, int length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setCharacterStream(parameterIndex, reader, length);
            }
        }
        return new OfCharacterStream(reader, length);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader, long length) {
        record OfCharacterStream(Reader reader, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setCharacterStream(parameterIndex, reader, length);
            }
        }
        return new OfCharacterStream(reader, length);
    }

    /**
     * @see PreparedStatement#setNCharacterStream(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNCharacterStream(Reader reader) {
        record OfNCharacterStream(Reader reader) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNCharacterStream(parameterIndex, reader);
            }
        }
        return new OfNCharacterStream(reader);
    }

    /**
     * @see PreparedStatement#setNCharacterStream(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNCharacterStream(Reader reader, long length) {
        record OfNCharacterStream(Reader reader, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNCharacterStream(parameterIndex, reader, length);
            }
        }
        return new OfNCharacterStream(reader, length);
    }

    /**
     * @see PreparedStatement#setNull(int, int)
     * @param sqlType The SQL type code defined in {@code java.sql.Types}.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNull(int sqlType) {
        record OfNull(int sqlType) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNull(parameterIndex, sqlType);
            }
        }
        return new OfNull(sqlType);
    }

    /**
     * @see PreparedStatement#setNull(int, int)
     * @param sqlType The SQL type code defined in {@code java.sql.Types}.
     * @param typeName The fully-qualified name of an SQL user-defined type;
     *                 ignored if the parameter is not a user-defined type or REF.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNull(int sqlType, String typeName) {
        record OfNull(int sqlType, String typeName) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNull(parameterIndex, sqlType, typeName);
            }
        }
        return new OfNull(sqlType, typeName);
    }

    /**
     * @see PreparedStatement#setBigDecimal(int, BigDecimal)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBigDecimal(BigDecimal x) {
        record OfBigDecimal(BigDecimal x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBigDecimal(parameterIndex, x);
            }
        }
        return new OfBigDecimal(x);
    }

    /**
     * @see PreparedStatement#setString(int, String)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofString(String x) {
        record OfString(String x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setString(parameterIndex, x);
            }
        }
        return new OfString(x);
    }

    /**
     * @see PreparedStatement#setBytes(int, byte[])
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBytes(byte[] x) {
        record OfBytes(byte[] x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBytes(parameterIndex, x);
            }
        }
        return new OfBytes(x);
    }

    /**
     * @see PreparedStatement#setTimestamp(int, Timestamp)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTimestamp(Timestamp x) {
        record OfTimestamp(Timestamp x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setTimestamp(parameterIndex, x);
            }
        }
        return new OfTimestamp(x);
    }

    /**
     * @see PreparedStatement#setTimestamp(int, Timestamp, Calendar)
     * @param x The value to set.
     * @param cal The calendar the driver will use.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTimestamp(Timestamp x, Calendar cal) {
        record OfTimestamp(Timestamp x, Calendar cal) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setTimestamp(parameterIndex, x, cal);
            }
        }
        return new OfTimestamp(x, cal);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream x) {
        record OfAsciiStream(InputStream x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setAsciiStream(parameterIndex, x);
            }
        }
        return new OfAsciiStream(x);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream, int)
     * @param x The value to set.
     * @param length The length of the ascii stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream x, int length) {
        record OfAsciiStream(InputStream x, int length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setAsciiStream(parameterIndex, x, length);
            }
        }
        return new OfAsciiStream(x, length);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream, long)
     * @param x The value to set.
     * @param length The length of the ascii stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream x, long length) {
        record OfAsciiStream(InputStream x, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setAsciiStream(parameterIndex, x, length);
            }
        }
        return new OfAsciiStream(x, length);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object x) {
        record OfObject(Object x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setObject(parameterIndex, x);
            }
        }
        return new OfObject(x);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param x The value to set.
     * @param targetSqlType The SQL type code defined in {@code java.sql.Types}.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object x, @MagicConstant(valuesFromClass = Types.class) int targetSqlType) {
        record OfObject(Object x, int targetSqlType) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setObject(parameterIndex, x, targetSqlType);
            }
        }
        return new OfObject(x, targetSqlType);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param x The value to set.
     * @param targetSqlType The SQL type code defined in {@code java.sql.Types}.
     * @param scaleOrLength for {@code java.sql.Types.DECIMAL}
     *                or {@code java.sql.Types.NUMERIC types},
     *                this is the number of digits after the decimal point. For
     *                Java Object types {@code InputStream} and {@code Reader},
     *                this is the length
     *                of the data in the stream or reader.  For all other types,
     *                this value will be ignored.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object x, @MagicConstant(valuesFromClass = Types.class) int targetSqlType, int scaleOrLength) {
        record OfObject(Object x, int targetSqlType, int scaleOrLength) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
            }
        }
        return new OfObject(x, targetSqlType, scaleOrLength);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param x The value to set.
     * @param targetSqlType the SQL type to be sent to the database. The
     *                      scale argument may further qualify this type.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object x, SQLType targetSqlType) {
        record OfObject(Object x, SQLType targetSqlType) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setObject(parameterIndex, x, targetSqlType);
            }
        }
        return new OfObject(x, targetSqlType);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param x The value to set.
     * @param targetSqlType the SQL type to be sent to the database. The
     *                      scale argument may further qualify this type.
     * @param scaleOrLength for {@code java.sql.Types.DECIMAL}
     *                or {@code java.sql.Types.NUMERIC types},
     *                this is the number of digits after the decimal point. For
     *                Java Object types {@code InputStream} and {@code Reader},
     *                this is the length
     *                of the data in the stream or reader.  For all other types,
     *                this value will be ignored.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object x, SQLType targetSqlType, int scaleOrLength) {
        record OfObject(Object x, SQLType targetSqlType, int scaleOrLength) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
            }
        }
        return new OfObject(x, targetSqlType, scaleOrLength);
    }

    /**
     * @see PreparedStatement#setBlob(int, Blob)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(Blob x) {
        record OfBlob(Blob x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBlob(parameterIndex, x);
            }
        }
        return new OfBlob(x);
    }

    /**
     * @see PreparedStatement#setBlob(int, InputStream)
     * @param inputStream The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(InputStream inputStream) {
        record OfBlob(InputStream inputStream) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBlob(parameterIndex, inputStream);
            }
        }
        return new OfBlob(inputStream);
    }

    /**
     * @see PreparedStatement#setBlob(int, InputStream)
     * @param inputStream The value to set.
     * @param length The length of the blob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(InputStream inputStream, long length) {
        record OfBlob(InputStream inputStream, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setBlob(parameterIndex, inputStream, length);
            }
        }
        return new OfBlob(inputStream, length);
    }

    /**
     * @see PreparedStatement#setClob(int, Clob)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Clob x) {
        record OfClob(Clob x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setClob(parameterIndex, x);
            }
        }
        return new OfClob(x);
    }

    /**
     * @see PreparedStatement#setClob(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Reader reader) {
        record OfClob(Reader reader) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setClob(parameterIndex, reader);
            }
        }
        return new OfClob(reader);
    }

    /**
     * @see PreparedStatement#setClob(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the clob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Reader reader, long length) {
        record OfClob(Reader reader, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setClob(parameterIndex, reader, length);
            }
        }
        return new OfClob(reader, length);
    }

    /**
     * @see PreparedStatement#setRowId(int, RowId)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofRowId(RowId x) {
        record OfRowId(RowId x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setRowId(parameterIndex, x);
            }
        }
        return new OfRowId(x);
    }

    /**
     * @see PreparedStatement#setNString(int, String)
     * @param value The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNString(String value) {
        record OfNString(String value) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNString(parameterIndex, value);
            }
        }
        return new OfNString(value);
    }

    /**
     * @see PreparedStatement#setNClob(int, NClob)
     * @param value The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(NClob value) {
        record OfNClob(NClob value) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNClob(parameterIndex, value);
            }
        }
        return new OfNClob(value);
    }

    /**
     * @see PreparedStatement#setNClob(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(Reader reader) {
        record OfNClob(Reader reader) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNClob(parameterIndex, reader);
            }
        }
        return new OfNClob(reader);
    }

    /**
     * @see PreparedStatement#setNClob(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the clob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(Reader reader, long length) {
        record OfNClob(Reader reader, long length) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setNClob(parameterIndex, reader, length);
            }
        }
        return new OfNClob(reader, length);
    }

    /**
     * @see PreparedStatement#setSQLXML(int, SQLXML)
     * @param xmlObject The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofSQLXML(SQLXML xmlObject) {
        record OfSQLXML(SQLXML xmlObject) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setSQLXML(parameterIndex, xmlObject);
            }
        }
        return new OfSQLXML(xmlObject);
    }

    /**
     * @see PreparedStatement#setRef(int, Ref)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofRef(Ref x) {
        record OfRef(Ref x) implements SettableParameter {
            @Override
            public void setParameter(PreparedStatement stmt, int parameterIndex) throws SQLException {
                stmt.setRef(parameterIndex, x);
            }
        }
        return new OfRef(x);
    }


    /*
public abstract void java.sql.PreparedStatement.setBoolean(int,boolean) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setByte(int,byte) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setShort(int,short) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setInt(int,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setLong(int,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setFloat(int,float) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setDouble(int,double) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setURL(int,java.net.URL) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setArray(int,java.sql.Array) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setTime(int,java.sql.Time) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setTime(int,java.sql.Time,java.util.Calendar) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setDate(int,java.sql.Date) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setDate(int,java.sql.Date,java.util.Calendar) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setUnicodeStream(int,java.io.InputStream,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBinaryStream(int,java.io.InputStream,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBinaryStream(int,java.io.InputStream,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBinaryStream(int,java.io.InputStream) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setCharacterStream(int,java.io.Reader) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setCharacterStream(int,java.io.Reader,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setCharacterStream(int,java.io.Reader,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNCharacterStream(int,java.io.Reader,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNCharacterStream(int,java.io.Reader) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNull(int,int,java.lang.String) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNull(int,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBigDecimal(int,java.math.BigDecimal) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setString(int,java.lang.String) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBytes(int,byte[]) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setTimestamp(int,java.sql.Timestamp) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setTimestamp(int,java.sql.Timestamp,java.util.Calendar) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setAsciiStream(int,java.io.InputStream,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setAsciiStream(int,java.io.InputStream) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setAsciiStream(int,java.io.InputStream,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setObject(int,java.lang.Object,int,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setObject(int,java.lang.Object) throws java.sql.SQLException
public default void java.sql.PreparedStatement.setObject(int,java.lang.Object,java.sql.SQLType) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setObject(int,java.lang.Object,int) throws java.sql.SQLException
public default void java.sql.PreparedStatement.setObject(int,java.lang.Object,java.sql.SQLType,int) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBlob(int,java.sql.Blob) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBlob(int,java.io.InputStream) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setBlob(int,java.io.InputStream,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setClob(int,java.sql.Clob) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setClob(int,java.io.Reader) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setClob(int,java.io.Reader,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setRowId(int,java.sql.RowId) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNString(int,java.lang.String) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNClob(int,java.io.Reader,long) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNClob(int,java.sql.NClob) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setNClob(int,java.io.Reader) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setSQLXML(int,java.sql.SQLXML) throws java.sql.SQLException
public abstract void java.sql.PreparedStatement.setRef(int,java.sql.Ref) throws java.sql.SQLException

     */
}