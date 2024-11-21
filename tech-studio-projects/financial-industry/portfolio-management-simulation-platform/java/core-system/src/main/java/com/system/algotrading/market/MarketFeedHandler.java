package com.system.algotrading.market;

import com.system.algotrading.data.MarketDataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The MarketFeedHandler class is responsible for managing the connection to the market data feed.
 * It handles the reception of market data and forwards it to the MarketDataProcessor for processing.
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
        this.dataProcessor = dataProcessor;
    }

    /**
     * Starts the market data feed and processes incoming data by sending it to the data processor.
     * This method simulates receiving data in a real-time market environment.
     */
    public void startFeed() {
        try {
            // Log that the market data feed has started
            logger.info("Starting market data feed");

            // Simulated market data, as a placeholder for real-time data reception
            String sampleData = "sample_market_data";

            // Process the sample market data using the MarketDataProcessor instance
            dataProcessor.processMarketData(sampleData);

        } catch (Exception e) {
            // Log any errors encountered during feed processing
            logger.error("Market feed handler encountered an error", e);
        }
    }
}
