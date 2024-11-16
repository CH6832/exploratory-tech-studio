package com.hft.risk;

/**
 * Validates orders based on risk parameters. Optimized for minimal latency.
 */
public class RiskValidator {
    private static final double MAX_ORDER_VALUE = 1000000;

    public RiskValidator(double maxRiskLimit) {
    }

    public RiskValidator() {

    }

    /**
     * Validates if the order's value is within acceptable risk limits.
     * @return true if valid, false otherwise.
     */
    public boolean validateOrder(double orderValue) {
        return orderValue <= MAX_ORDER_VALUE;
    }

    public void setRiskLimit(double v) {
    }
}

