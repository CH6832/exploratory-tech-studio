import com.system.algotrading.strategy.TradingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for TradingStrategy.
 * This class tests the behavior of the TradingStrategy class, particularly the logic of
 * evaluating the trading strategy and ensuring it returns the expected decision.
 */
class TradingStrategyTest {

    private TradingStrategy tradingStrategy;

    /**
     * Sets up the TradingStrategy instance before each test.
     * This method initializes the TradingStrategy object that will be used in the tests.
     */
    @BeforeEach
    void setUp() {
        // Initialize the TradingStrategy object before each test
        tradingStrategy = new TradingStrategy();
    }

    /**
     * Tests the evaluateStrategy method of the TradingStrategy class.
     * This test verifies that the evaluateStrategy method returns the expected decision
     * ("buy") based on the simulated logic within the method.
     */
    @Test
    void testEvaluateStrategy() {
        // Call the evaluateStrategy method to get the trading decision
        String result = tradingStrategy.evaluateStrategy();

        // Verify that the result matches the expected decision ("buy")
        assertEquals("buy", result, "The strategy should return 'buy' as the decision.");
    }
}
