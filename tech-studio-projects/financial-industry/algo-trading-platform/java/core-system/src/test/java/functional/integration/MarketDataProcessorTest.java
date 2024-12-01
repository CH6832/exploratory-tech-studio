package functional.integration;

import com.system.algotrading.data.MarketDataProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MarketDataProcessorTest verifies the functionality of the MarketDataProcessor component.
 * It ensures that market data is correctly received, processed, and made available for further trading operations.
 */
public class MarketDataProcessorTest {

    private MarketDataProcessor marketDataProcessor;

    /**
     * Sets up the necessary component before each test.
     * Initializes the MarketDataProcessor for testing.
     */
    @BeforeEach
    public void setup() {
        marketDataProcessor = new MarketDataProcessor(); // Initialize the MarketDataProcessor instance
    }

    /**
     * Test to verify that the MarketDataProcessor correctly updates the market price for a given symbol.
     * This ensures that the price is updated and can be retrieved.
     */
    @Test
    public void testUpdateMarketData() {
        // Simulate updating the market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Assert that the market price for AAPL is updated correctly
        double price = marketDataProcessor.getMarketPrice("AAPL");
        assertEquals(150.0, price, "The market price for AAPL should be 150.0.");
    }

    /**
     * Test to verify that the MarketDataProcessor handles multiple market updates correctly.
     * Ensures that multiple symbols are processed without overwriting or losing data.
     */
    @Test
    public void testUpdateMultipleMarketData() {
        // Simulate updating the market data for multiple symbols
        marketDataProcessor.updateMarketData("AAPL", 150.0);
        marketDataProcessor.updateMarketData("GOOG", 2800.0);

        // Assert that both market prices are updated correctly
        double aaplPrice = marketDataProcessor.getMarketPrice("AAPL");
        double googPrice = marketDataProcessor.getMarketPrice("GOOG");

        assertEquals(150.0, aaplPrice, "The market price for AAPL should be 150.0.");
        assertEquals(2800.0, googPrice, "The market price for GOOG should be 2800.0.");
    }

    /**
     * Test to verify that the MarketDataProcessor correctly handles invalid symbols.
     * Ensures that the system does not process market data for non-existent symbols.
     */
    @Test
    public void testInvalidSymbolHandling() {
        // Attempt to retrieve market price for a non-existent symbol
        double price = marketDataProcessor.getMarketPrice("XYZ"); // XYZ is an invalid symbol

        // Assert that the price for an invalid symbol returns -1.0 (or some default error value)
        assertEquals(-1.0, price, "The market price for an invalid symbol should return -1.0.");
    }

    /**
     * Test to verify that the MarketDataProcessor correctly handles market price updates for the same symbol.
     * This ensures that the latest price is always retained.
     */
    @Test
    public void testUpdateMarketDataForSameSymbol() {
        // Simulate updating the market data for AAPL twice
        marketDataProcessor.updateMarketData("AAPL", 150.0);
        marketDataProcessor.updateMarketData("AAPL", 155.0);

        // Assert that the price for AAPL is updated to the latest value (155.0)
        double price = marketDataProcessor.getMarketPrice("AAPL");
        assertEquals(155.0, price, "The market price for AAPL should be updated to 155.0.");
    }

    /**
     * Test to verify that the MarketDataProcessor returns an empty price for symbols that have no data.
     * This ensures that the system can gracefully handle symbols with no data updates.
     */
    @Test
    public void testEmptyPriceForSymbolsWithoutData() {
        // Assert that an empty symbol returns the default value (-1.0)
        double price = marketDataProcessor.getMarketPrice("AMZN");
        assertEquals(-1.0, price, "The market price for a symbol with no data should return -1.0.");
    }

    /**
     * Test to verify that the MarketDataProcessor correctly handles the clearing of market data.
     * Ensures that data is cleared correctly, preventing stale or outdated prices from being used.
     */
    @Test
    public void testClearMarketData() {
        // Simulate updating market data for AAPL and then clearing the data
        marketDataProcessor.updateMarketData("AAPL", 150.0);
        marketDataProcessor.clearMarketData();

        // Assert that the price for AAPL is cleared and returns the default value (-1.0)
        double price = marketDataProcessor.getMarketPrice("AAPL");
        assertEquals(-1.0, price, "The market price for AAPL should be cleared and return -1.0.");
    }

    /**
     * Cleans up the market data processor environment after each test.
     * Ensures the system is ready for the next test without stale data.
     */
    @AfterEach
    public void tearDown() {
        marketDataProcessor.clearMarketData(); // Ensure all market data is cleared after each test
    }
}
