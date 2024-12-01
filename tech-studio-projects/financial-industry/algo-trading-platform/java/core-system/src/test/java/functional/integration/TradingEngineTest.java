package functional.integration;

import com.system.algotrading.engine.TradingEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TradingEngineTest verifies the functionality and integration of the TradingEngine component.
 * It ensures that the engine correctly processes orders, integrates with market data, and handles edge cases.
 */
public class TradingEngineTest {

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
     * Test to verify that the TradingEngine correctly processes a buy order.
     * Ensures that the order execution process integrates with market data.
     */
    @Test
    public void testProcessBuyOrder() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a buy order for AAPL stock
        boolean orderExecuted = tradingEngine.processOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "Buy order should be executed successfully.");

        // Simulate checking if the order is properly logged or processed
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals("AAPL", executedOrder.getSymbol(), "The executed order should be for AAPL.");
        assertEquals(100, executedOrder.getQuantity(), "The executed order quantity should be 100.");
        assertEquals(150.0, executedOrder.getPrice(), "The executed order price should be 150.0.");
    }

    /**
     * Test to verify that the TradingEngine correctly processes a sell order.
     * Ensures that sell orders are correctly executed with the current market price.
     */
    @Test
    public void testProcessSellOrder() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a sell order for AAPL stock
        boolean orderExecuted = tradingEngine.processOrder("AAPL", 50, "sell", 150.0);
        assertTrue(orderExecuted, "Sell order should be executed successfully.");

        // Simulate checking if the order is properly logged or processed
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals("AAPL", executedOrder.getSymbol(), "The executed order should be for AAPL.");
        assertEquals(50, executedOrder.getQuantity(), "The executed order quantity should be 50.");
        assertEquals(150.0, executedOrder.getPrice(), "The executed order price should be 150.0.");
    }

    /**
     * Test to verify that the TradingEngine handles a scenario where there are insufficient funds for a buy order.
     * It ensures that the system prevents the order from being placed if there is not enough balance.
     */
    @Test
    public void testInsufficientFundsForBuyOrder() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a buy order that exceeds available funds
        boolean orderExecuted = tradingEngine.processOrder("AAPL", 1000, "buy", 150.0);
        assertFalse(orderExecuted, "Buy order should be rejected due to insufficient funds.");
    }

    /**
     * Test to verify that the TradingEngine correctly handles invalid order types.
     * It ensures that invalid orders (e.g., unknown order types) are rejected by the engine.
     */
    @Test
    public void testInvalidOrderType() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing an invalid order type
        boolean orderExecuted = tradingEngine.processOrder("AAPL", 100, "invalidOrderType", 150.0);
        assertFalse(orderExecuted, "Invalid order type should be rejected.");
    }

    /**
     * Test to verify that the TradingEngine correctly handles a scenario where there is insufficient quantity for a sell order.
     * It ensures that sell orders are rejected if the user doesn't own enough shares to sell.
     */
    @Test
    public void testInsufficientSharesForSellOrder() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a sell order without enough shares
        boolean orderExecuted = tradingEngine.processOrder("AAPL", 100, "sell", 150.0);
        assertFalse(orderExecuted, "Sell order should be rejected due to insufficient shares.");
    }

    /**
     * Test to verify that the TradingEngine properly processes multiple orders in sequence.
     * Ensures that the engine can handle multiple orders and correctly update the system state.
     */
    @Test
    public void testMultipleOrderProcessing() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing multiple orders
        boolean buyOrderExecuted = tradingEngine.processOrder("AAPL", 100, "buy", 150.0);
        boolean sellOrderExecuted = tradingEngine.processOrder("AAPL", 50, "sell", 150.0);

        assertTrue(buyOrderExecuted, "Buy order should be executed successfully.");
        assertTrue(sellOrderExecuted, "Sell order should be executed successfully.");

        // Simulate checking the last executed order
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
    }

    /**
     * Cleans up the trading engine environment after each test.
     * Ensures the system is ready for the next test without stale data.
     */
    @AfterEach
    public void tearDown() {
        tradingEngine = null;
        orderExecution = null;
        marketDataProcessor = null;
    }
}
