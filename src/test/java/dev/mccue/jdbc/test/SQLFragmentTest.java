package dev.mccue.jdbc.test;

import dev.mccue.jdbc.SQLFragment;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLFragmentTest {
    @Test
    public void allowsNullParams() {
        var fragment = SQLFragment.of("SELECT ?", Arrays.asList(1, null, "a", null));
        assertEquals(fragment.parameters(), Arrays.asList(1, null, "a", null));
    }

    @Test
    public void concatsInOrder() {
        var fragment = SQLFragment.of("SELECT ?", Arrays.asList(1, null, "a", null))
                .concat(SQLFragment.of("FROM ?", Arrays.asList(5, 6)));
        assertEquals(fragment.parameters(), Arrays.asList(1, null, "a", null, 5, 6));
    }

    @Test
    public void joinsWithSeparator() {
        var fragment1 = SQLFragment.of("SELECT ?", Arrays.asList(1, null));
        var fragment2 = SQLFragment.of("FROM ?", Arrays.asList("a", null));

        var combinedFragment = SQLFragment.join(" ", Arrays.asList(fragment1, fragment2));

        assertEquals(combinedFragment.sql(), "SELECT ? FROM ?");
        assertEquals(combinedFragment.parameters(), Arrays.asList(1, null, "a", null));
    }

    @Test
    public void joinsWithEmptySeparator() {
        var fragment1 = SQLFragment.of("SELECT ?", Arrays.asList("a", 1));
        var fragment2 = SQLFragment.of("FROM ?", Arrays.asList(5, null));
        var fragment3 = SQLFragment.of("WHERE ?", Arrays.asList(6));

        var combinedFragment = SQLFragment.join("", Arrays.asList(fragment1, fragment2, fragment3));

        assertEquals(combinedFragment.sql(), "SELECT ?FROM ?WHERE ?");
        assertEquals(combinedFragment.parameters(), Arrays.asList("a", 1, 5, null, 6));
    } 
}
