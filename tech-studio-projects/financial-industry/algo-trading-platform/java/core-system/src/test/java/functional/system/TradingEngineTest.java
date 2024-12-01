package systemtests;

import com.system.algotrading.engine.TradingEngine;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TradingEngineTest validates that the TradingEngine component of the trading system is functioning correctly.
 * This test suite includes several basic operations to ensure the core trading logic is intact.
 */
public class TradingEngineTest {

    private TradingEngine tradingEngine;

    /**
     * Setup the environment for trading engine tests.
     * Initializes the TradingEngine to test its core functionalities.
     */
    public TradingEngineTest() {
        this.tradingEngine = new TradingEngine();
    }

    /**
     * Test to ensure the TradingEngine can process a simple order.
     * This validates that the engine processes orders correctly without errors.
     */
    @Test
    public void testProcessOrder() {
        // Create a simple order for testing
        Order order = new Order("AAPL", 100, 150.0);

        // Process the order through the trading engine
        boolean result = tradingEngine.processOrder(order);

        // Assert that the order was processed successfully
        assertTrue(result, "The trading engine should process orders correctly.");
    }

    /**
     * Test to verify that the TradingEngine rejects invalid orders.
     * This validates that invalid or malformed orders are properly handled.
     */
    @Test
    public void testRejectInvalidOrder() {
        // Create an invalid order (e.g., negative quantity)
        Order invalidOrder = new Order("AAPL", -100, 150.0);

        // Try to process the invalid order
        boolean result = tradingEngine.processOrder(invalidOrder);

        // Assert that the invalid order is rejected
        assertFalse(result, "The trading engine should reject invalid orders.");
    }

    /**
     * Test to verify that the TradingEngine can handle multiple orders in sequence.
     * This tests the system's ability to handle order flow over time.
     */
    @Test
    public void testMultipleOrderProcessing() {
        // Create multiple orders to process
        Order order1 = new Order("AAPL", 100, 150.0);
        Order order2 = new Order("GOOG", 50, 2000.0);
        Order order3 = new Order("AMZN", 200, 1800.0);

        // Process the orders through the trading engine
        boolean result1 = tradingEngine.processOrder(order1);
        boolean result2 = tradingEngine.processOrder(order2);
        boolean result3 = tradingEngine.processOrder(order3);

        // Assert that all orders are processed successfully
        assertTrue(result1, "The trading engine should process the first order correctly.");
        assertTrue(result2, "The trading engine should process the second order correctly.");
        assertTrue(result3, "The trading engine should process the third order correctly.");
    }

    /**
     * Test to verify that the TradingEngine can handle an order cancellation.
     * This tests the ability of the engine to correctly cancel orders.
     */
    @Test
    public void testCancelOrder() {
        // Create an order and process it
        Order order = new Order("AAPL", 100, 150.0);
        boolean orderProcessed = tradingEngine.processOrder(order);

        // Cancel the order
        boolean orderCancelled = tradingEngine.cancelOrder(order);

        // Assert that the order was processed and then cancelled successfully
        assertTrue(orderProcessed, "Order should be processed successfully.");
        assertTrue(orderCancelled, "Order should be cancelled successfully.");
    }
}
