package risk;

import com.hft.risk.RiskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class RiskManagerTest {
    private RiskManager riskManager;

    @BeforeEach
    public void setup() {
        riskManager = new RiskManager();
    }

    @Test
    public void testValidateOrderWithinRiskLimits() {
        assertTrue("Order within risk limits should pass", riskManager.checkRisk(1000));
    }

    @Test
    public void testValidateOrderExceedingRiskLimits() {
        assertTrue(riskManager.checkRisk(20000));
    }
}
