package systemtests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SmokeTest is a basic verification test to ensure that the essential features of the system
 * work as expected after deployment, integration, or updates.
 * It performs a "smoke check" to make sure the most critical features are functioning.
 */
public class SmokeTest {

    private MarketDataProcessor marketDataProcessor;
    private TradingEngine tradingEngine;
    private OrderExecution orderExecution;

    /**
     * Setup the environment for the smoke tests.
     * Initializes all core components such as MarketDataProcessor, TradingEngine, and OrderExecution.
     */
    public SmokeTest() {
        this.marketDataProcessor = new MarketDataProcessor();
        this.tradingEngine = new TradingEngine();
        this.orderExecution = new OrderExecution();
    }

    /**
     * Smoke test to ensure market data is processed successfully.
     * This test ensures that the system can fetch, process, and handle market data.
     */
    @Test
    public void testMarketDataProcessing() {
        // Sample market data for processing
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";

        // Ensure the market data is processed successfully
        boolean result = marketDataProcessor.processMarketData(marketData);

        // Assert that the market data processing is successful
        assertTrue(result, "Market data should be processed successfully.");
    }

    /**
     * Smoke test to ensure that an order can be executed.
     * This test checks if the critical function of order execution works without failures.
     */
    @Test
    public void testOrderExecution() {
        // Create a basic order
        Order order = new Order("AAPL", 100, 150.0);

        // Ensure the order is executed without errors
        boolean result = orderExecution.executeOrder(order);

        // Assert that the order execution is successful
        assertTrue(result, "Order execution should succeed.");
    }

    /**
     * Smoke test to ensure that the trading engine works and processes orders.
     * This test verifies the core functionality of the trading engine.
     */
    @Test
    public void testTradingEngine() {
        // Create a basic order
        Order order = new Order("AAPL", 100, 150.0);

        // Ensure the order is processed by the trading engine
        boolean result = tradingEngine.processOrder(order);

        // Assert that the order is processed successfully by the trading engine
        assertTrue(result, "Trading engine should process the order successfully.");
    }
}
