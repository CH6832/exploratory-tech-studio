package com.system.algotrading.engine;

import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.strategy.TradingStrategy;
import com.system.algotrading.strategy.TradingStrategyFactory;

public class TradingEngine {

    private OrderExecution orderExecution;
    private TradingStrategy strategy;

    // Constructor that initializes strategy using the TradingStrategyFactory
    public TradingEngine(String strategyType, OrderExecution orderExecution) {
        this.orderExecution = orderExecution;
        this.strategy = (TradingStrategy) TradingStrategyFactory.getTradingStrategy(strategyType);
    }

    // Constructor that accepts both a custom OrderExecution and a Strategy
    public TradingEngine(OrderExecution orderExecution, TradingStrategy strategy) {
        this.orderExecution = orderExecution;
        this.strategy = strategy;
    }

    /**
     * Run the trading engine logic, evaluate strategy and decide on actions.
     */
    public void runTradingCycle() {
        // Make sure the strategy is not null
        if (strategy == null) {
            System.out.println("Error: No valid strategy provided.");
            return;
        }

        // Run the selected strategy
        System.out.println("Running strategy: " + strategy.getClass().getSimpleName());
        strategy.executeStrategy(orderExecution);
    }

    public static void main(String[] args) {
        // Example using the TradingStrategyFactory to set a strategy and OrderExecution
        OrderExecution orderExecution = new OrderExecution();  // Assuming this is a valid class
        TradingEngine engine = new TradingEngine("trendFollowing", orderExecution);
        engine.runTradingCycle();
    }

    public void start() {
    }
}
