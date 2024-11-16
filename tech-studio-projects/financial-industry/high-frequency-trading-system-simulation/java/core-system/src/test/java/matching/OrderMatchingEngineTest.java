package matching;

import com.hft.matching.OrderMatchingEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderMatchingEngineTest {
    private OrderMatchingEngine engine;

    @BeforeEach
    public void setup() {
        engine = new OrderMatchingEngine();
    }

    @Test
    public void testMatchOrder_SuccessfulMatch() {
        double buyOrderValue = 100.0;
        double sellOrderValue = 100.0;
        assertTrue(engine.matchOrder(buyOrderValue, sellOrderValue), "Orders should match");
    }

    @Test
    public void testMatchOrder_UnsuccessfulMatch() {
        double buyOrderValue = 100.0;
        double sellOrderValue = 105.0;
        assertFalse(engine.matchOrder(buyOrderValue, sellOrderValue), "Orders should not match");
    }
}

