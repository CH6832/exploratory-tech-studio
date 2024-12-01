package nonfunctional.usability;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TradeStatusUITest validates that the user interface correctly displays the trade status updates
 * including success, pending, and failure statuses for each trade placed by the user.
 */
public class TradeStatusUITest {

    private TradingSystem tradingSystem;
    private UserInterface ui;

    /**
     * Sets up the TradingSystem and UserInterface instances before each test.
     */
    @BeforeEach
    public void setup() {
        tradingSystem = new TradingSystem();
        ui = new UserInterface(tradingSystem);
    }

    /**
     * Tests the UI display of trade status after placing a trade successfully.
     */
    @Test
    public void testTradeStatusSuccess() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a trade
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate checking the trade status on UI
        String tradeStatus = ui.getTradeStatus("AAPL");
        assertEquals("Success", tradeStatus, "Trade status should display as 'Success' after trade is placed.");
    }

    /**
     * Tests the UI display of trade status when a trade is pending.
     */
    @Test
    public void testTradeStatusPending() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a pending trade (e.g., because of network delay)
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate a scenario where the trade is pending
        tradingSystem.setTradeStatus("AAPL", "Pending");  // Mocking pending status

        // Simulate checking the trade status on UI
        String tradeStatus = ui.getTradeStatus("AAPL");
        assertEquals("Pending", tradeStatus, "Trade status should display as 'Pending' when the trade is still processing.");
    }

    /**
     * Tests the UI display of trade status when a trade fails.
     */
    @Test
    public void testTradeStatusFailure() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a trade that fails (e.g., insufficient funds)
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 1000, "buy");
        assertFalse(isTradePlaced, "User should not be able to place a trade with insufficient funds.");

        // Simulate setting trade status to failed
        tradingSystem.setTradeStatus("AAPL", "Failed");

        // Simulate checking the trade status on UI
        String tradeStatus = ui.getTradeStatus("AAPL");
        assertEquals("Failed", tradeStatus, "Trade status should display as 'Failed' when there is an issue with the trade.");
    }

    /**
     * Tests the UI display of trade status when no trades are placed yet.
     */
    @Test
    public void testNoTradePlacedStatus() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Check UI for trade status before placing any trade
        String tradeStatus = ui.getTradeStatus("AAPL");
        assertEquals("No trade placed", tradeStatus, "UI should show 'No trade placed' before any trades are made.");
    }

    /**
     * Tears down the TradingSystem and UserInterface instances after each test.
     */
    @AfterEach
    public void tearDown() {
        tradingSystem = null;
        ui = null;
    }
}
