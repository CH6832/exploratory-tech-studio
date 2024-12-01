package regression;

import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.risk.RiskManagement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RegressionSuiteTest is a comprehensive regression test suite designed to verify that all recent changes,
 * refactors, or bug fixes do not break any existing functionality across the trading system.
 * The suite includes tests that cover core system components and ensure that the software behaves as expected.
 */
public class RegressionSuiteTest {

    private TradingEngine tradingEngine;
    private MarketDataProcessor marketDataProcessor;
    private OrderExecution orderExecution;
    private RiskManagement riskManagement;

    /**
     * Setup the environment for the regression tests.
     * Initializes core components of the system like TradingEngine, MarketDataProcessor,
     * OrderExecution, and RiskManagement, which will be tested for regression.
     */
    @BeforeEach
    public void setup() {
        tradingEngine = new TradingEngine();
        marketDataProcessor = new MarketDataProcessor();
        orderExecution = new OrderExecution();
        riskManagement = new RiskManagement();
    }

    /**
     * Regression test to ensure that the MarketDataProcessor works as expected after recent changes.
     * Verifies that market data is processed correctly and doesn't break with the new updates.
     */
    @Test
    public void testMarketDataProcessorAfterChanges() {
        // Sample market data input
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";

        // Process the market data
        boolean result = marketDataProcessor.processMarketData(marketData);

        // Assert that the market data is processed successfully
        assertTrue(result, "Market data should be processed without errors.");
    }

    /**
     * Regression test to ensure the OrderExecution system functions properly after system updates.
     * This test validates that orders are executed correctly and no existing functionality is broken.
     */
    @Test
    public void testOrderExecutionAfterChanges() {
        // Create a sample order
        Order order = new Order("AAPL", 100, 150.0);

        // Execute the order
        boolean result = orderExecution.executeOrder(order);

        // Assert that the order is executed without errors
        assertTrue(result, "Order should be executed successfully.");
    }

    /**
     * Regression test to validate that the TradingEngine continues to process orders correctly.
     * It ensures that all trading logic, including order handling, works after the system changes.
     */
    @Test
    public void testTradingEngineAfterChanges() {
        // Create a sample order for processing through the TradingEngine
        Order order = new Order("AAPL", 200, 155.0);

        // Process the order through the TradingEngine
        boolean result = tradingEngine.processOrder(order);

        // Assert that the TradingEngine processes the order correctly
        assertTrue(result, "TradingEngine should process the order successfully.");
    }

    /**
     * Regression test to ensure RiskManagement continues to validate risk after recent code changes.
     * This test checks if the risk assessment logic is still functioning correctly.
     */
    @Test
    public void testRiskManagementAfterChanges() {
        // Create a sample trade to assess risk
        Trade trade = new Trade("AAPL", 100, 150.0);

        // Assess the risk of the trade
        boolean result = riskManagement.assessRisk(trade);

        // Assert that risk management works as expected
        assertTrue(result, "Risk management should assess trade risk correctly.");
    }

    /**
     * Regression test to ensure that all components function properly together after recent changes.
     * This test simulates a complete trade execution, validating that the entire system works as expected.
     */
    @Test
    public void testEndToEndTradeExecution() {
        // Simulate sending market data to the processor
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";
        boolean isMarketDataProcessed = marketDataProcessor.processMarketData(marketData);

        // Create an order based on the processed market data
        Order order = new Order("AAPL", 100, 150.0);

        // Execute the order using the TradingEngine
        boolean isOrderExecuted = tradingEngine.processOrder(order);

        // Ensure the entire trade flow works by checking both components
        assertTrue(isMarketDataProcessed, "Market data should be processed successfully.");
        assertTrue(isOrderExecuted, "Order should be executed without errors.");
    }

    /**
     * A regression test to verify that any critical defects are not reintroduced in the system after code changes.
     * It covers all necessary aspects of order processing, risk management, and trading engine functionality.
     */
    @Test
    public void testCompleteSystemRegression() {
        // Process market data, execute orders, and assess risks all together in a single test
        String marketData = "{\"symbol\":\"AAPL\",\"price\":150.0,\"volume\":1000}";
        Order order = new Order("AAPL", 100, 150.0);
        Trade trade = new Trade("AAPL", 100, 150.0);

        // Ensure market data is processed
        boolean isMarketDataProcessed = marketDataProcessor.processMarketData(marketData);

        // Execute the order through the trading engine
        boolean isOrderExecuted = tradingEngine.processOrder(order);

        // Assess the risk of the trade
        boolean isRiskAssessed = riskManagement.assessRisk(trade);

        // Assert that all components work together without any issues
        assertTrue(isMarketDataProcessed, "Market data should be processed correctly.");
        assertTrue(isOrderExecuted, "Order execution should succeed.");
        assertTrue(isRiskAssessed, "Risk assessment should be successful.");
    }

    /**
     * Clean up after each test, ensuring all components are reset to avoid any test contamination.
     */
    @AfterEach
    public void tearDown() {
        tradingEngine.reset();
        marketDataProcessor.reset();
        orderExecution.reset();
        riskManagement.reset();
    }
}
