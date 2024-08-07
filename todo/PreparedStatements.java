package dev.mccue.jdbc;

import org.intellij.lang.annotations.Language;
import org.intellij.lang.annotations.MagicConstant;

import java.sql.*;

public final class PreparedStatements {
    private PreparedStatements() {}

    public static PreparedStatement of(Connection connection, @Language("SQL") String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static PreparedStatement of(Connection connection, @Language("SQL") String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }


    public static PreparedStatement of(Connection connection, @Language("SQL") String sql, int[] columnIndexes)
            throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    public static PreparedStatement of(
            Connection connection,
            @Language("SQL") String sql,
            @MagicConstant(intValues = {
                    Statement.RETURN_GENERATED_KEYS,
                    Statement.NO_GENERATED_KEYS
            }) int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    public static PreparedStatement of(
            Connection connection,
            @Language("SQL") String sql,
            @MagicConstant(intValues = {
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            }) int resultSetType,
            @MagicConstant(intValues = {
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            }) int resultSetConcurrency
    ) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public static PreparedStatement of(
            Connection connection,
            @Language("SQL") String sql,
            @MagicConstant(intValues = {
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            }) int resultSetType,
            @MagicConstant(intValues = {
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            }) int resultSetConcurrency,
            @MagicConstant(intValues = {
                    ResultSet.HOLD_CURSORS_OVER_COMMIT,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT
            }) int resultSetHoldability) throws SQLException{
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /*
    public static PreparedStatement of(Connection connection, StringTemplate stringTemplate) throws SQLException {
        return new StatementPreparer(connection::prepareStatement)
                .process(stringTemplate);
    }

    public static PreparedStatement of(Connection connection, StringTemplate stringTemplate, String[] columnNames) throws SQLException {
        return new StatementPreparer(sql -> connection.prepareStatement(sql, columnNames))
                .process(stringTemplate);
    }

    public static PreparedStatement of(Connection connection, StringTemplate stringTemplate, int[] columnIndexes) throws SQLException {
        return new StatementPreparer(sql -> connection.prepareStatement(sql, columnIndexes))
                .process(stringTemplate);
    }

    public static PreparedStatement of(
            Connection connection,
            StringTemplate stringTemplate,
            @MagicConstant(intValues = {
                    Statement.RETURN_GENERATED_KEYS,
                    Statement.NO_GENERATED_KEYS
            }) int autoGeneratedKeys) throws SQLException {
        return new StatementPreparer(sql -> connection.prepareStatement(sql, autoGeneratedKeys))
                .process(stringTemplate);
    }


    public static PreparedStatement of(
            Connection connection,
            StringTemplate stringTemplate,
            @MagicConstant(intValues = {
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            }) int resultSetType,
            @MagicConstant(intValues = {
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            }) int resultSetConcurrency
    ) throws SQLException {
        return new StatementPreparer(sql -> connection.prepareStatement(sql, resultSetType, resultSetConcurrency))
                .process(stringTemplate);
    }

    public static PreparedStatement of(
            Connection connection,
            StringTemplate stringTemplate,
            @MagicConstant(intValues = {
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            }) int resultSetType,
            @MagicConstant(intValues = {
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            }) int resultSetConcurrency,
            @MagicConstant(intValues = {
                    ResultSet.HOLD_CURSORS_OVER_COMMIT,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT
            }) int resultSetHoldability)throws SQLException {
        return new StatementPreparer(sql -> connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability))
                .process(stringTemplate);
    }

     */

}
