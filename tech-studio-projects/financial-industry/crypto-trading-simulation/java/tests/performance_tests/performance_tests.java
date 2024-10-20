import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Helper method to simulate order routing time
void simulateOrderRouting(OrderRouter orderRouter) {
    // Simulate some work for order routing
    for (int i = 0; i < 10000; i++) {
        orderRouter.routeOrder(); // Call routeOrder multiple times
    }
}

public class PerformanceTests {

    private OrderRouter orderRouter;
    private MarketMaker marketMaker;

    @BeforeEach
    public void setUp() {
        orderRouter = new OrderRouter();
        marketMaker = new MarketMaker();
        orderRouter.start(); // Start the order router
        marketMaker.start(); // Start the market maker
    }

    @AfterEach
    public void tearDown() {
        orderRouter.stop(); // Stop the order router
        marketMaker.stop(); // Stop the market maker
    }

    @Test
    public void routingPerformanceTest() {
        long start = System.nanoTime(); // Start time measurement
        simulateOrderRouting(orderRouter); // Simulate order routing
        long end = System.nanoTime(); // End time measurement

        long duration = (end - start) / 1_000_000; // Calculate duration in milliseconds
        System.out.println("Order routing took: " + duration + " ms");

        assertTrue(duration <= 500, "Expected routing to take less than 500ms");
    }

    @Test
    public void marketMakingPerformanceTest() {
        long start = System.nanoTime(); // Start time measurement
        for (int i = 0; i < 1000; i++) {
            // Simulate market making logic
            marketMaker.start(); // This should represent some market-making logic
            marketMaker.stop();  // This should represent stopping the market-making logic
        }
        long end = System.nanoTime(); // End time measurement

        long duration = (end - start) / 1_000_000; // Calculate duration in milliseconds
        System.out.println("Market making took: " + duration + " ms");

        assertTrue(duration <= 1000, "Expected total market-making to take less than 1000ms");
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("PerformanceTests");
    }
}
