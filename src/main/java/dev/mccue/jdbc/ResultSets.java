package dev.mccue.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
            throw new SQLException(STR."Column \{index} was null");
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
            throw new SQLException(STR."Column \{columnName} was null");
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
}
