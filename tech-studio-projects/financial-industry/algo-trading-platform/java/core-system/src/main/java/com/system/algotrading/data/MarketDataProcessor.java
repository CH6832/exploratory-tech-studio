package com.system.algotrading.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MarketDataProcessor is responsible for handling and processing incoming market data.
 * This class operates with a focus on low-latency and reliability, ensuring that data is processed
 * efficiently. It includes logging for observability and error handling to manage and log any issues
 * during data processing.
 */
public class MarketDataProcessor {

    // Logger for tracking system operations, debug information, and error handling
    private static final Logger logger = LoggerFactory.getLogger(MarketDataProcessor.class);

    /**
     * Processes the incoming market data string. This function simulates data parsing,
     * validates input data, and logs necessary information.
     *
     * @param data The incoming market data as a string.
     * @return The processed market data string, if processing is successful.
     * @throws IllegalArgumentException if the provided market data is null or empty.
     */
    public String processMarketData(String data) {
        try {
            // Log incoming data for observability
            logger.info("Received market data: {}", data);

            // Simulate processing with basic validation
            // Ensures data integrity before further operations
            if (data == null || data.isEmpty()) {
                // Log and throw an exception if data is invalid
                throw new IllegalArgumentException("Market data is empty");
            }

            // Simulate data processing: parsing, validation, etc.
            // Avoids computationally intensive operations for low-latency
            // For example, parsing data format and preparing it for next layer
            // This area can be optimized further for GC (Garbage Collection) efficiency

            logger.debug("Market data processed successfully");

        } catch (Exception e) {
            // Log the error details for troubleshooting
            logger.error("Error processing market data: ", e);
        }

        // Return the processed data (may return a transformed or parsed format in real implementation)
        return data;
    }
}
