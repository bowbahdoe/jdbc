package dev.mccue.jdbc;

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
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBoolean(boolean x) {
        return (stmt, parameterIndex) -> stmt.setBoolean(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setByte(int, byte)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofByte(byte x) {
        return (stmt, parameterIndex) -> stmt.setByte(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setShort(int, short)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofShort(short x) {
        return (stmt, parameterIndex) -> stmt.setShort(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setInt(int, int)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofInt(int x) {
        return (stmt, parameterIndex) -> stmt.setInt(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setLong(int, long)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofLong(long x) {
        return (stmt, parameterIndex) -> stmt.setLong(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setFloat(int, float)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofFloat(float x) {
        return (stmt, parameterIndex) -> stmt.setFloat(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setDouble(int, double)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDouble(double x) {
        return (stmt, parameterIndex) -> stmt.setDouble(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setURL(int, URL)
     * @param url The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofURL(URL url) {
        return (stmt, parameterIndex) -> stmt.setURL(parameterIndex, url);
    }

    /**
     * @see PreparedStatement#setArray(int, Array)
     * @param array The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofArray(Array array) {
        return (stmt, parameterIndex) -> stmt.setArray(parameterIndex, array);
    }

    /**
     * @see PreparedStatement#setTime(int, Time)
     * @param time The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTime(Time time) {
        return (stmt, parameterIndex) -> stmt.setTime(parameterIndex, time);
    }

    /**
     * @see PreparedStatement#setTime(int, Time, Calendar)
     * @param time The value to set.
     * @param calendar The calendar the driver will use.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTime(Time time, Calendar calendar) {
        return (stmt, parameterIndex) -> stmt.setTime(parameterIndex, time, calendar);
    }

    /**
     * @see PreparedStatement#setDate(int, Date)
     * @param date The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDate(Date date) {
        return (stmt, parameterIndex) -> stmt.setDate(parameterIndex, date);
    }

    /**
     * @see PreparedStatement#setDate(int, Date, Calendar)
     * @param date The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofDate(Date date, Calendar calendar) {
        return (stmt, parameterIndex) -> stmt.setDate(parameterIndex, date, calendar);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream)
     * @param inputStream The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream inputStream) {
        return (stmt, parameterIndex) -> stmt.setBinaryStream(parameterIndex, inputStream);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream, int)
     * @param inputStream The value to set.
     * @param length The length of the binary stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream inputStream, int length) {
        return (stmt, parameterIndex) -> stmt.setBinaryStream(parameterIndex, inputStream, length);
    }

    /**
     * @see PreparedStatement#setBinaryStream(int, InputStream, long)
     * @param inputStream The value to set.
     * @param length The length of the binary stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBinaryStream(InputStream inputStream, long length) {
        return (stmt, parameterIndex) -> stmt.setBinaryStream(parameterIndex, inputStream, length);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader) {
        return (stmt, parameterIndex) -> stmt.setCharacterStream(parameterIndex, reader);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader, int)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader, int length) {
        return (stmt, parameterIndex) -> stmt.setCharacterStream(parameterIndex, reader, length);
    }

    /**
     * @see PreparedStatement#setCharacterStream(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofCharacterStream(Reader reader, long length) {
        return (stmt, parameterIndex) -> stmt.setCharacterStream(parameterIndex, reader, length);
    }

    /**
     * @see PreparedStatement#setNCharacterStream(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNCharacterStream(Reader reader) {
        return (stmt, parameterIndex) -> stmt.setNCharacterStream(parameterIndex, reader);
    }

    /**
     * @see PreparedStatement#setNCharacterStream(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the character stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNCharacterStream(Reader reader, long length) {
        return (stmt, parameterIndex) -> stmt.setNCharacterStream(parameterIndex, reader, length);
    }

    /**
     * @see PreparedStatement#setNull(int, int)
     * @param sqlType The SQL type code defined in {@code java.sql.Types}.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNull(int sqlType) {
        return (stmt, parameterIndex) -> stmt.setNull(parameterIndex, sqlType);
    }

    /**
     * @see PreparedStatement#setNull(int, int)
     * @param sqlType The SQL type code defined in {@code java.sql.Types}.
     * @param typeName The fully-qualified name of an SQL user-defined type;
     *                 ignored if the parameter is not a user-defined type or REF.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNull(int sqlType, String typeName) {
        return (stmt, parameterIndex) -> stmt.setNull(parameterIndex, sqlType, typeName);
    }

    /**
     * @see PreparedStatement#setBigDecimal(int, BigDecimal)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBigDecimal(BigDecimal x) {
        return (stmt, parameterIndex) -> stmt.setBigDecimal(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setString(int, String)
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofString(String x) {
        return (stmt, parameterIndex) -> stmt.setString(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setBytes(int, byte[])
     * @param x The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBytes(byte[] x) {
        return (stmt, parameterIndex) -> stmt.setBytes(parameterIndex, x);
    }

    /**
     * @see PreparedStatement#setTimestamp(int, Timestamp)
     * @param timestamp The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTimestamp(Timestamp timestamp) {
        return (stmt, parameterIndex) -> stmt.setTimestamp(parameterIndex, timestamp);
    }

    /**
     * @see PreparedStatement#setTimestamp(int, Timestamp, Calendar)
     * @param timestamp The value to set.
     * @param calendar The calendar the driver will use.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofTimestamp(Timestamp timestamp, Calendar calendar) {
        return (stmt, parameterIndex) -> stmt.setTimestamp(parameterIndex, timestamp, calendar);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream)
     * @param inputStream The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream inputStream) {
        return (stmt, parameterIndex) -> stmt.setAsciiStream(parameterIndex, inputStream);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream, int)
     * @param inputStream The value to set.
     * @param length The length of the ascii stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream inputStream, int length) {
        return (stmt, parameterIndex) -> stmt.setAsciiStream(parameterIndex, inputStream, length);
    }

    /**
     * @see PreparedStatement#setAsciiStream(int, InputStream, long)
     * @param inputStream The value to set.
     * @param length The length of the ascii stream.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofAsciiStream(InputStream inputStream, long length) {
        return (stmt, parameterIndex) -> stmt.setAsciiStream(parameterIndex, inputStream, length);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param object The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object object) {
        return (stmt, parameterIndex) -> stmt.setObject(parameterIndex, object);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param object The value to set.
     * @param targetSqlType The SQL type code defined in {@code java.sql.Types}.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object object, int targetSqlType) {
        return (stmt, parameterIndex) -> stmt.setObject(parameterIndex, object, targetSqlType);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param object The value to set.
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
    static SettableParameter ofObject(Object object, int targetSqlType, int scaleOrLength) {
        return (stmt, parameterIndex) -> stmt.setObject(parameterIndex, object, targetSqlType, scaleOrLength);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param object The value to set.
     * @param targetSqlType the SQL type to be sent to the database. The
     *                      scale argument may further qualify this type.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofObject(Object object, SQLType targetSqlType) {
        return (stmt, parameterIndex) -> stmt.setObject(parameterIndex, object, targetSqlType);
    }

    /**
     * @see PreparedStatement#setObject(int, Object)
     * @param object The value to set.
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
    static SettableParameter ofObject(Object object, SQLType targetSqlType, int scaleOrLength) {
        return (stmt, parameterIndex) -> stmt.setObject(parameterIndex, object, targetSqlType, scaleOrLength);
    }

    /**
     * @see PreparedStatement#setBlob(int, Blob)
     * @param blob The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(Blob blob) {
        return (stmt, parameterIndex) -> stmt.setBlob(parameterIndex, blob);
    }

    /**
     * @see PreparedStatement#setBlob(int, InputStream)
     * @param inputStream The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(InputStream inputStream) {
        return (stmt, parameterIndex) -> stmt.setBlob(parameterIndex, inputStream);
    }

    /**
     * @see PreparedStatement#setBlob(int, InputStream)
     * @param inputStream The value to set.
     * @param length The length of the blob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofBlob(InputStream inputStream, long length) {
        return (stmt, parameterIndex) -> stmt.setBlob(parameterIndex, inputStream, length);
    }

    /**
     * @see PreparedStatement#setBlob(int, Blob)
     * @param clob The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Clob clob) {
        return (stmt, parameterIndex) -> stmt.setClob(parameterIndex, clob);
    }

    /**
     * @see PreparedStatement#setBlob(int, Blob)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Reader reader) {
        return (stmt, parameterIndex) -> stmt.setClob(parameterIndex, reader);
    }

    /**
     * @see PreparedStatement#setBlob(int, Blob)
     * @param reader The value to set.
     * @param length The length of the clob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofClob(Reader reader, long length) {
        return (stmt, parameterIndex) -> stmt.setClob(parameterIndex, reader, length);
    }

    /**
     * @see PreparedStatement#setRowId(int, RowId)
     * @param rowId The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofRowId(RowId rowId) {
        return (stmt, parameterIndex) -> stmt.setRowId(parameterIndex, rowId);
    }

    /**
     * @see PreparedStatement#setNString(int, String)
     * @param value The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNString(String value) {
        return (stmt, parameterIndex) -> stmt.setNString(parameterIndex, value);
    }

    /**
     * @see PreparedStatement#setNClob(int, NClob)
     * @param value The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(NClob value) {
        return (stmt, parameterIndex) -> stmt.setNClob(parameterIndex, value);
    }

    /**
     * @see PreparedStatement#setNClob(int, Reader)
     * @param reader The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(Reader reader) {
        return (stmt, parameterIndex) -> stmt.setNClob(parameterIndex, reader);
    }

    /**
     * @see PreparedStatement#setNClob(int, Reader, long)
     * @param reader The value to set.
     * @param length The length of the clob.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofNClob(Reader reader, long length) {
        return (stmt, parameterIndex) -> stmt.setNClob(parameterIndex, reader, length);
    }

    /**
     * @see PreparedStatement#setSQLXML(int, SQLXML)
     * @param xmlObject The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofSQLXML(SQLXML xmlObject) {
        return (stmt, parameterIndex) -> stmt.setSQLXML(parameterIndex, xmlObject);
    }

    /**
     * @see PreparedStatement#setRef(int, Ref)
     * @param ref The value to set.
     * @return A {@link SettableParameter}.
     */
    static SettableParameter ofRef(Ref ref) {
        return (stmt, parameterIndex) -> stmt.setRef(parameterIndex, ref);
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