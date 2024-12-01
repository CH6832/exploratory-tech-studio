package functional.acceptance;

import com.system.algotrading.engine.TradingEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TradeStatusUITest validates the User Interface (UI) that displays the status of trades.
 * It ensures that trade statuses such as 'Pending', 'Executed', 'Cancelled', etc., are correctly shown in the UI.
 */
public class TradeStatusUITest {

    private TradingEngine tradingSystem;
    private UserInterface userInterface;

    /**
     * Sets up the TradingSystem and UserInterface instances before each test.
     */
    @BeforeEach
    public void setup() {
        tradingSystem = new TradingEngine();
        userInterface = tradingSystem.getUserInterface(); // Assuming this method retrieves the UI component
    }

    /**
     * Test to verify the correct display of trade status after a trade is placed.
     */
    @Test
    public void testTradeStatusDisplayedAfterTradePlaced() {
        // Simulate user logging in
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        // Simulate placing a trade
        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Check if trade status is displayed as 'Pending' on UI
        String tradeStatus = userInterface.getTradeStatus("AAPL");
        assertEquals("Pending", tradeStatus, "Trade status should be displayed as 'Pending' after placing the trade.");
    }

    /**
     * Test to verify the correct display of trade status after a trade is executed.
     */
    @Test
    public void testTradeStatusDisplayedAfterTradeExecuted() {
        // Simulate user logging in and placing a trade
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate the execution of the trade
        boolean isTradeExecuted = tradingSystem.executeTrade("AAPL", 100, "buy");
        assertTrue(isTradeExecuted, "Trade should be executed successfully.");

        // Verify that the UI displays the correct trade status as 'Executed'
        String tradeStatus = userInterface.getTradeStatus("AAPL");
        assertEquals("Executed", tradeStatus, "Trade status should be displayed as 'Executed' after the trade is executed.");
    }

    /**
     * Test to verify the correct display of trade status after a trade is canceled.
     */
    @Test
    public void testTradeStatusDisplayedAfterTradeCancelled() {
        // Simulate user logging in and placing a trade
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        boolean isTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        assertTrue(isTradePlaced, "User should be able to place a trade.");

        // Simulate canceling the trade
        boolean isTradeCancelled = tradingSystem.cancelTrade("AAPL");
        assertTrue(isTradeCancelled, "Trade should be cancelled successfully.");

        // Verify that the UI displays the correct trade status as 'Cancelled'
        String tradeStatus = userInterface.getTradeStatus("AAPL");
        assertEquals("Cancelled", tradeStatus, "Trade status should be displayed as 'Cancelled' after the trade is canceled.");
    }

    /**
     * Test to verify the correct display of trade status for multiple trades.
     */
    @Test
    public void testTradeStatusForMultipleTrades() {
        // Simulate user logging in and placing multiple trades
        boolean isLoggedIn = tradingSystem.login("testUser", "password123");
        assertTrue(isLoggedIn, "User should be able to log in with valid credentials.");

        boolean isFirstTradePlaced = tradingSystem.placeTrade("AAPL", 100, "buy");
        boolean isSecondTradePlaced = tradingSystem.placeTrade("TSLA", 50, "sell");
        assertTrue(isFirstTradePlaced, "User should be able to place the first trade.");
        assertTrue(isSecondTradePlaced, "User should be able to place the second trade.");

        // Simulate executing both trades
        boolean isFirstTradeExecuted = tradingSystem.executeTrade("AAPL", 100, "buy");
        boolean isSecondTradeExecuted = tradingSystem.executeTrade("TSLA", 50, "sell");
        assertTrue(isFirstTradeExecuted, "First trade should be executed.");
        assertTrue(isSecondTradeExecuted, "Second trade should be executed.");

        // Check UI for status of both trades
        String firstTradeStatus = userInterface.getTradeStatus("AAPL");
        String secondTradeStatus = userInterface.getTradeStatus("TSLA");

        assertEquals("Executed", firstTradeStatus, "First trade status should be 'Executed'.");
        assertEquals("Executed", secondTradeStatus, "Second trade status should be 'Executed'.");
    }

    /**
     * Tears down the TradingSystem and UserInterface instances after each test.
     */
    @AfterEach
    public void tearDown() {
        tradingSystem = null;
        userInterface = null;
    }
}
