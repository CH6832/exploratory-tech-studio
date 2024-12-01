package functional.acceptance;

import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.strategy.TradingStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * UATTest verifies the functionality of the trading engine during User Acceptance Testing (UAT).
 * UAT focuses on validating whether the system meets the customer's or end-user's requirements,
 * ensuring that the application behaves as expected from a user’s perspective.
 */
public class UATTest {

    private TradingEngine tradingEngine;
    private OrderExecution orderExecution;
    private MarketDataProcessor marketDataProcessor;

    /**
     * Sets up the necessary components before each test.
     * Initializes the TradingEngine, OrderExecution, and MarketDataProcessor for testing.
     */
    @BeforeEach
    public void setup() {
        marketDataProcessor = new MarketDataProcessor(); // Simulate market data processing
        orderExecution = new OrderExecution(); // Simulate order execution logic
        tradingEngine = new TradingEngine(orderExecution, (TradingStrategy) marketDataProcessor); // Integrating components into TradingEngine
    }

    /**
     * Test to verify that the trading engine correctly places an order according to the user's input.
     * Simulates a user placing a buy order for a stock and ensures it is processed correctly.
     */
    @Test
    public void testUserCanPlaceOrderSuccessfully() {
        // Simulate receiving market data for AAPL
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market data for AAPL stock

        // Simulate user placing a buy order
        boolean orderExecuted = tradingEngine.placeOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "User should be able to place the buy order successfully.");
    }

    /**
     * Test to verify that the trading engine rejects invalid user input.
     * Simulates a user attempting to place an invalid order, such as ordering more shares than available.
     */
    @Test
    public void testUserCannotPlaceInvalidOrder() {
        // Simulate receiving market data for AAPL
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Simulate user attempting to place an order with an invalid quantity (e.g., negative quantity)
        boolean invalidOrderExecuted = tradingEngine.placeOrder("AAPL", -100, "buy", 150.0);
        assertFalse(invalidOrderExecuted, "User should not be able to place an order with invalid quantity.");
    }

    /**
     * Test to verify that the trading engine allows the user to view the status of their orders.
     * This simulates a user querying the status of an order placed on the platform.
     */
    @Test
    public void testUserCanViewOrderStatus() {
        // Simulate receiving market data for AAPL
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Simulate user placing a buy order
        boolean orderExecuted = tradingEngine.placeOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "User should be able to place the buy order successfully.");

        // Simulate user viewing the status of the placed order
        String orderStatus = tradingEngine.getOrderStatus("AAPL", 100);
        assertEquals("Executed", orderStatus, "User should be able to view the order status as 'Executed'.");
    }

    /**
     * Test to verify that the trading engine gives appropriate error messages for failed orders.
     * This ensures that the system communicates with users effectively when an error occurs.
     */
    @Test
    public void testUserReceivesErrorMessageForFailedOrder() {
        // Simulate receiving market data for AAPL
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Simulate user attempting to place an order with insufficient funds (invalid scenario)
        boolean invalidOrderExecuted = tradingEngine.placeOrder("AAPL", 100, "buy", 1500.0); // Larger than user's balance
        assertFalse(invalidOrderExecuted, "User should not be able to place an order if they don't have sufficient funds.");

        // Simulate user receiving an error message
        String errorMessage = tradingEngine.getLastErrorMessage();
        assertEquals("Insufficient funds for the order.", errorMessage, "User should receive an error message for insufficient funds.");
    }

    /**
     * Test to verify that the trading engine provides real-time market updates for the user.
     * This ensures that the user has access to the latest market information for making trading decisions.
     */
    @Test
    public void testUserReceivesRealTimeMarketUpdates() {
        // Simulate initial market data for AAPL
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Simulate user viewing market data for AAPL
        double currentPrice = tradingEngine.getMarketPrice("AAPL");
        assertEquals(150.0, currentPrice, "User should see the correct market price for AAPL.");

        // Simulate market price update
        marketDataProcessor.updateMarketData("AAPL", 155.0);
        double updatedPrice = tradingEngine.getMarketPrice("AAPL");
        assertEquals(155.0, updatedPrice, "User should receive updated market data when prices change.");
    }

    /**
     * Test to verify that the trading engine handles user preferences correctly.
     * This ensures that the system supports customizations or preferences set by the user, such as preferred trading hours.
     */
    @Test
    public void testUserCanSetTradingPreferences() {
        // Simulate user setting their preferred trading hours
        tradingEngine.setUserTradingPreference("preferredTradingHours", "9:00 AM - 4:00 PM");

        // Simulate user retrieving their set preference
        String tradingPreference = tradingEngine.getUserTradingPreference("preferredTradingHours");
        assertEquals("9:00 AM - 4:00 PM", tradingPreference, "User should be able to set and retrieve their preferred trading hours.");
    }

    /**
     * Tears down the TradingEngine, OrderExecution, and MarketDataProcessor instances after each test.
     */
    @AfterEach
    public void tearDown() {
        tradingEngine = null;
        orderExecution = null;
        marketDataProcessor = null;
    }
}
