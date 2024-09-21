package com.fintech.algotrading.logging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTypeTest {

    @Test
    public void testEnumConstants() {
        // Check that all enum constants are defined
        LoggerType[] expectedValues = {LoggerType.TEXT, LoggerType.DATABASE, LoggerType.TRACE, LoggerType.CONSOLE};
        LoggerType[] actualValues = LoggerType.values();

        assertEquals(expectedValues.length, actualValues.length, "Enum constants length mismatch");

        for (LoggerType expected : expectedValues) {
            boolean found = false;
            for (LoggerType actual : actualValues) {
                if (expected == actual) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Enum constant " + expected + " not found");
        }
    }

    @Test
    public void testEnumNames() {
        // Verify the names of the enum constants
        assertEquals("TEXT", LoggerType.TEXT.name(), "Name of TEXT enum constant mismatch");
        assertEquals("DATABASE", LoggerType.DATABASE.name(), "Name of DATABASE enum constant mismatch");
        assertEquals("TRACE", LoggerType.TRACE.name(), "Name of TRACE enum constant mismatch");
        assertEquals("CONSOLE", LoggerType.CONSOLE.name(), "Name of CONSOLE enum constant mismatch");
    }

    @Test
    public void testEnumOrdinals() {
        // Verify the ordinal values of the enum constants
        assertEquals(0, LoggerType.TEXT.ordinal(), "Ordinal of TEXT enum constant mismatch");
        assertEquals(1, LoggerType.DATABASE.ordinal(), "Ordinal of DATABASE enum constant mismatch");
        assertEquals(2, LoggerType.TRACE.ordinal(), "Ordinal of TRACE enum constant mismatch");
        assertEquals(3, LoggerType.CONSOLE.ordinal(), "Ordinal of CONSOLE enum constant mismatch");
    }

    @Test
    public void testEnumValueOf() {
        // Verify that the valueOf method returns the correct enum constant
        assertEquals(LoggerType.TEXT, LoggerType.valueOf("TEXT"), "valueOf TEXT enum constant mismatch");
        assertEquals(LoggerType.DATABASE, LoggerType.valueOf("DATABASE"), "valueOf DATABASE enum constant mismatch");
        assertEquals(LoggerType.TRACE, LoggerType.valueOf("TRACE"), "valueOf TRACE enum constant mismatch");
        assertEquals(LoggerType.CONSOLE, LoggerType.valueOf("CONSOLE"), "valueOf CONSOLE enum constant mismatch");
    }

    @Test
    public void testEnumToString() {
        // Verify the toString method
        assertEquals("TEXT", LoggerType.TEXT.toString(), "toString TEXT enum constant mismatch");
        assertEquals("DATABASE", LoggerType.DATABASE.toString(), "toString DATABASE enum constant mismatch");
        assertEquals("TRACE", LoggerType.TRACE.toString(), "toString TRACE enum constant mismatch");
        assertEquals("CONSOLE", LoggerType.CONSOLE.toString(), "toString CONSOLE enum constant mismatch");
    }
}
