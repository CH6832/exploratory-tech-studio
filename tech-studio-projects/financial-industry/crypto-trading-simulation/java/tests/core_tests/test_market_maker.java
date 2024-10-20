import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MarketMakerTest {
    private MarketMaker marketMaker;

    @BeforeEach
    public void setUp() {
        marketMaker = new MarketMaker(); // Initialize the MarketMaker before each test
    }

    @Test
    public void testStartStop() {
        marketMaker.start(); // Start the MarketMaker
        assertDoesNotThrow(() -> marketMaker.stop()); // Ensure stopping does not throw an exception
    }
}
