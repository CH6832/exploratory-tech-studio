package com.system.algotrading.strategy;

import com.system.algotrading.execution.OrderExecution;

import java.util.List;

public class TrendFollowingStrategy implements TradingStrategy {

    /**
     * Calculate the Simple Moving Average (SMA) of a list of prices.
     *
     * @param prices A list of historical prices.
     * @return The SMA (Simple Moving Average) value.
     */
    public double calculateSMA(List<Double> prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.size();  // Average price over the window
    }

    /**
     * Evaluate whether to buy, sell, or hold based on the trend following strategy.
     *
     * @param prices A list of historical prices.
     * @param currentPrice The current price of the asset.
     * @return The action to take: "buy", "sell", or "hold".
     */
    public String evaluateStrategy(List<Double> prices, double currentPrice) {
        double sma = calculateSMA(prices);

        if (currentPrice > sma) {
            return "buy";   // Buy if the current price is above the SMA (uptrend)
        } else if (currentPrice < sma) {
            return "sell";  // Sell if the current price is below the SMA (downtrend)
        } else {
            return "hold";  // Hold if the current price is at the SMA (neutral)
        }
    }

    @Override
    public void executeStrategy(OrderExecution orderExecution) {
        // Implement the logic for the trend following strategy
        System.out.println("Executing Trend Following Strategy...");
        // orderExecution.executeOrder(...) or any logic related to executing the strategy
    }

    @Override
    public String evaluateStrategy() {
        return "";
    }
}
