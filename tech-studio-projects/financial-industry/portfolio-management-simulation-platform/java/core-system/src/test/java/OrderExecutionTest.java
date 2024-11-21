import com.system.algotrading.execution.OrderExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit test class for OrderExecution.
 * This class tests the behavior of the OrderExecution class, specifically
 * verifying whether an order is executed successfully or not based on the provided order.
 */
class OrderExecutionTest {

    private OrderExecution orderExecution;

    /**
     * Sets up the OrderExecution instance before each test.
     * This method initializes the orderExecution object so that it can be used in test methods.
     */
    @BeforeEach
    void setUp() {
        // Initialize the OrderExecution object before each test
        orderExecution = new OrderExecution();
    }

    /**
     * Tests the executeOrder method for a successful order execution.
     * Verifies that the order is executed successfully when a valid order is passed ("buy").
     */
    @Test
    void testExecuteOrderSuccessful() {
        // Test if the order execution is successful for a valid order
        assertTrue(orderExecution.executeOrder("buy"), "Order should be executed successfully for 'buy' order");
    }

    /**
     * Tests the executeOrder method for a failed order execution.
     * Verifies that the order execution fails when an invalid order is passed.
     */
    @Test
    void testExecuteOrderFailure() {
        // Test if the order execution fails for an invalid order
        assertFalse(orderExecution.executeOrder("invalid_order"), "Order execution should fail for an invalid order");
    }
}
