package com.fintech.algotrading.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Concrete implementation of AbstractLogger for testing purposes
class TestLogger extends AbstractLogger {

    private final List<String> logMessages = new ArrayList<>();

    @Override
    protected void log(LogLevel level, String module, String message) {
        // Store the log messages in a list for verification
        logMessages.add(level + " - " + module + ": " + message);
    }

    public List<String> getLogMessages() {
        return logMessages;
    }

	@Override
	public void logInformation(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logWarning(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logError(String message) {
		// TODO Auto-generated method stub
		
	}
}

// Test class for AbstractLogger
public class AbstractLoggerTest {

    private TestLogger logger;

    @BeforeEach
    void setUp() {
        logger = new TestLogger();
    }

    @Test
    void testDebugWithMessage() {
        String module = "TestModule";
        String message = "Debug message";
        logger.debug(module, message);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.DEBUG.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(message));
    }

    @Test
    void testDebugWithException() {
        String module = "TestModule";
        Exception exception = new Exception("Debug exception");
        logger.debug(module, exception);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.DEBUG.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(exception.getMessage()));
    }

    @Test
    void testInformationWithMessage() {
        String module = "TestModule";
        String message = "Information message";
        logger.information(module, message);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.INFORMATION.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(message));
    }

    @Test
    void testInformationWithException() {
        String module = "TestModule";
        Exception exception = new Exception("Information exception");
        logger.information(module, exception);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.INFORMATION.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(exception.getMessage()));
    }

    @Test
    void testWarningWithMessage() {
        String module = "TestModule";
        String message = "Warning message";
        logger.warning(module, message);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.WARNING.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(message));
    }

    @Test
    void testWarningWithException() {
        String module = "TestModule";
        Exception exception = new Exception("Warning exception");
        logger.warning(module, exception);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.WARNING.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(exception.getMessage()));
    }

    @Test
    void testErrorWithMessage() {
        String module = "TestModule";
        String message = "Error message";
        logger.error(module, message);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.ERROR.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(message));
    }

    @Test
    void testErrorWithException() {
        String module = "TestModule";
        Exception exception = new Exception("Error exception");
        logger.error(module, exception);

        List<String> logMessages = logger.getLogMessages();
        assertEquals(1, logMessages.size());
        assertTrue(logMessages.get(0).contains(LogLevel.ERROR.toString()));
        assertTrue(logMessages.get(0).contains(module));
        assertTrue(logMessages.get(0).contains(exception.getMessage()));
    }
}
