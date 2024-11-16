package com.system.algotrading.engine;

import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.strategy.TradingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The TradingEngine class is responsible for managing the core trading loop.
 * It evaluates a trading strategy and executes orders based on strategy decisions.
 *
 * This class integrates with the TradingStrategy and OrderExecution components
 * to handle strategy evaluation and trade execution with a focus on reliability
 * and real-time performance.
 */
public class TradingEngine {

    // Logger instance to log operational information and errors
    private static final Logger logger = LoggerFactory.getLogger(TradingEngine.class);

    // OrderExecution handles order placement logic
    private final OrderExecution orderExecution;

    // TradingStrategy provides decision-making logic for trades
    private final TradingStrategy strategy;

    /**
     * Constructs a TradingEngine with specified order execution and trading strategy.
     *
     * @param orderExecution The component responsible for executing trade orders.
     * @param strategy The strategy component responsible for generating trading decisions.
     */
    public TradingEngine(OrderExecution orderExecution, TradingStrategy strategy) {
        this.orderExecution = orderExecution;
        this.strategy = strategy;
    }

    /**
     * Starts the trading engine cycle, evaluating the strategy and executing orders.
     * This method captures and logs any errors during execution to ensure stability.
     */
    public void start() {
        try {
            // Log the start of the trading engine for tracking
            logger.info("Starting trading engine");

            // Apply the trading strategy's logic to evaluate market conditions
            String decision = strategy.evaluateStrategy();

            // Execute an order based on the strategy's decision
            orderExecution.executeOrder(decision);

            // Log successful completion of one trading engine cycle
            logger.debug("Trading engine cycle completed");

        } catch (Exception e) {
            // Catch and log any exceptions that occur during execution
            logger.error("Error in trading engine", e);
        }
    }
}
