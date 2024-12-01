package nonfunctional.usability;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MarketDataAccessibilityTest ensures that market data is accessible to all users,
 * including those using assistive technologies like screen readers and tools for
 * visual impairments such as color-blindness simulators.
 */
public class MarketDataAccessibilityTest {

    private MarketDataDisplay marketDataDisplay;

    /**
     * Sets up the MarketDataDisplay instance before each test.
     */
    @BeforeEach
    public void setup() {
        marketDataDisplay = new MarketDataDisplay();
    }

    /**
     * Tests the screen reader compatibility of the market data interface.
     */
    @Test
    public void testScreenReaderCompatibility() {
        // Simulate accessing market data via a screen reader
        String marketData = marketDataDisplay.getMarketDataForScreenReader();

        // Assert that the data provided is not null or empty
        assertNotNull(marketData, "Market data should be accessible for screen readers.");
        assertFalse(marketData.isBlank(), "Market data provided to screen readers should not be empty.");
    }

    /**
     * Tests for proper contrast ratios for visually impaired users.
     */
    @Test
    public void testContrastRatioCompliance() {
        // Simulate checking UI elements for contrast ratio
        boolean isCompliant = marketDataDisplay.isContrastRatioCompliant();

        // Assert that the UI complies with contrast ratio standards
        assertTrue(isCompliant, "UI contrast ratios should meet accessibility standards.");
    }

    /**
     * Tests for color-blindness friendly design in market data charts.
     */
    @Test
    public void testColorBlindFriendlyDesign() {
        // Simulate validation for color-blind friendly visualization
        boolean isColorBlindFriendly = marketDataDisplay.isColorBlindFriendly();

        // Assert that the design accommodates users with color blindness
        assertTrue(isColorBlindFriendly, "Market data visualizations should be color-blind friendly.");
    }

    /**
     * Tests for keyboard navigation in the market data UI.
     */
    @Test
    public void testKeyboardNavigation() {
        // Simulate navigation through UI using keyboard inputs
        boolean isKeyboardNavigable = marketDataDisplay.isKeyboardNavigable();

        // Assert that the UI can be navigated using only the keyboard
        assertTrue(isKeyboardNavigable, "UI should support full keyboard navigation.");
    }

    /**
     * Tests for text scalability on the dashboard.
     */
    @Test
    public void testTextScalability() {
        // Simulate resizing text on the dashboard
        boolean isTextScalable = marketDataDisplay.isTextScalable();

        // Assert that text scales appropriately without breaking UI
        assertTrue(isTextScalable, "Dashboard text should scale appropriately.");
    }

    /**
     * Tears down the MarketDataDisplay instance after each test.
     */
    @AfterEach
    public void tearDown() {
        marketDataDisplay = null;
    }
}
