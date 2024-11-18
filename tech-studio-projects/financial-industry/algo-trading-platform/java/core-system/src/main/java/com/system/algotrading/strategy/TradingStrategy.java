package com.system.algotrading.strategy;

import com.system.algotrading.execution.OrderExecution;

public interface TradingStrategy {
    void executeStrategy(OrderExecution orderExecution);

    String evaluateStrategy();
}

