package dev.mccue.jdbc.test;

import dev.mccue.jdbc.SQLFragment;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLFragmentTest {
    @Test
    public void allowsNullParams() {
        var fragment = SQLFragment.of("SELECT ?", 1, null, "a", null);
        assertEquals(fragment.parameters(), Arrays.asList(1, null, "a", null));
    }

    @Test
    public void concatsInOrder() {
        var fragment = SQLFragment.of("SELECT ?", 1, null, "a", null)
                .concat(SQLFragment.of("FROM ?", 5, 6));
        assertEquals(fragment.parameters(), Arrays.asList(1, null, "a", null, 5, 6));
    }
}
