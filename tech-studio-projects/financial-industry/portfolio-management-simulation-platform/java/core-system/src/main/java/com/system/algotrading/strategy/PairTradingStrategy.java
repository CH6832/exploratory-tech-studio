package com.system.algotrading.strategy;

import com.system.algotrading.execution.OrderExecution;

public class PairTradingStrategy implements TradingStrategy {
    @Override
    public void executeStrategy(OrderExecution orderExecution) {
        // Pair trading strategy logic (buy/sell based on spread divergence)
        System.out.println("Executing Pair Trading Strategy...");
        // orderExecution.executeOrder(...) or any logic related to pair trades
    }

    @Override
    public String evaluateStrategy() {
        return "";
    }
}
