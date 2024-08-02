/**
 * Utilities for working with the low level JDBC API.
 */
module dev.mccue.jdbc {
    requires static org.jetbrains.annotations;
    requires transitive java.sql;

    exports dev.mccue.jdbc;
}