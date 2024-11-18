package com.system.algotrading.strategy;

public class ArbitrageStrategy {

    /**
     * Calculate the arbitrage profit by comparing prices in two different markets.
     *
     * @param priceA The price of the asset in Market A.
     * @param priceB The price of the asset in Market B.
     * @return The profit from arbitrage (PriceA - PriceB).
     */
    public double calculateArbitrageProfit(double priceA, double priceB) {
        return priceA - priceB;
    }

    /**
     * Evaluate whether there is an arbitrage opportunity based on the price difference.
     *
     * @param priceA The price of the asset in Market A.
     * @param priceB The price of the asset in Market B.
     * @return The action to take: "arbitrage" if there is an opportunity, otherwise "no opportunity".
     */
    public String evaluateStrategy(double priceA, double priceB) {
        double profit = calculateArbitrageProfit(priceA, priceB);

        if (profit > 0) {
            return "arbitrage";   // If there is profit, execute arbitrage
        } else {
            return "no opportunity";  // No arbitrage opportunity
        }
    }
}
