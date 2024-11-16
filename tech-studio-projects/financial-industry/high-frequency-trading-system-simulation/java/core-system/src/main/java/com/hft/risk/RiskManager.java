package com.hft.risk;

/**
 * Manages risk checks for incoming orders, minimizing latency impact on the main trading process.
 */
public class RiskManager {
    private final RiskValidator validator = new RiskValidator();
    private final MarginCalculator marginCalculator = new MarginCalculator();

    /**
     * Performs a risk check for the provided order, tuned for low-latency operations.
     */
    public boolean checkRisk(double orderValue) {
        boolean isValid = validator.validateOrder(orderValue);
        if (isValid) {
            marginCalculator.calculateMargin(orderValue,"DEFAULT");
        }
        return isValid;
    }

    public String validateOrder(int i) {
        return "";
    }
}
