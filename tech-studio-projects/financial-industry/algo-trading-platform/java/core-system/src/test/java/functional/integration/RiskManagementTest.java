package functional.integration;

import com.system.algotrading.risk.RiskManagement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RiskManagementTest validates the functionality of the RiskManagement class.
 * It ensures that the risk management system correctly calculates and enforces risk rules
 * such as position limits, stop-loss, and margin requirements.
 */
public class RiskManagementTest {

    private RiskManagement riskManagement;

    /**
     * Initializes the necessary components before each test.
     * Creates a fresh instance of the RiskManagement class before each test.
     */
    @BeforeEach
    public void setup() {
        riskManagement = new RiskManagement(); // Create a new instance of RiskManagement
    }

    /**
     * Verifies that the system enforces position limits correctly.
     * The system should not allow positions to exceed the predefined limit.
     */
    @Test
    public void testPositionLimitCheck() {
        // Set a position limit for the asset
        String symbol = "AAPL";
        double positionLimit = 1000;
        riskManagement.setPositionLimit(symbol, positionLimit);

        // Simulate a position for the asset
        double currentPosition = 1200;

        // Assert that the position exceeds the limit
        assertFalse(riskManagement.checkPositionLimit(symbol, currentPosition),
                "The position should exceed the set limit of 1000 for AAPL.");
    }

    /**
     * Verifies that the system enforces stop-loss rules correctly.
     * If the market price drops below the stop-loss level, the position should be closed.
     */
    @Test
    public void testStopLossTrigger() {
        // Set a stop-loss level for AAPL
        String symbol = "AAPL";
        double stopLossLevel = 145.0;
        riskManagement.setStopLoss(symbol, stopLossLevel);

        // Simulate the current market price for AAPL
        double currentPrice = 140.0;

        // Assert that the stop-loss triggers when the price drops below the level
        assertTrue(riskManagement.checkStopLoss(symbol, currentPrice),
                "The stop-loss should trigger if the price drops below the set level of 145.0 for AAPL.");
    }

    /**
     * Verifies that the system calculates margin requirements correctly.
     * The margin should be calculated based on the current position and asset price.
     */
    @Test
    public void testMarginCalculation() {
        // Set the asset price and margin rate
        String symbol = "AAPL";
        double price = 150.0;
        double marginRate = 0.25; // 25% margin
        riskManagement.setMarginRate(symbol, marginRate);

        // Simulate a position for the asset
        double position = 1000;

        // Calculate the required margin
        double requiredMargin = riskManagement.calculateMargin(symbol, position, price);

        // Assert that the calculated margin matches the expected margin
        assertEquals(25000.0, requiredMargin, "The margin for 1000 shares of AAPL should be 25000.0.");
    }

    /**
     * Verifies that the system does not allow a position if the margin is insufficient.
     * The system should reject any position that requires more margin than available.
     */
    @Test
    public void testInsufficientMargin() {
        // Set the available margin
        double availableMargin = 5000.0;

        // Set the asset price and margin rate
        String symbol = "AAPL";
        double price = 150.0;
        double marginRate = 0.25; // 25% margin
        riskManagement.setMarginRate(symbol, marginRate);

        // Simulate a position for the asset
        double position = 2500; // 2500 shares of AAPL

        // Assert that the system rejects the position due to insufficient margin
        assertFalse(riskManagement.checkMarginSufficiency(symbol, position, availableMargin, price),
                "The system should reject the position due to insufficient margin.");
    }

    /**
     * Verifies that the system allows a position if the margin is sufficient.
     * The system should approve positions when sufficient margin is available.
     */
    @Test
    public void testSufficientMargin() {
        // Set the available margin
        double availableMargin = 10000.0;

        // Set the asset price and margin rate
        String symbol = "AAPL";
        double price = 150.0;
        double marginRate = 0.25; // 25% margin
        riskManagement.setMarginRate(symbol, marginRate);

        // Simulate a position for the asset
        double position = 5000; // 5000 shares of AAPL

        // Assert that the system approves the position due to sufficient margin
        assertTrue(riskManagement.checkMarginSufficiency(symbol, position, availableMargin, price),
                "The system should allow the position due to sufficient margin.");
    }

    /**
     * Verifies that the system enforces portfolio limits correctly.
     * The system should reject trades that exceed the portfolio's risk tolerance.
     */
    @Test
    public void testPortfolioLimitCheck() {
        // Set a portfolio risk limit
        double portfolioRiskLimit = 50000.0;
        riskManagement.setPortfolioRiskLimit(portfolioRiskLimit);

        // Simulate portfolio risk exceeding the limit
        double currentPortfolioRisk = 60000.0;

        // Assert that the portfolio exceeds the risk limit
        assertFalse(riskManagement.checkPortfolioRiskLimit(currentPortfolioRisk),
                "The portfolio risk should exceed the set limit of 50000.");
    }

    /**
     * Cleans up the resources after each test.
     * Ensures that the system is ready for the next test, with no leftover state.
     */
    @AfterEach
    public void tearDown() {
        riskManagement.resetRiskManagement(); // Reset risk management settings after each test
    }
}
