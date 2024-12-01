package nonfunctional.maintenance;

import com.system.algotrading.execution.OrderExecution;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Regression tests for the OrderExecution component post-deployment.
 * Ensures orders are executed correctly after updates to the system.
 */
public class OrderExecutionRegressionTest {

    /**
     * Test to verify successful execution of a valid buy order.
     */
    @Test
    public void testExecuteBuyOrder() {
        // Setup test data
        Order buyOrder = new Order("BUY", "AAPL", 10, 150.00);
        OrderExecution executor = new OrderExecution();

        // Execute order
        boolean result = executor.executeOrder(String.valueOf(buyOrder));

        // Assert expected behavior
        assertTrue(result, "OrderExecution should successfully execute valid buy orders.");
    }

    /**
     * Test to verify rejection of invalid order parameters.
     */
    @Test
    public void testRejectInvalidOrder() {
        // Setup invalid order
        Order invalidOrder = new Order("INVALID", "AAPL", 0, 0.0);
        OrderExecution executor = new OrderExecution();

        // Execute invalid order
        boolean result = executor.executeOrder(String.valueOf(invalidOrder));

        // Assert rejection
        assertFalse(result, "OrderExecution should reject invalid orders.");
    }
}
