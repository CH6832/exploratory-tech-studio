package com.fintech.algotrading.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggingConfigurationTest {

    @Test
    public void testSetAndGetTextLoggerConfiguration() {
        // Arrange
        LoggingConfiguration loggingConfig = new LoggingConfiguration();
        TextLogger expectedTextLogger = new TextLogger(); // Create a dummy TextLogger object or mock if necessary

        // Act
        loggingConfig.setTextLoggerConfiguration(expectedTextLogger);
        TextLogger actualTextLogger = loggingConfig.getTextLoggerConfiguration();

        // Assert
        assertNotNull(actualTextLogger, "TextLoggerConfiguration should not be null");
        assertEquals(expectedTextLogger, actualTextLogger, "The retrieved TextLoggerConfiguration is not as expected");
    }
}
