package functional.integration;

import com.system.algotrading.market.MarketFeedHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MarketFeedHandlerTest validates the functionality of the MarketFeedHandler class.
 * It ensures that market data feeds are correctly received and processed by the handler.
 */
public class MarketFeedHandlerTest {

    private MarketFeedHandler marketFeedHandler;

    /**
     * Initializes the necessary components before each test.
     * Creates a fresh instance of the MarketFeedHandler before each test.
     */
    @BeforeEach
    public void setup() {
        marketFeedHandler = new MarketFeedHandler(); // Create a new instance of the MarketFeedHandler
    }

    /**
     * Verifies that the MarketFeedHandler correctly processes a market data feed.
     * The handler should update the market data in the system.
     */
    @Test
    public void testProcessMarketData() {
        // Simulate receiving a market feed for AAPL
        String marketDataFeed = "AAPL,150.0";
        marketFeedHandler.processMarketData(marketDataFeed);

        // Assert that the market data for AAPL is processed and updated
        double price = marketFeedHandler.getMarketPrice("AAPL");
        assertEquals(150.0, price, "The price for AAPL should be updated to 150.0.");
    }

    /**
     * Tests the scenario where invalid market data is received.
     * The system should handle invalid feeds gracefully.
     */
    @Test
    public void testInvalidMarketData() {
        // Simulate receiving invalid market data (incorrect format)
        String invalidMarketData = "INVALID_DATA";
        boolean result = marketFeedHandler.processMarketData(invalidMarketData);

        // Assert that invalid data is not processed and returns false
        assertFalse(result, "Invalid market data should return false.");
    }

    /**
     * Verifies that the MarketFeedHandler processes multiple market feeds correctly.
     * The handler should update multiple symbols without conflicts.
     */
    @Test
    public void testProcessMultipleMarketData() {
        // Simulate receiving market data for multiple symbols
        String feedAAPL = "AAPL,150.0";
        String feedGOOG = "GOOG,2800.0";
        marketFeedHandler.processMarketData(feedAAPL);
        marketFeedHandler.processMarketData(feedGOOG);

        // Assert that both market data are processed and updated correctly
        double priceAAPL = marketFeedHandler.getMarketPrice("AAPL");
        double priceGOOG = marketFeedHandler.getMarketPrice("GOOG");

        assertEquals(150.0, priceAAPL, "The price for AAPL should be updated to 150.0.");
        assertEquals(2800.0, priceGOOG, "The price for GOOG should be updated to 2800.0.");
    }

    /**
     * Verifies that the MarketFeedHandler correctly handles empty market data feeds.
     * An empty feed should be ignored or result in an error.
     */
    @Test
    public void testEmptyMarketDataFeed() {
        // Simulate receiving an empty market data feed
        String emptyFeed = "";
        boolean result = marketFeedHandler.processMarketData(emptyFeed);

        // Assert that an empty feed returns false as it cannot be processed
        assertFalse(result, "An empty market data feed should return false.");
    }

    /**
     * Verifies that the MarketFeedHandler ignores outdated or duplicate market data.
     * The latest market data should overwrite any previously received data for the same symbol.
     */
    @Test
    public void testDuplicateMarketDataHandling() {
        // Simulate receiving duplicate market data for AAPL
        String feedAAPL1 = "AAPL,150.0";
        String feedAAPL2 = "AAPL,155.0";
        marketFeedHandler.processMarketData(feedAAPL1);
        marketFeedHandler.processMarketData(feedAAPL2);

        // Assert that the most recent price for AAPL (155.0) is retained
        double priceAAPL = marketFeedHandler.getMarketPrice("AAPL");
        assertEquals(155.0, priceAAPL, "The price for AAPL should be updated to the latest value of 155.0.");
    }

    /**
     * Verifies that the MarketFeedHandler correctly processes feeds with missing data.
     * For example, the market data might be missing the symbol or price.
     */
    @Test
    public void testMissingDataInMarketFeed() {
        // Simulate receiving incomplete market data (missing price)
        String missingPriceFeed = "AAPL,";
        boolean result = marketFeedHandler.processMarketData(missingPriceFeed);

        // Assert that incomplete data is rejected and not processed
        assertFalse(result, "Market data with missing information should not be processed.");
    }

    /**
     * Cleans up the resources after each test.
     * Ensures that the system is ready for the next test, with no leftover state.
     */
    @AfterEach
    public void tearDown() {
        marketFeedHandler.clearMarketData(); // Ensure all market data is cleared after each test
    }
}
