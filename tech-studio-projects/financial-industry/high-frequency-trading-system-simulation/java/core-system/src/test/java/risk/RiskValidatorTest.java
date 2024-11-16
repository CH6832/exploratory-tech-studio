package risk;

import com.hft.risk.RiskValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RiskValidatorTest {

    private RiskValidator riskValidator;
    private static final double MAX_RISK_LIMIT = 10000.0; // Example maximum limit for testing

    @BeforeEach
    public void setup() {
        riskValidator = new RiskValidator(MAX_RISK_LIMIT);
    }

    @Test
    public void testOrderWithinRiskLimit() {
        double orderValue = 5000.0;
        assertTrue(riskValidator.validateOrder(orderValue),
                "Order within risk limit should be validated successfully.");
    }

    @Test
    public void testOrderExactlyAtRiskLimit() {
        double orderValue = MAX_RISK_LIMIT;
        assertTrue(riskValidator.validateOrder(orderValue),
                "Order exactly at risk limit should pass validation.");
    }

    @Test
    public void testOrderExceedingRiskLimit() {
        double orderValue = MAX_RISK_LIMIT + 1;
        assertFalse(riskValidator.validateOrder(orderValue),
                "Order exceeding risk limit should not pass validation.");
    }

    @Test
    public void testMinimumOrderValue() {
        double minOrderValue = 0.01; // Assuming 0.01 is the smallest valid order size
        assertTrue(riskValidator.validateOrder(minOrderValue),
                "Minimum order value should be validated successfully.");
    }

    @Test
    public void testZeroOrderValue() {
        double zeroOrderValue = 0.0;
        assertFalse(riskValidator.validateOrder(zeroOrderValue),
                "Zero order value should not be validated.");
    }

    @Test
    public void testNegativeOrderValue() {
        double negativeOrderValue = -100.0;
        assertFalse(riskValidator.validateOrder(negativeOrderValue),
                "Negative order value should not be validated.");
    }

    @Test
    public void testVeryHighOrderValue() {
        double veryHighOrderValue = 1_000_000.0; // Test with a significantly high order value
        assertFalse(riskValidator.validateOrder(veryHighOrderValue),
                "Order far exceeding the risk limit should not pass validation.");
    }

    @Test
    public void testRiskLimitChangeEffectiveness() {
        // Modify risk limit and ensure it takes effect
        riskValidator.setRiskLimit(15000.0);
        double newLimitOrderValue = 14000.0;
        assertTrue(riskValidator.validateOrder(newLimitOrderValue),
                "Order within the new risk limit should pass validation.");
    }
}
