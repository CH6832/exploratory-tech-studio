import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OrderRouterTest {
    private OrderRouter orderRouter;

    @BeforeEach
    public void setUp() {
        orderRouter = new OrderRouter(); // Initialize the OrderRouter before each test
        orderRouter.start(); // Start the OrderRouter
    }

    @Test
    public void testRouteOrder() {
        // Test that routing an order does not throw an exception
        assertDoesNotThrow(() -> orderRouter.routeOrder());
    }

    @Test
    public void testStartStop() {
        // Ensure starting and stopping does not throw exceptions
        assertDoesNotThrow(() -> orderRouter.start());
        assertDoesNotThrow(() -> orderRouter.stop());
    }

    // Clean up resources after each test
    @AfterEach
    public void tearDown() {
        orderRouter.stop(); // Stop the OrderRouter after each test
    }
}
