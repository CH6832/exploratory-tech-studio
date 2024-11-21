import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.market.MarketFeedHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Unit test class for MarketFeedHandler.
 * This class tests the functionality of the MarketFeedHandler by verifying
 * that it correctly interacts with the MarketDataProcessor to process market data.
 */
class MarketFeedHandlerTest {

    private MarketFeedHandler marketFeedHandler;
    private MarketDataProcessor marketDataProcessor;

    /**
     * Sets up mock dependencies and initializes MarketFeedHandler instance before each test.
     * A mock MarketDataProcessor is created to test interactions without relying on actual data processing.
     */
    @BeforeEach
    void setUp() {
        // Create a mock instance of MarketDataProcessor to isolate the test
        marketDataProcessor = mock(MarketDataProcessor.class);

        // Pass the mock to the MarketFeedHandler
        marketFeedHandler = new MarketFeedHandler(marketDataProcessor);
    }

    /**
     * Tests the startFeed method in MarketFeedHandler.
     * Verifies that the MarketFeedHandler correctly calls processMarketData()
     * in MarketDataProcessor once with the expected sample data.
     */
    @Test
    void testStartFeed() {
        // Call the startFeed method, which should trigger data processing
        marketFeedHandler.startFeed();

        // Verify that processMarketData() was called exactly once with "sample_market_data"
        verify(marketDataProcessor, times(1)).processMarketData("sample_market_data");
    }
}
