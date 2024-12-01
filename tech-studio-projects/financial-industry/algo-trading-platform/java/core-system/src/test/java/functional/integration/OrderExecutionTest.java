package functional.integration;

import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderExecutionTest verifies the correctness and functionality of the order execution component in the trading engine.
 * It ensures that the system correctly handles the execution of buy and sell orders.
 */
public class OrderExecutionTest {

    private OrderExecution orderExecution;
    private TradingEngine tradingEngine;
    private MarketDataProcessor marketDataProcessor;

    /**
     * Sets up the necessary components before each test.
     * Initializes the OrderExecution, TradingEngine, and MarketDataProcessor for testing.
     */
    @BeforeEach
    public void setup() {
        marketDataProcessor = new MarketDataProcessor(); // Simulate market data processing
        orderExecution = new OrderExecution(); // Simulate order execution logic
        tradingEngine = new TradingEngine(orderExecution, marketDataProcessor); // Integrating components into TradingEngine
    }

    /**
     * Test to verify that the system executes a buy order correctly.
     * It ensures that the order is processed and the expected result occurs.
     */
    @Test
    public void testBuyOrderExecution() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a buy order for AAPL stock
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 100, "buy", 150.0);
        assertTrue(orderExecuted, "Buy order should be executed successfully.");

        // Simulate checking if the order is properly logged or processed
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals("AAPL", executedOrder.getSymbol(), "The executed order should be for AAPL.");
        assertEquals(100, executedOrder.getQuantity(), "The executed order quantity should be 100.");
        assertEquals(150.0, executedOrder.getPrice(), "The executed order price should be 150.0.");
    }

    /**
     * Test to verify that the system executes a sell order correctly.
     * It ensures that the sell order is processed and the expected result occurs.
     */
    @Test
    public void testSellOrderExecution() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a sell order for AAPL stock
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 50, "sell", 150.0);
        assertTrue(orderExecuted, "Sell order should be executed successfully.");

        // Simulate checking if the order is properly logged or processed
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals("AAPL", executedOrder.getSymbol(), "The executed order should be for AAPL.");
        assertEquals(50, executedOrder.getQuantity(), "The executed order quantity should be 50.");
        assertEquals(150.0, executedOrder.getPrice(), "The executed order price should be 150.0.");
    }

    /**
     * Test to verify that the system rejects an order when the price is below the market price for buy orders.
     * It ensures that the trading system handles price validation for buy orders.
     */
    @Test
    public void testRejectBuyOrderBelowMarketPrice() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a buy order for AAPL below the market price
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 100, "buy", 140.0);
        assertFalse(orderExecuted, "Buy orders below market price should be rejected.");
    }

    /**
     * Test to verify that the system rejects a sell order when the user doesn't have enough shares to sell.
     * It ensures that the system checks user balance and prevents invalid sell orders.
     */
    @Test
    public void testRejectSellOrderWithoutSufficientShares() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a sell order for AAPL without having enough shares
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 100, "sell", 150.0);
        assertFalse(orderExecuted, "Sell orders without sufficient shares should be rejected.");
    }

    /**
     * Test to verify that the system properly handles a market order execution.
     * It ensures that a market order gets executed at the best available price.
     */
    @Test
    public void testMarketOrderExecution() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a market order for AAPL
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 100, "buy", 0.0); // Price 0.0 indicates market price
        assertTrue(orderExecuted, "Market order should be executed successfully at the best available price.");

        // Simulate checking the last executed order to verify price is set to the market price
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals(150.0, executedOrder.getPrice(), "Market order price should be set to the current market price.");
    }

    /**
     * Test to verify that the system properly handles a limit order execution.
     * It ensures that a limit order only gets executed if the price conditions are met.
     */
    @Test
    public void testLimitOrderExecution() {
        // Simulate receiving market data for AAPL (Apple)
        marketDataProcessor.updateMarketData("AAPL", 150.0); // Market price for AAPL

        // Simulate placing a limit buy order for AAPL with a limit price of 160
        boolean orderExecuted = orderExecution.executeOrder("AAPL", 100, "buy", 160.0);
        assertTrue(orderExecuted, "Limit buy order should be executed when the market price matches the limit price.");

        // Simulate checking the last executed order
        Order executedOrder = orderExecution.getLastExecutedOrder();
        assertNotNull(executedOrder, "Last executed order should not be null.");
        assertEquals(160.0, executedOrder.getPrice(), "Limit order price should be set to the limit price.");
    }

    /**
     * Cleans up the order execution environment after each test.
     * Ensures the system is ready for the next test without stale data.
     */
    @AfterEach
    public void tearDown() {
        orderExecution = null;
        tradingEngine = null;
        marketDataProcessor = null;
    }
}
