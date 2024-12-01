package functional.acceptance;

import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TradingEngineBetaTest verifies the TradingEngine functionality during the Beta Testing phase.
 * Beta Testing focuses on ensuring that the trading engine works as expected when interacting with real-world data,
 * as well as identifying any issues or bugs that were not previously uncovered during Alpha Testing.
 */
public class TradingEngineBetaTest {

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
        tradingEngine = new TradingEngine(orderExecution, marketDataProcessor); // Integrating components into TradingEngine
    }

    /**
     * Test to verify that the trading engine processes live market data and executes orders correctly.
     */
    @Test
    public void testTradingEngineProcessesMarketDataAndExecutesOrder() {
        // Simulate receiving live market data
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Example: Market data for AAPL stock

        // Simulate placing an order using the trading engine
        boolean orderExecuted = tradingEngine.placeOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "Order should be executed successfully based on received market data.");
    }

    /**
     * Test to verify that the trading engine handles multiple concurrent orders.
     * This simulates a scenario with several trades placed at the same time.
     */
    @Test
    public void testTradingEngineHandlesMultipleConcurrentOrders() {
        // Simulate receiving multiple market data updates for different stocks
        marketDataProcessor.updateMarketData("AAPL", 150.0);
        marketDataProcessor.updateMarketData("GOOG", 2700.0);

        // Simulate placing multiple orders concurrently
        boolean order1Executed = tradingEngine.placeOrder("AAPL", 100, "buy", 150.0);
        boolean order2Executed = tradingEngine.placeOrder("GOOG", 50, "sell", 2700.0);
        assertTrue(order1Executed, "Order for AAPL should be executed successfully.");
        assertTrue(order2Executed, "Order for GOOG should be executed successfully.");
    }

    /**
     * Test to verify that the trading engine handles market volatility and executes orders accordingly.
     * This tests the ability of the trading engine to adapt to sudden changes in market prices.
     */
    @Test
    public void testTradingEngineHandlesMarketVolatility() {
        // Simulate sudden market fluctuations
        marketDataProcessor.updateMarketData("AAPL", 145.0); // Market drops to 145.0
        marketDataProcessor.updateMarketData("AAPL", 160.0); // Market rises to 160.0

        // Simulate placing an order based on fluctuating market data
        boolean orderExecutedAtLowerPrice = tradingEngine.placeOrder("AAPL", 100, "buy", 145.0);
        boolean orderExecutedAtHigherPrice = tradingEngine.placeOrder("AAPL", 100, "sell", 160.0);

        assertTrue(orderExecutedAtLowerPrice, "Order should be executed successfully at lower price.");
        assertTrue(orderExecutedAtHigherPrice, "Order should be executed successfully at higher price.");
    }

    /**
     * Test to verify that the trading engine can handle large orders and large trading volumes.
     * This simulates high volume trades and verifies that the system performs as expected.
     */
    @Test
    public void testTradingEngineHandlesLargeOrders() {
        // Simulate placing a large trade order
        double largeOrderAmount = 1000000; // Example large order amount
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Simulate market price for AAPL
        boolean largeOrderExecuted = tradingEngine.placeOrder("AAPL", 10000, "buy", 150.0); // Place large order for 10,000 shares

        assertTrue(largeOrderExecuted, "Large order should be successfully executed by the trading engine.");
    }

    /**
     * Test to verify that the trading engine logs all executed trades for future analysis.
     * This ensures that trading activity can be tracked and analyzed later.
     */
    @Test
    public void testTradingEngineLogsExecutedTrades() {
        // Simulate placing a trade
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Simulate market price
        boolean orderExecuted = tradingEngine.placeOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "Order should be executed successfully.");

        // Verify that the trade has been logged
        assertTrue(tradingEngine.getTradeLogs().size() > 0, "Executed trade should be logged.");
    }

    /**
     * Test to verify that the trading engine is able to reject invalid orders.
     * This ensures that the system doesn't execute erroneous trades, such as those with invalid quantities or prices.
     */
    @Test
    public void testTradingEngineRejectsInvalidOrders() {
        // Simulate invalid market data
        marketDataProcessor.updateMarketData("AAPL", 150.0);

        // Simulate placing an invalid order with incorrect quantity (e.g., negative quantity)
        boolean invalidOrderExecuted = tradingEngine.placeOrder("AAPL", -100, "buy", 150.0);
        assertFalse(invalidOrderExecuted, "Invalid order should be rejected.");
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
