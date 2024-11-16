package com.system.algotrading;

import com.system.algotrading.data.MarketDataProcessor;
import com.system.algotrading.engine.TradingEngine;
import com.system.algotrading.execution.OrderExecution;
import com.system.algotrading.market.MarketFeedHandler;
import com.system.algotrading.risk.RiskManagement;
import com.system.algotrading.strategy.TradingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the algorithmic trading system simulation.
 * Initializes the core components and runs a sample trading cycle, demonstrating the data flow
 * from market data processing to strategy evaluation, risk assessment, and order execution.
 */
public class Main {

    // Logger instance for logging the main trading system events and errors
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main method to execute the trading system simulation.
     *
     * @param args Command-line arguments (not used in this simulation).
     */
    public static void main(String[] args) {
        logger.info("Starting the trading system simulation...");

        // Initialize core components
        MarketDataProcessor marketDataProcessor = new MarketDataProcessor();
        MarketFeedHandler marketFeedHandler = new MarketFeedHandler(marketDataProcessor);
        OrderExecution orderExecution = new OrderExecution();
        TradingStrategy tradingStrategy = new TradingStrategy();
        RiskManagement riskManagement = new RiskManagement();

        // Initialize the trading engine with its dependencies
        TradingEngine tradingEngine = new TradingEngine(orderExecution, tradingStrategy);

        // Start market data feed to simulate receiving market data
        logger.info("Initiating market data feed simulation...");
        marketFeedHandler.startFeed();
        System.out.println("Market data feed started.");

        // Simulate a single trading cycle to test system interaction
        try {
            logger.info("Running a single trading cycle...");

            // Step 1: Evaluate the trading strategy based on market data
            String tradeDecision = tradingStrategy.evaluateStrategy();
            logger.info("Trade decision based on strategy: {}", tradeDecision);
            System.out.println("Trade decision: " + tradeDecision);

            // Step 2: Assess the risk for the trade decision
            if (riskManagement.assessRisk(tradeDecision)) {
                logger.info("Risk is acceptable, proceeding with order execution.");
                System.out.println("Risk assessment passed. Executing trade...");

                // Step 3: Execute the order if risk assessment is successful
                boolean executionSuccess = orderExecution.executeOrder(tradeDecision);
                if (executionSuccess) {
                    logger.info("Order execution completed successfully.");
                    System.out.println("Order executed successfully.");
                } else {
                    logger.warn("Order execution failed.");
                    System.out.println("Order execution failed.");
                }
            } else {
                logger.warn("Trade decision was not executed due to risk assessment failure");
                System.out.println("Risk assessment failed. Trade decision not executed.");
            }

            // Step 4: Run the trading engine, which coordinates strategy evaluation and order execution
            logger.info("Starting trading engine...");
            tradingEngine.start();
            logger.info("Trading cycle completed successfully.");
            System.out.println("Trading cycle completed.");

        } catch (Exception e) {
            // Catch and log any exceptions that may occur during the trading cycle
            logger.error("An error occurred during the trading cycle: ", e);
            System.out.println("Error occurred during trading cycle. Check logs for details.");
        }

        logger.info("Trading system simulation finished.");
        System.out.println("Trading system simulation ended.");
    }
}
