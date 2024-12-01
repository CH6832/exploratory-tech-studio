package nonfunctional.usability;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserWorkflowExperienceTest simulates and validates the user experience
 * for key workflows in the trading system to ensure smooth and intuitive operation.
 */
public class UserWorkflowExperienceTest {

    private TradingSystem tradingSystem;

    /**
     * Sets up the TradingSystem instance before each test.
     */
    @BeforeEach
    public void setup() {
        tradingSystem = new TradingSystem();
    }

    /**
     * Tests the entire user workflow from login to placing a trade.
     */
    @Test
    public void testLoginAndPlaceTradeWorkflow() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a trade after login
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade after login.");
    }

    /**
     * Tests the workflow for modifying an existing trade.
     */
    @Test
    public void testModifyTradeWorkflow() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing an initial trade
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate modifying the trade
        boolean isTradeModified = tradingSystem.modifyTrade(1, 50); // Trade ID 1, update quantity to 50
        assertTrue(isTradeModified, "User should be able to modify an existing trade.");
    }

    /**
     * Tests the workflow for cancelling a trade.
     */
    @Test
    public void testCancelTradeWorkflow() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a trade
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate cancelling the trade
        boolean isTradeCancelled = tradingSystem.cancelTrade(1); // Trade ID 1
        assertTrue(isTradeCancelled, "User should be able to cancel a trade.");
    }

    /**
     * Tests the workflow for viewing trade history.
     */
    @Test
    public void testViewTradeHistoryWorkflow() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing some trades
        tradingSystem.placeTrade("AAPL", 100, "buy");
        tradingSystem.placeTrade("TSLA", 50, "sell");

        // Simulate viewing trade history
        String tradeHistory = tradingSystem.getTradeHistory();
        assertNotNull(tradeHistory, "User should be able to view their trade history.");
        assertTrue(tradeHistory.contains("AAPL"), "Trade history should contain AAPL trades.");
        assertTrue(tradeHistory.contains("TSLA"), "Trade history should contain TSLA trades.");
    }

    /**
     * Tests the workflow for logging out of the trading system.
     */
    @Test
    public void testLogoutWorkflow() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate logging out
        boolean isLoggedOut = tradingSystem.logout();
        assertTrue(isLoggedOut, "User should be able to log out.");
    }

    /**
     * Tears down the TradingSystem instance after each test.
     */
    @AfterEach
    public void tearDown() {
        tradingSystem = null;
    }
}
