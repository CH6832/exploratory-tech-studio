package com.hft.risk;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * MarginCalculator is responsible for calculating the margin requirements for trades.
 * In high-frequency trading, accurate and efficient margin calculations are critical to ensure
 * trades meet risk and margin requirements without incurring excessive computational overhead.
 * This class is optimized to operate within a low-latency, high-throughput environment.
 */
public class MarginCalculator {

    // Logger instance for debugging and tracking calculation flow
    private static final Logger logger = Logger.getLogger(MarginCalculator.class.getName());

    // Margin rates by asset type or tier, configurable to support different risk profiles
    private static final Map<String, Double> MARGIN_RATES = new HashMap<>();

    // Static block to initialize default margin rates for different asset types or risk levels
    static {
        MARGIN_RATES.put("DEFAULT", 0.1);   // 10% margin for standard assets
        MARGIN_RATES.put("HIGH_RISK", 0.2); // 20% margin for high-risk assets
        MARGIN_RATES.put("LOW_RISK", 0.05); // 5% margin for low-risk assets
    }

    /**
     * Calculates the required margin for a given order value and risk tier.
     * The result reflects the capital required to cover the position.
     *
     * @param orderValue The monetary value of the order.
     * @param riskTier A String identifier for the risk tier (e.g., "DEFAULT", "HIGH_RISK").
     * @return The margin required to cover the order.
     *
     * @throws IllegalArgumentException if orderValue is negative or risk tier is invalid.
     */
    public double calculateMargin(double orderValue, String riskTier) {
        validateOrderValue(orderValue);

        // Retrieve margin rate for specified risk tier, defaulting if tier not recognized
        double marginRate = MARGIN_RATES.getOrDefault(riskTier, MARGIN_RATES.get("DEFAULT"));

        // Compute margin based on order value and risk tier rate
        double marginRequired = orderValue * marginRate;

        // Log calculation details for tracing, useful in high-frequency profiling and audits
        logger.info(String.format("Calculated margin: %f (Order Value: %f, Margin Rate: %f, Risk Tier: %s)",
                marginRequired, orderValue, marginRate, riskTier));

        return marginRequired;
    }

    /**
     * Validates the order value. Ensures that values are non-negative and within
     * reasonable bounds to avoid overflow or unrealistic trades in simulations.
     *
     * @param orderValue The monetary value of the order.
     * @throws IllegalArgumentException if the order value is negative.
     */
    private void validateOrderValue(double orderValue) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value must be non-negative.");
        }
    }

    /**
     * Adds or updates a margin rate for a specified risk tier.
     * This allows for dynamic adjustments to margin rates based on market conditions
     * or policy changes, enabling flexibility in high-frequency trading environments.
     *
     * @param riskTier The name of the risk tier (e.g., "DEFAULT", "HIGH_RISK").
     * @param marginRate The margin rate associated with the risk tier.
     *
     * @throws IllegalArgumentException if the margin rate is negative.
     */
    public void setMarginRate(String riskTier, double marginRate) {
        if (marginRate < 0) {
            throw new IllegalArgumentException("Margin rate must be non-negative.");
        }
        MARGIN_RATES.put(riskTier, marginRate);
        logger.info(String.format("Set new margin rate for %s: %f", riskTier, marginRate));
    }

    /**
     * Retrieves the current margin rate for a specific risk tier.
     * Useful for reporting and auditing purposes to understand margin configurations.
     *
     * @param riskTier The risk tier for which to retrieve the margin rate.
     * @return The margin rate for the specified risk tier.
     */
    public double getMarginRate(String riskTier) {
        return MARGIN_RATES.getOrDefault(riskTier, MARGIN_RATES.get("DEFAULT"));
    }

    /**
     * Provides a summary of the configured margin rates.
     * This method allows external services or monitoring tools to check the current
     * margin requirements for each tier.
     *
     * @return A map of margin rates by risk tier.
     */
    public Map<String, Double> getMarginRatesSummary() {
        return new HashMap<>(MARGIN_RATES); // Returns a copy to prevent modification
    }
}
