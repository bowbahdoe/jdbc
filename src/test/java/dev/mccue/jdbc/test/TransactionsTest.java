package dev.mccue.jdbc.test;

import dev.mccue.jdbc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsTest {
    SQLiteDataSource db;

    @BeforeEach
    public void setUp() throws Exception {
        var path = Files.createTempFile("test", "db");
        var db = new SQLiteDataSource();
        db.setUrl("jdbc:sqlite:" + path);
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    CREATE TABLE widget (
                        id integer primary key,
                        name text not null,
                        number integer,
                        float_number real
                    )
                    """)) {
                stmt.execute();
            }
        }

        this.db = db;
    }

    @Test
    public void testRollback() throws Exception {
        record Widget(
                String name,
                int number,
                @Column(label = "float_number") double floatNumber
        ) {}

        try (var conn = db.getConnection()) {
            Connections.transact(conn, () -> {
                try (var stmt = conn.prepareStatement("""
                        INSERT INTO widget(name, number, float_number)
                        VALUES ('z', 9, 12.5)
                        """)) {
                    stmt.execute();
                }

                try (var stmt = conn.prepareStatement("""
                        INSERT INTO widget(name, number, float_number)
                        VALUES ('113', 94, 125.5)
                        """)) {
                    stmt.execute();
                }

                try (var stmt = conn.prepareStatement("""
                        SELECT name, number, float_number
                        FROM widget
                        """)) {
                    assertEquals(
                            ResultSets.stream(
                                    stmt.executeQuery(),
                                            ResultSets.getRecord(Widget.class, MethodHandles.lookup())
                                    ).toList(),
                            List.of(
                                    new Widget("z", 9, 12.5),
                                    new Widget("113", 94, 125.5)
                            )
                    );
                }

                throw new RuntimeException("Should rollback");
            });
        } catch (RuntimeException ignored) {}

        try (var conn = db.getConnection();
             var stmt = conn.prepareStatement("""
                        SELECT name, number, float_number
                        FROM widget
                        """)) {
            assertEquals(
                ResultSets.stream(stmt.executeQuery(), ResultSets.getRecord(Widget.class, MethodHandles.lookup()))
                        .toList(),
                    List.of()
            );
        }
    }

    @Test
    public void testRollbackDatasource() throws Exception {
        record Widget(
                String name,
                int number,
                @Column(label = "float_number") double floatNumber
        ) {}

        try {
            DataSources.transact(db, conn -> {
                try (var stmt = conn.prepareStatement("""
                        INSERT INTO widget(name, number, float_number)
                        VALUES ('z', 9, 12.5)
                        """)) {
                    stmt.execute();
                }

                try (var stmt = conn.prepareStatement("""
                        INSERT INTO widget(name, number, float_number)
                        VALUES ('113', 94, 125.5)
                        """)) {
                    stmt.execute();
                }

                try (var stmt = conn.prepareStatement("""
                        SELECT name, number, float_number
                        FROM widget
                        """)) {
                    assertEquals(
                            ResultSets.stream(
                                    stmt.executeQuery(),
                                    ResultSets.getRecord(Widget.class, MethodHandles.lookup())
                            ).toList(),
                            List.of(
                                    new Widget("z", 9, 12.5),
                                    new Widget("113", 94, 125.5)
                            )
                    );
                }

                if (false) {
                    return null;
                }

                throw new RuntimeException("Should rollback");
            });
        } catch (RuntimeException ignored) {}

        try (var conn = db.getConnection();
             var stmt = conn.prepareStatement("""
                        SELECT name, number, float_number
                        FROM widget
                        """)) {
            assertEquals(
                    ResultSets.stream(stmt.executeQuery(), ResultSets.getRecord(Widget.class, MethodHandles.lookup()))
                            .toList(),
                    List.of()
            );
        }
    }
}
