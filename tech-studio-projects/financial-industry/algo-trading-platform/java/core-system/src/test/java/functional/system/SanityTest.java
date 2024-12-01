package systemtests;

import com.system.algotrading.engine.TradingEngine;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SanityTest is a quick check to verify that critical functionalities of the system
 * are working as expected after deployment or code changes.
 * This test ensures that the system is stable and doesn't have critical failures.
 */
public class SanityTest {

    private TradingEngine tradingEngine;

    /**
     * Setup the environment for sanity tests.
     * Initializes the TradingEngine to test the core trading functionalities.
     */
    public SanityTest() {
        this.tradingEngine = new TradingEngine();
    }

    /**
     * Sanity test to verify that the TradingEngine starts and processes basic trades correctly.
     * This test ensures the system can handle a basic trade and doesn't fail on initialization.
     */
    @Test
    public void testTradingEngineSanity() {
        // Create a basic order to test the trading engine
        Order order = new Order("AAPL", 100, 150.0);

        // Ensure the system can process the order without errors
        boolean result = tradingEngine.processOrder(order);

        // Assert that the order is processed successfully
        assertTrue(result, "The trading engine should process orders correctly.");
    }
}
