package dev.mccue.jdbc.test;

import dev.mccue.jdbc.SettableParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SettableParameterTest {
    @Test
    public void ofBooleanGivesValueSemantics() {
        assertEquals(SettableParameter.ofBoolean(false), SettableParameter.ofBoolean(false));
        assertEquals(SettableParameter.ofBoolean(true), SettableParameter.ofBoolean(true));
        assertNotEquals(SettableParameter.ofBoolean(true), SettableParameter.ofBoolean(false));
    }
}
