package com.system.algotrading.market;

import com.system.algotrading.data.MarketDataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The MarketFeedHandler class is responsible for managing the connection to the market data feed.
 * It handles the reception of market data and forwards it to the MarketDataProcessor for processing.
 * This class simulates the behavior of handling live market data feeds in a realistic format.
 */
public class MarketFeedHandler {

    // Logger instance for logging information and errors related to market feed handling
    private static final Logger logger = LoggerFactory.getLogger(MarketFeedHandler.class);

    // Dependency for processing the market data received from the feed
    private final MarketDataProcessor dataProcessor;

    /**
     * Constructor for MarketFeedHandler.
     *
     * @param dataProcessor An instance of MarketDataProcessor that processes incoming market data.
     */
    public MarketFeedHandler(MarketDataProcessor dataProcessor) {
        if (dataProcessor == null) {
            // If the MarketDataProcessor is not provided, log an error and throw an exception
            throw new IllegalArgumentException("MarketDataProcessor cannot be null");
        }
        this.dataProcessor = dataProcessor;
    }

    /**
     * Starts the market data feed and processes incoming data by sending it to the data processor.
     * This method simulates receiving market data in a real-time market environment.
     * In real applications, this would be replaced by actual data feed reception logic.
     */
    public void startFeed() {
        try {
            // Log that the market data feed has started
            logger.info("Starting market data feed");

            // Simulated market data in a more realistic format (e.g., stock symbols, prices, volume, timestamp)
            for (int i = 0; i < 100; i++) {
                String sampleData = generateSimulatedMarketData(i);
                // Log the simulated market data before processing
                logger.debug("Received market data: {}", sampleData);

                // Process the sample market data using the MarketDataProcessor instance
                String processedData = dataProcessor.processMarketData(sampleData);

                // After processing, log the result of the processed data
                logger.info("Processed market data: {}", processedData);
            }
        } catch (IllegalArgumentException e) {
            // Handle specific case of invalid market data (e.g., empty or null data)
            logger.error("Invalid market data encountered: {}", e.getMessage());
        } catch (Exception e) {
            // Log any unexpected errors encountered during feed processing
            logger.error("Market feed handler encountered an error: ", e);
        }
    }

    /**
     * Generates simulated market data for testing purposes.
     * This data represents a stock symbol, its price, volume traded, and the timestamp of the trade.
     *
     * @param index The index of the data entry (to generate unique data).
     * @return A string representing a simulated market data entry.
     */
    private String generateSimulatedMarketData(int index) {
        String[] symbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "NFLX", "NVDA", "META", "SPY", "MS", "INTC", "BABA"};
        Random random = new Random();

        // Randomly select a symbol
        String symbol = symbols[random.nextInt(symbols.length)];

        // Random price between 100 and 3500 (stock prices)
        double price = 50 + (random.nextDouble() * 1000); // Example: Stock price between 50 and 1000

        // Random volume between 100 and 10000
        int volume = 100 + random.nextInt(10000);

        // Generate a timestamp (e.g., current time + offset for each entry)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date now = new Date();
        long timeOffset = index * 1000L; // Increase each subsequent entry by 1 second
        Date timestamp = new Date(now.getTime() + timeOffset);

        // Format the simulated market data
        return String.format("%s,%.2f,%d,%s", symbol, price, volume, dateFormat.format(timestamp));
    }

    /**
     * Processes market data received from the market feed and forwards it to the MarketDataProcessor.
     *
     * @param marketDataFeed The raw market data to be processed (e.g., "AAPL,150.25,1000,2024-11-26T10:15:00Z").
     */
    public void processMarketData(String marketDataFeed) {
        try {
            // Validate that market data is not null or empty before processing
            if (marketDataFeed == null || marketDataFeed.isEmpty()) {
                throw new IllegalArgumentException("Market data feed is empty or null");
            }

            // Log the received data for transparency
            logger.debug("Processing received market data: {}", marketDataFeed);

            // Forward the data to the data processor for further handling
            String processedData = dataProcessor.processMarketData(marketDataFeed);

            // After processing, log the result
            logger.info("Successfully processed market data: {}", processedData);
        } catch (IllegalArgumentException e) {
            // Handle invalid market data, such as null or empty strings
            logger.error("Error processing market data: {}", e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions that occur during processing
            logger.error("Unexpected error during market data processing: ", e);
        }
    }
}
