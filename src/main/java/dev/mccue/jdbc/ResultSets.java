package dev.mccue.jdbc;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Utilities for reading data out of {@link ResultSet}s. Specifically, static methods for reading
 * primitive types out of result sets are provided with explicit behavior around null values.
 *
 * <p>
 *     The choice of methods to provide was influenced by the helpers given in
 *     <a href="https://github.com/squigglesql/squigglesql/blob/master/src/main/java/com/github/squigglesql/squigglesql/util/JdbcUtils.java">squigglesql</a>.
 * </p>
 */
public final class ResultSets {
    private ResultSets() {
    }

    /**
     * Gets a boolean value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A boolean value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static boolean getBooleanNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getBoolean(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a boolean value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A boolean value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static boolean getBooleanNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getBoolean(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a boolean value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A boolean value
     * @throws SQLException If the driver throws an exception.
     */
    public static Boolean getBooleanNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getBoolean(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a boolean value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A boolean value
     * @throws SQLException If the driver throws an exception.
     */
    public static Boolean getBooleanNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getBoolean(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a byte value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A byte value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static byte getByteNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getByte(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a byte value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A byte value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static byte getByteNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getByte(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a byte value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A byte value
     * @throws SQLException If the driver throws an exception.
     */
    public static Byte getByteNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getByte(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a byte value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A byte value
     * @throws SQLException If the driver throws an exception.
     */
    public static Byte getByteNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getByte(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a short value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A short value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static short getShortNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getShort(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a short value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A short value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static short getShortNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getShort(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a short value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A short value
     * @throws SQLException If the driver throws an exception.
     */
    public static Short getShortNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getShort(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a short value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A short value
     * @throws SQLException If the driver throws an exception.
     */
    public static Short getShortNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getShort(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets an int value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return An int value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static int getIntegerNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getInt(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets an int value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return An int value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static int getIntegerNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getInt(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets an int value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return An int value
     * @throws SQLException If the driver throws an exception.
     */
    public static Integer getIntegerNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getInt(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets an int value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return An int value
     * @throws SQLException If the driver throws an exception.
     */
    public static Integer getIntegerNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a long value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A long value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static long getLongNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getLong(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a long value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A long value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static long getLongNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getLong(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a long value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A long value
     * @throws SQLException If the driver throws an exception.
     */
    public static Long getLongNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getLong(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a long value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A long value
     * @throws SQLException If the driver throws an exception.
     */
    public static Long getLongNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getLong(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a float value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A float value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static float getFloatNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getFloat(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a float value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A float value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static float getFloatNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getFloat(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a float value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A float value
     * @throws SQLException If the driver throws an exception.
     */
    public static Float getFloatNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getFloat(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a float value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A float value
     * @throws SQLException If the driver throws an exception.
     */
    public static Float getFloatNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getFloat(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a double value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A double value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static double getDoubleNotNull(ResultSet rs, int index) throws SQLException {
        var value = rs.getDouble(index);
        if (rs.wasNull()) {
            throw new SQLException("Column " + index + " was null");
        }
        return value;
    }

    /**
     * Gets a double value from a {@link ResultSet}, throwing a {@link SQLException}
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A double value
     * @throws SQLException If the driver throws an exception or if the value in the column is null.
     */
    public static double getDoubleNotNull(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getDouble(columnName);
        if (rs.wasNull()) {
            throw new SQLException("Column " + columnName + " was null");
        }
        return value;
    }

    /**
     * Gets a double value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param index The column to get.
     * @return A double value
     * @throws SQLException If the driver throws an exception.
     */
    public static Double getDoubleNullable(ResultSet rs, int index) throws SQLException {
        var value = rs.getDouble(index);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Gets a double value from a {@link ResultSet}, returning null
     * if the column is null.
     *
     * @param rs The {@link ResultSet}
     * @param columnName The column to get.
     * @return A double value
     * @throws SQLException If the driver throws an exception.
     */
    public static Double getDoubleNullable(ResultSet rs, String columnName) throws SQLException {
        var value = rs.getDouble(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Directly maps the current row to a record.
     *
     * <p>
     *     Record component names are used as the names for columns. Record component types
     *     are given to {@link ResultSet#getObject(String, Class)}.
     * </p>
     *
     * <p>
     *     If a record component is annotated with {@link Column} then the value
     *     specified by that annotation will be used for the column name.
     * </p>
     *
     * @param rs The {@link ResultSet}
     * @param klass The {@link Record} class to map to.
     * @return An instance of the given record.
     * @param <T> The type of the {@link Record}.
     * @throws SQLException If an error occurs.
     */
    public static <T extends Record> T getRecord(
            ResultSet rs,
            Class<T> klass
    ) throws SQLException {
        return getRecord(klass).get(rs);
    }

    /**
     * {@link ResultSetGetter} which retrieves a full record as with
     * {@link ResultSets#getRecord(ResultSet,Class)}
     * @param klass The record class.
     * @return A {@link ResultSetGetter}
     * @param <T> The type of record.
     */
    public static <T extends Record> ResultSetGetter<T> getRecord(
            Class<T> klass
    ) {
        return getRecord(klass, MethodHandles.publicLookup());
    }

    /**
     * {@link ResultSetGetter} which retrieves a full record as with
     * {@link ResultSets#getRecord(ResultSet,Class,MethodHandles.Lookup)}
     * @param klass The record class.
     * @return A {@link ResultSetGetter}
     * @param <T> The type of record.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Record> ResultSetGetter<T> getRecord(
            Class<T> klass,
            MethodHandles.Lookup lookup
    ) {
        if (!klass.isRecord()) {
            throw new IllegalArgumentException("Provided class is not a record: " + klass.getName());
        }
        var components = klass.getRecordComponents();
        var componentTypes = Arrays.stream(klass.getRecordComponents())
                .map(RecordComponent::getType)
                .toArray(Class<?>[]::new);
        try {
            var constructor = lookup
                    .findConstructor(klass, MethodType.methodType(void.class, componentTypes));
            return rs -> {
                var o = new Object[components.length];

                for (int i = 0; i < o.length; i++) {
                    var component = components[i];
                    RecordComponentGetter<?> mapper
                            = DefaultRecordComponentGetter.INSTANCE;;
                    o[i] = mapper.getRecordComponent(rs, component);
                }

                try {
                    return (T) constructor.invokeWithArguments(o);
                } catch (Throwable t) {
                    throw new SQLException(t);
                }
            };
        } catch (NoSuchMethodException
                 | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Directly maps the current row to a record.
     *
     * <p>
     *     Record component names are used as the names for columns. Record component types
     *     are given to {@link ResultSet#getObject(String, Class)}.
     * </p>
     *
     * <p>
     *     If a record component is annotated with {@link Column} then the value
     *     specified by that annotation will be used for the column name.
     * </p>
     *
     * @param rs The {@link ResultSet}
     * @param klass The {@link Record} class to map to.
     * @param lookup A {@link MethodHandles.Lookup} for accessing record constructors and
     *               instantiating any custom {@link RecordComponentGetter}.
     * @return An instance of the given record.
     * @param <T> The type of the {@link Record}.
     * @throws SQLException If an error occurs.
     */
    public static <T extends Record> T getRecord(
            ResultSet rs,
            Class<T> klass,
            MethodHandles.Lookup lookup
    ) throws SQLException {
        return getRecord(klass, lookup).get(rs);
    }

    /**
     * Pulls a list of data from a {@link ResultSet}.
     *
     * @param rs The {@link ResultSet} to pull from.
     * @param getter Called to get each item of the list.
     * @return A list of data.
     * @param <T> The type of data in the list.
     * @throws SQLException If something goes wrong.
     */
    public static <T> List<T> getList(ResultSet rs, ResultSetGetter<? extends T> getter) throws SQLException {
        var items = new ArrayList<T>();
        while (rs.next()) {
            items.add(getter.get(rs));
        }
        return Collections.unmodifiableList(items);
    }
}
