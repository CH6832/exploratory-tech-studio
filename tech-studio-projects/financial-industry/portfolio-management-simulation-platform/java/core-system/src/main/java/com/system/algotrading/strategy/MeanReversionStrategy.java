package com.system.algotrading.strategy;

import com.system.algotrading.execution.OrderExecution;

public class MeanReversionStrategy {

    /**
     * Calculate the Z-Score to determine how far the current price is from the mean.
     *
     * @param currentPrice Current price of the asset.
     * @param mean The average price over a historical period.
     * @param stdDev The standard deviation of the asset's price over a historical period.
     * @return The Z-Score indicating how far the current price is from the mean.
     */
    public double calculateZScore(double currentPrice, double mean, double stdDev) {
        return (currentPrice - mean) / stdDev;
    }

    /**
     * Evaluate whether to buy, sell, or hold based on the mean reversion strategy.
     *
     * @param currentPrice Current price of the asset.
     * @param mean The historical mean of the asset.
     * @param stdDev The standard deviation of the asset's price.
     * @return The action to take: "buy", "sell", or "hold".
     */
    public String evaluateStrategy(double currentPrice, double mean, double stdDev) {
        double zScore = calculateZScore(currentPrice, mean, stdDev);

        if (zScore < -1) {
            return "buy";   // Buy if the price is too low (below the mean by more than 1 standard deviation)
        } else if (zScore > 1) {
            return "sell";  // Sell if the price is too high (above the mean by more than 1 standard deviation)
        } else {
            return "hold";  // Hold if the price is within 1 standard deviation of the mean
        }
    }
}
