package dev.mccue.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLRunnable {
    void run() throws SQLException;
}
