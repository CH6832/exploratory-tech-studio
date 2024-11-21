import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.strategy.TradingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Unit test class for TradingEngine.
 * This class tests the behavior of the TradingEngine class, particularly focusing
 * on verifying whether the engine correctly evaluates the strategy and executes orders.
 */
class TradingEngineTest {

    private TradingEngine tradingEngine;
    private OrderExecution orderExecution;
    private TradingStrategy tradingStrategy;

    /**
     * Sets up the TradingEngine instance and mocks the dependencies before each test.
     * This method initializes the TradingEngine object along with its dependencies,
     * OrderExecution and TradingStrategy, which are mocked for testing purposes.
     */
    @BeforeEach
    void setUp() {
        // Mocking the dependencies for testing
        orderExecution = mock(OrderExecution.class);
        tradingStrategy = mock(TradingStrategy.class);

        // Initialize the TradingEngine with mocked dependencies
        tradingEngine = new TradingEngine(orderExecution, tradingStrategy);
    }

    /**
     * Tests the start method of the TradingEngine.
     * This test verifies that when the trading engine starts, it correctly calls
     * the evaluateStrategy method of TradingStrategy and the executeOrder method of OrderExecution.
     */
    @Test
    void testStartTradingEngine() {
        // Simulate starting the trading engine
        tradingEngine.start();

        // Verify that evaluateStrategy was called exactly once
        verify(tradingStrategy, times(1)).evaluateStrategy();

        // Verify that executeOrder was called exactly once with "buy" as the argument
        verify(orderExecution, times(1)).executeOrder("buy");
    }
}
