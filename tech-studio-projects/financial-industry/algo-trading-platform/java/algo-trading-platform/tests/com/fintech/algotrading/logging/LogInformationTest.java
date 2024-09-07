package com.fintech.algotrading.logging;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

public class LogInformationTest {

    @Test
    public void testLogInformationInitialization() {
        // Arrange
        LogLevel logLevel = LogLevel.ERROR;
        String module = "TestModule";
        Instant now = Instant.now();
        int threadId = (int) Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        
        // Act
        LogInformation logInformation = new LogInformation(logLevel, module, now, threadId, threadName);

        // Assert
        assertEquals(logLevel, logInformation.getLogLevel(), "LogLevel should match.");
        assertEquals(module, logInformation.getModule(), "Module name should match.");
        assertEquals(now, logInformation.getNow(), "Instant time should match.");
        assertEquals(threadId, logInformation.getThreadId(), "Thread ID should match.");
        assertEquals(threadName, logInformation.getThreadName(), "Thread name should match.");
    }
}
