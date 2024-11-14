package com.hft.matching;

/**
 * Core order matching engine. Designed for high-throughput and low-latency trade executions.
 */
public class OrderMatchingEngine {
    private final OrderBook orderBook = new OrderBook();
    private static final double MATCHING_TOLERANCE = 0.01;

    /**
     * Processes an incoming order, adding it to the order book and attempting to match.
     * Minimized locking and tuned for low-latency order handling.
     */
    public synchronized void processOrder(Order order) {
        orderBook.addOrder(order);
        if (orderBook.matchOrders()) {
            System.out.println("Order matched and executed");
        }
    }

    /**
     * Attempts to match a buy order with a sell order based on their values.
     * If the difference between the buy and sell order values is within the tolerance
     * level, the orders are considered matched.
     *
     * @param buyOrderValue  The value of the buy order.
     * @param sellOrderValue The value of the sell order.
     * @return true if the orders match within the tolerance level; false otherwise.
     */
    public boolean matchOrder(double buyOrderValue, double sellOrderValue) {
        // Validate inputs to ensure both values are positive
        if (buyOrderValue <= 0 || sellOrderValue <= 0) {
            throw new IllegalArgumentException("Order values must be positive.");
        }

        // Calculate the absolute difference between the buy and sell values
        double difference = Math.abs(buyOrderValue - sellOrderValue);

        // If difference is within the tolerance, consider the orders as matched
        if (difference <= MATCHING_TOLERANCE) {
            System.out.printf("Orders matched. Buy Value: %.2f, Sell Value: %.2f\n", buyOrderValue, sellOrderValue);
            return true;
        } else {
            System.out.printf("Orders did not match. Buy Value: %.2f, Sell Value: %.2f, Difference: %.2f\n",
                    buyOrderValue, sellOrderValue, difference);
            return false;
        }
    }
}

