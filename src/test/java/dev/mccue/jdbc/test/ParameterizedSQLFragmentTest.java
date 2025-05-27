package dev.mccue.jdbc.test;

import dev.mccue.jdbc.ParameterizedSQLFragment;
import dev.mccue.jdbc.SQLFragment;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedSQLFragmentTest {
    @Test
    public void testNoReplacements() {
        assertEquals(
                ParameterizedSQLFragment.of("A").apply(Map.of()),
                SQLFragment.of("A")
        );
    }

    @Test
    public void testOneReplacement() {
        assertEquals(
                ParameterizedSQLFragment.of("A :name B").apply(Map.of("name", "bob")),
                SQLFragment.of("A ? B", List.of("bob"))
        );
    }

    @Test
    public void testMultipleReplacements() {
        assertEquals(
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a AND b = :b AND c = :a AND d <> :a and e IN :c
                        """).apply(Map.of("a", "A", "b", "B", "c", "C")),
                SQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = ? AND b = ? AND c = ? AND d <> ? and e IN ?
                        """, List.of("A", "B", "A", "A", "C"))
        );
    }

    @Test
    public void testRegexBounds() {
        assertEquals(
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a-b AND b = :c_d AND c = :0 AND something
                        """).apply(Map.of("a-b", "AB", "c_d", "CD", "0", 5)),
                SQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = ? AND b = ? AND c = ? AND something
                        """, List.of("AB", "CD", 5))
        );
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a-b AND b = :c_d AND c = :0 AND something
                        """),
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a-b AND b = :c_d AND c = :0 AND something
                        """)
        );

        assertEquals(
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a-b AND b = :c_d AND c = :0 AND something
                        """).hashCode(),
                ParameterizedSQLFragment.of("""
                        SELECT *
                        FROM stuff
                        WHERE a = :a-b AND b = :c_d AND c = :0 AND something
                        """).hashCode()
        );
    }
}
