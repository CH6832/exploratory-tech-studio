package nonfunctional.maintenance;

import com.system.algotrading.data.MarketDataProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Regression tests for the MarketDataProcessor component post-deployment.
 * Ensures the component processes data correctly after changes or updates.
 */
public class MarketDataProcessorRegressionTest {

    /**
     * Test to ensure that market data is processed correctly for valid inputs.
     */
    @Test
    public void testProcessValidMarketData() {
        // Setup test data
        String sampleMarketData = "AAPL,150.00,100";
        MarketDataProcessor processor = new MarketDataProcessor();

        // Execute processing
        boolean result = Boolean.parseBoolean(processor.processMarketData(sampleMarketData));

        // Assert expected behavior
        assertTrue(result, "MarketDataProcessor should process valid market data successfully.");
    }

    /**
     * Test to ensure invalid data does not crash the processor.
     */
    @Test
    public void testHandleInvalidMarketData() {
        // Setup test data
        String invalidMarketData = "INVALID_DATA";
        MarketDataProcessor processor = new MarketDataProcessor();

        // Execute processing
        boolean result = Boolean.parseBoolean(processor.processMarketData(invalidMarketData));

        // Assert processor handled gracefully
        assertFalse(result, "MarketDataProcessor should fail gracefully on invalid data.");
    }
}
