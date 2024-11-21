package com.system.algotrading.strategy;

import com.system.algotrading.execution.OrderExecution;

public class ScalpingStrategy implements TradingStrategy {
    @Override
    public void executeStrategy(OrderExecution orderExecution) {
        // Scalping strategy logic (execute many quick trades with small profits)
        System.out.println("Executing Scalping Strategy...");
        // orderExecution.executeOrder(...) or any logic related to scalping trades
    }

    @Override
    public String evaluateStrategy() {
        return "";
    }
}
