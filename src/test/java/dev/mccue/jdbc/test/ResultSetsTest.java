package dev.mccue.jdbc.test;

import dev.mccue.jdbc.Column;
import dev.mccue.jdbc.ResultSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultSetsTest {
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

            try (var stmt = conn.prepareStatement("""
                    INSERT INTO widget (name, number, float_number)
                    VALUES ('a', 1, 1.5), ('b', null, null), ('c', 2, 2.3)
                    """)) {
                stmt.execute();
            }
        }

        this.db = db;
    }

    @Test
    public void getNullableByte() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals((byte) 1, ResultSets.getByteNullable(rs, "number"));
                assertEquals((byte) 1, ResultSets.getByteNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getByteNullable(rs, "number"));
                assertNull(ResultSets.getByteNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullByte() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getByteNotNull(rs, "number"));
                assertEquals(1, ResultSets.getByteNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getByteNotNull(rs, "number"));
                assertThrows(SQLException.class, () -> ResultSets.getByteNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableShort() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals((short) 1, ResultSets.getShortNullable(rs, "number"));
                assertEquals((short) 1, ResultSets.getShortNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getShortNullable(rs, "number"));
                assertNull(ResultSets.getShortNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullShort() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getShortNotNull(rs, "number"));
                assertEquals(1, ResultSets.getShortNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getShortNotNull(rs, "number"));
                assertThrows(SQLException.class, () -> ResultSets.getShortNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableInt() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getIntegerNullable(rs, "number"));
                assertEquals(1, ResultSets.getIntegerNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getIntegerNullable(rs, "number"));
                assertNull(ResultSets.getIntegerNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullInt() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getIntegerNotNull(rs, "number"));
                assertEquals(1, ResultSets.getIntegerNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getIntegerNotNull(rs, "number"));
                assertThrows(SQLException.class, () -> ResultSets.getIntegerNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableLong() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getLongNullable(rs, "number"));
                assertEquals(1, ResultSets.getLongNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getLongNullable(rs, "number"));
                assertNull(ResultSets.getLongNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullLong() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1, ResultSets.getLongNotNull(rs, "number"));
                assertEquals(1, ResultSets.getLongNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getLongNotNull(rs, "number"));
                assertThrows(SQLException.class, () -> ResultSets.getLongNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableFloat() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT float_number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1.5f, ResultSets.getFloatNullable(rs, "float_number"));
                assertEquals(1.5f, ResultSets.getFloatNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getFloatNullable(rs, "float_number"));
                assertNull(ResultSets.getFloatNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullFloat() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT float_number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1.5, ResultSets.getFloatNotNull(rs, "float_number"));
                assertEquals(1.5, ResultSets.getFloatNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getFloatNotNull(rs, "float_number"));
                assertThrows(SQLException.class, () -> ResultSets.getFloatNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableDouble() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT float_number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1.5, ResultSets.getDoubleNullable(rs, "float_number"));
                assertEquals(1.5, ResultSets.getDoubleNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getDoubleNullable(rs, "float_number"));
                assertNull(ResultSets.getDoubleNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullDouble() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT float_number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(1.5, ResultSets.getDoubleNotNull(rs, "float_number"));
                assertEquals(1.5, ResultSets.getDoubleNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getDoubleNotNull(rs, "float_number"));
                assertThrows(SQLException.class, () -> ResultSets.getDoubleNotNull(rs, 1));
            }
        }
    }

    @Test
    public void getNullableBoolean() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number
                    FROM widget
                    WHERE name = 'a' OR name = 'b'
                    """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(true, ResultSets.getBooleanNullable(rs, "number"));
                assertEquals(true, ResultSets.getBooleanNullable(rs, 1));

                rs.next();

                assertNull(ResultSets.getBooleanNullable(rs, "number"));
                assertNull(ResultSets.getBooleanNullable(rs, 1));
            }
        }
    }

    @Test
    public void getNonNullBoolean() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                rs.next();
                assertEquals(true, ResultSets.getBooleanNotNull(rs, "number"));
                assertEquals(true, ResultSets.getBooleanNotNull(rs, 1));

                rs.next();

                assertThrows(SQLException.class, () -> ResultSets.getBooleanNotNull(rs, "number"));
                assertThrows(SQLException.class, () -> ResultSets.getBooleanNotNull(rs, 1));
            }
        }
    }

    public record GetRecordTestResult(
            @Column(label = "number") Integer n,
            String name
    ) {}

    @Test
    public void getRecordTest() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number, name
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {
                var rs = stmt.executeQuery();
                var record = ResultSets.getRecord(rs, GetRecordTestResult.class);
                assertEquals(record, new GetRecordTestResult(1, "a"));
            }
        }
    }

    public record GetRecordIntTestResult(
            @Column(label = "number") int n,
            String name
    ) {}


    @Test
    public void getRecordIntTest() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT number, name
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {

                var rs = stmt.executeQuery();
                var record = ResultSets.getRecord(rs, GetRecordIntTestResult.class);
                assertEquals(record, new GetRecordIntTestResult(1, "a"));
            }
        }
    }

    public record GetRecordListTestResult(
            String name
    ) {}

    @Test
    public void getRecordListTest() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT name
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {

                var rs = stmt.executeQuery();
                var record = ResultSets.getList(
                        rs,
                        ResultSets.getRecord(GetRecordListTestResult.class)
                );
                assertEquals(record, List.of(
                        new GetRecordListTestResult("a"),
                        new GetRecordListTestResult("b")
                ));
            }
        }
    }

    @Test
    public void getRecordStreamTest() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                        SELECT name
                        FROM widget
                        WHERE name = 'a' OR name = 'b'
                        """)) {

                var rs = stmt.executeQuery();
                var record = ResultSets.stream(
                        rs,
                        ResultSets.getRecord(GetRecordListTestResult.class)
                ).toList();
                assertEquals(record, List.of(
                        new GetRecordListTestResult("a"),
                        new GetRecordListTestResult("b")
                ));
            }

            try (var stmt = conn.prepareStatement("""
                        SELECT name
                        FROM widget
                        WHERE name = 'not_there'
                        """)) {

                var rs = stmt.executeQuery();
                var record = ResultSets.stream(
                        rs,
                        ResultSets.getRecord(GetRecordListTestResult.class)
                ).toList();
                assertEquals(record, List.of());
            }
        }
    }

    /*
    public record Text(String contents) {
    }

    public static final class CustomRecordComponentGetter extends DefaultRecordComponentGetter {
        @Override
        protected Object getIndexedRecordComponent(
                ResultSet rs,
                RecordComponent recordComponent,
                int index
        ) throws SQLException {
            return new Text(rs.getString(index));
        }

        @Override
        protected Object getLabeledRecordComponent(
                ResultSet rs,
                RecordComponent recordComponent,
                String label
        ) throws SQLException {
            return new Text(rs.getString(label));
        }
    }

    public record Widget(
            @Column(label = "number")
            int n,
            @Column(
                    recordComponentGetter = CustomRecordComponentGetter.class
            )
            Text name
    ) {
    }

    @Test
    public void customMapperTest() throws Exception {
        try (var conn = db.getConnection()) {
            try (var stmt = conn.prepareStatement("""
                    SELECT number, name
                    FROM widget
                    LIMIT 1
                    """)) {
                var rs = stmt.executeQuery();
                var widget = ResultSets.getRecord(rs, Widget.class);

                assertEquals(widget, new Widget(1, new Text("a")));
                System.out.println(widget);
            }
        }
    }
     */



}
