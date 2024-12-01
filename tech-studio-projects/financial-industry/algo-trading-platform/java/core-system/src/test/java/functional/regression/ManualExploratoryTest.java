package regression.manual;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ManualExploratoryTest is a regression test suite to verify that recent code changes
 * or feature additions do not break any existing functionality in the system.
 * This test focuses on interacting with the system and identifying any unintended consequences
 * after changes.
 */
public class ManualExploratoryTest {

    private TradingEngine tradingEngine;
    private MarketDataProcessor marketDataProcessor;
    private OrderExecution orderExecution;

    /**
     * Setup the environment for the regression test.
     * Initializes the TradingEngine, MarketDataProcessor, and OrderExecution components.
     */
    @BeforeEach
    public void setup() {
        tradingEngine = new TradingEngine();
        marketDataProcessor = new MarketDataProcessor();
        orderExecution = new OrderExecution();
    }

    /**
     * Regression test to verify that market data is processed correctly after recent code changes.
     * It simulates sending market data and ensuring that the market data processor is functioning properly.
     */
    @Test
    public void testMarketDataProcessingAfterChanges() {
        // Simulate new market data after code changes
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";

        // Process market data through the MarketDataProcessor
        boolean isProcessed = marketDataProcessor.processMarketData(marketData);

        // Assert that the data is processed correctly
        assertTrue(isProcessed, "Market data should be processed successfully.");
    }

    /**
     * Regression test to verify that order execution continues to work correctly after code changes.
     * This test ensures that the system processes valid orders without any issues.
     */
    @Test
    public void testOrderExecutionAfterRecentChanges() {
        // Create a valid order
        Order order = new Order("AAPL", 500, 150.0);

        // Attempt to execute the order
        boolean isExecuted = orderExecution.executeOrder(order);

        // Assert that the order was executed successfully
        assertTrue(isExecuted, "Order should be executed successfully.");
    }

    /**
     * Regression test to verify that the TradingEngine still functions correctly after system changes.
     * The TradingEngine should process orders and interact with other components as expected.
     */
    @Test
    public void testTradingEngineFunctionality() {
        // Simulate a valid order through the TradingEngine
        Order order = new Order("AAPL", 200, 150.0);

        // Attempt to process the order through the TradingEngine
        boolean isProcessed = tradingEngine.processOrder(order);

        // Assert that the order is processed by the TradingEngine
        assertTrue(isProcessed, "The TradingEngine should process the order successfully.");
    }

    /**
     * Regression test to ensure that recent code changes do not break the overall system
     * by testing all components interacting together in a simple use case.
     */
    @Test
    public void testFullTradeExecutionFlow() {
        // Simulate a sequence of market data and order execution
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";
        boolean isMarketDataProcessed = marketDataProcessor.processMarketData(marketData);

        // Create an order based on the processed market data
        Order order = new Order("AAPL", 100, 150.0);

        // Check if the order is executed through the TradingEngine
        boolean isOrderExecuted = tradingEngine.processOrder(order);

        // Ensure the system behaves as expected by combining both functionalities
        assertTrue(isMarketDataProcessed, "Market data should be processed successfully.");
        assertTrue(isOrderExecuted, "Order should be executed successfully.");
    }

    /**
     * Cleanup after each test, resetting all system components to ensure no test contamination.
     */
    @AfterEach
    public void tearDown() {
        tradingEngine.reset();
        marketDataProcessor.reset();
        orderExecution.reset();
    }
}
