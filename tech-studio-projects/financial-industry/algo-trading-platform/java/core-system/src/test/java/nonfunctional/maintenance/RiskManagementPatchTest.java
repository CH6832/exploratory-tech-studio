package nonfunctional.maintenance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Patch tests for RiskManagement component to verify fixes after patch updates.
 */
public class RiskManagementPatchTest {

    /**
     * Test to verify risk thresholds are applied correctly after a patch update.
     */
    @Test
    public void testRiskThresholds() {
        // Setup initial risk configuration
        RiskManagement riskManager = new RiskManagement();
        riskManager.setRiskThreshold(10000);

        // Test sample trade
        Trade trade = new Trade("AAPL", 150.00, 100);

        // Check if trade exceeds risk threshold
        boolean isAllowed = riskManager.validateTrade(trade);

        // Assert risk validation works
        assertTrue(isAllowed, "RiskManagement should allow trades within risk thresholds.");
    }

    /**
     * Test to verify that unauthorized trades are blocked post-patch.
     */
    @Test
    public void testBlockUnauthorizedTrades() {
        // Setup with strict risk rules
        RiskManagement riskManager = new RiskManagement();
        riskManager.setAllowUnauthorizedTrades(false);

        // Test unauthorized trade
        Trade unauthorizedTrade = new Trade("AAPL", 20000.00, 500);
        boolean isAllowed = riskManager.validateTrade(unauthorizedTrade);

        // Assert trade is blocked
        assertFalse(isAllowed, "RiskManagement should block unauthorized trades.");
    }
}
