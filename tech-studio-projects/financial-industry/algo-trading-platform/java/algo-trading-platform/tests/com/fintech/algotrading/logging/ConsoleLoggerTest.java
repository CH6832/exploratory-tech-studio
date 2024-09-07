package com.fintech.algotrading.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleLoggerTest {

    private ConsoleLogger logger;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        logger = new ConsoleLogger();
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testDebugWithMessage() {
        String module = "Module";
        String message = "Debug message";
        logger.debug(module, message);
        assertEquals("[DEBUG] [" + module + "] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDebugWithException() {
        String module = "Module";
        Exception exception = new Exception("Debug exception");
        logger.debug(module, exception);
        assertEquals("[DEBUG] [" + module + "] " + exception.getMessage() + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInformationWithMessage() {
        String module = "Module";
        String message = "Information message";
        logger.information(module, message);
        assertEquals("[INFO] [" + module + "] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInformationWithException() {
        String module = "Module";
        Exception exception = new Exception("Information exception");
        logger.information(module, exception);
        assertEquals("[INFO] [" + module + "] " + exception.getMessage() + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWarningWithMessage() {
        String module = "Module";
        String message = "Warning message";
        logger.warning(module, message);
        assertEquals("[WARNING] [" + module + "] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWarningWithException() {
        String module = "Module";
        Exception exception = new Exception("Warning exception");
        logger.warning(module, exception);
        assertEquals("[WARNING] [" + module + "] " + exception.getMessage() + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testErrorWithMessage() {
        String module = "Module";
        String message = "Error message";
        logger.error(module, message);
        assertEquals("[ERROR] [" + module + "] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testErrorWithException() {
        String module = "Module";
        Exception exception = new Exception("Error exception");
        logger.error(module, exception);
        assertEquals("[ERROR] [" + module + "] " + exception.getMessage() + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLogInformation() {
        String message = "Log information message";
        logger.logInformation(message);
        assertEquals("[INFO] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLogWarning() {
        String message = "Log warning message";
        logger.logWarning(message);
        assertEquals("[WARNING] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLogError() {
        String message = "Log error message";
        logger.logError(message);
        assertEquals("[ERROR] " + message + System.lineSeparator(), outputStreamCaptor.toString().trim());
    }
}
