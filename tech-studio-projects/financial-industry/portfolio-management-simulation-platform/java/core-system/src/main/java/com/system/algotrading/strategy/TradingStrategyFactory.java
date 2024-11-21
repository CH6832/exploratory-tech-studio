package com.system.algotrading.strategy;

public class TradingStrategyFactory {

    /**
     * Factory method to create an instance of the appropriate trading strategy.
     *
     * @param strategyType The type of strategy to use ("meanReversion", "trendFollowing", or "arbitrage").
     * @return The trading strategy.
     */
    public static Object getTradingStrategy(String strategyType) {
        switch (strategyType) {
            case "meanReversion":
                return new MeanReversionStrategy();
            case "trendFollowing":
                return new TrendFollowingStrategy();
            case "arbitrage":
                return new ArbitrageStrategy();
            default:
                throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
        }
    }
}
