package com.fintech.algotrading.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextLoggerTest {

    @Test
    public void testSetAndGetDirectory() {
        // Arrange
        TextLogger textLogger = new TextLogger();
        String expectedDirectory = "/var/logs";

        // Act
        textLogger.setDirectory(expectedDirectory);
        String actualDirectory = textLogger.getDirectory();

        // Assert
        assertEquals(expectedDirectory, actualDirectory, "The directory value is not as expected.");
    }

    @Test
    public void testSetAndGetFilename() {
        // Arrange
        TextLogger textLogger = new TextLogger();
        String expectedFilename = "application";

        // Act
        textLogger.setFilename(expectedFilename);
        String actualFilename = textLogger.getFilename();

        // Assert
        assertEquals(expectedFilename, actualFilename, "The filename value is not as expected.");
    }

    @Test
    public void testSetAndGetFileExtension() {
        // Arrange
        TextLogger textLogger = new TextLogger();
        String expectedFileExtension = ".log";

        // Act
        textLogger.setFileExtension(expectedFileExtension);
        String actualFileExtension = textLogger.getFileExtension();

        // Assert
        assertEquals(expectedFileExtension, actualFileExtension, "The file extension value is not as expected.");
    }
}
