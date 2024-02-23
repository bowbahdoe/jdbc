package dev.mccue.jdbc.test;

import dev.mccue.jdbc.SettableParameter;
import dev.mccue.jdbc.StatementPreparer;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcTest {
    @Test
    public void testWhereClause() throws Exception {
        var path = Files.createTempFile("test", "db");
        var db = new SQLiteDataSource();
        db.setUrl(STR."jdbc:sqlite:\{path}");

        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    CREATE TABLE widget (
                        id integer primary key,
                        name text not null
                    )
                    """)) {
                stmt.execute();
            }

            try (var stmt = conn.prepareStatement("""
                   INSERT INTO widget (name)
                   VALUES ('bob')
                   """)) {
                stmt.execute();
            }

            var name = "bob";
            try (var stmt = StatementPreparer.of(conn)."""
                   SELECT * FROM widget
                   WHERE name = \{name}
                   """) {
                var rs = stmt.executeQuery();
                assertTrue(rs.next(), "Get first row");
                assertEquals(1, rs.getInt("id"));
                assertEquals("bob", rs.getString("name"));
                assertFalse(rs.next(), "But no other rows");
            }

            name = "susan";
            try (var stmt = StatementPreparer.of(conn)."""
                   SELECT * FROM widget
                   WHERE name = \{name}
                   """) {
                var rs = stmt.executeQuery();
                assertFalse(rs.next(), "Got no rows");
            }
        }
    }

    @Test
    public void testListParam() throws Exception {
        var path = Files.createTempFile("test", "db");
        var db = new SQLiteDataSource();
        db.setUrl(STR."jdbc:sqlite:\{path}");

        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    CREATE TABLE widget (
                        id integer primary key,
                        name text not null
                    )
                    """)) {
                stmt.execute();
            }

            try (var stmt = conn.prepareStatement("""
                   INSERT INTO widget (name)
                   VALUES ('bob'), ('susan'), ('peter')
                   """)) {
                stmt.execute();
            }

            var names = List.of("bob", "peter");
            try (var stmt = StatementPreparer.of(conn)."""
                   SELECT * FROM widget
                   WHERE name IN \{names}
                   ORDER BY name DESC
                   """) {
                var rs = stmt.executeQuery();
                assertTrue(rs.next(), "Get first row");
                assertEquals("bob", rs.getString("name"));
                assertTrue(rs.next(), "Get second row");
                assertEquals("peter", rs.getString("name"));
                assertFalse(rs.next(), "But no other rows");
            }

            var id = 1;
            try (var stmt = StatementPreparer.of(conn)."""
                   SELECT * FROM widget
                   WHERE name IN \{names} AND id = \{id}
                   ORDER BY name DESC
                   """) {
                var rs = stmt.executeQuery();
                assertTrue(rs.next(), "Get first row");
                assertEquals("bob", rs.getString("name"));
                assertFalse(rs.next(), "But no other rows");
            }
        }
    }

    @Test
    public void testSubstitutionStrategy() throws Exception {
        var path = Files.createTempFile("test", "db");
        var db = new SQLiteDataSource();
        db.setUrl(STR."jdbc:sqlite:\{path}");

        int x = 4;
        List<String> s = List.of("bob", "susan", "peter");
        SettableParameter o = SettableParameter.ofFloat(4.5f);

        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    CREATE TABLE widget (
                        id integer primary key,
                        name text not null,
                        value REAL
                    )
                    """)) {
                stmt.execute();
            }

            // Brittle to sqlite changes, but it's just a test and there isn't a pressing reason to upgrade
            try (var stmt = StatementPreparer.of(conn)."""
                    SELECT * FROM widget WHERE name IN \{s} OR id = \{x} OR value = \{o}
                    """) {
                assertTrue(stmt.toString().startsWith("SELECT * FROM widget WHERE name IN (?,?,?) OR id = ? OR value = ?"));
                assertTrue(stmt.toString().endsWith("[bob, susan, peter, 4, 4.5]"));
            }
        }
    }
}
