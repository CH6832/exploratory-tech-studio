package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchResultTest {

    @Test
    public void testMatchResultSuccess() {
        // Arrange
        boolean expectedSuccess = true;
        String expectedDetails = "Order matched successfully";

        // Act
        MatchResult result = new MatchResult(expectedSuccess, expectedDetails);

        // Assert
        assertTrue(result.isSuccess(), "The success status should be true.");
        assertEquals(expectedDetails, result.getDetails(), "The details should match the expected string.");
    }

    @Test
    public void testMatchResultFailure() {
        // Arrange
        boolean expectedSuccess = false;
        String expectedDetails = "Order match failed";

        // Act
        MatchResult result = new MatchResult(expectedSuccess, expectedDetails);

        // Assert
        assertFalse(result.isSuccess(), "The success status should be false.");
        assertEquals(expectedDetails, result.getDetails(), "The details should match the expected string.");
    }
}
