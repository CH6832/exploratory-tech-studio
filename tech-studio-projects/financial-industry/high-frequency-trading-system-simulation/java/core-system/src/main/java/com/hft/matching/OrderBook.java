package com.hft.matching;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Order book for managing buy and sell orders. Uses data structures optimized for lock-free access.
 */
public class OrderBook {
    private final ConcurrentSkipListMap<Double, Order> buyOrders = new ConcurrentSkipListMap<>((a, b) -> Double.compare(b, a)); // Descending for max buy
    private final ConcurrentSkipListMap<Double, Order> sellOrders = new ConcurrentSkipListMap<>();

    /**
     * Adds an order to the order book.
     * Utilizes lock-free data structures to ensure minimal latency.
     */
    public void addOrder(Order order) {
        if (order.isBuyOrder()) {
            buyOrders.put(order.getPrice(), order);
        } else {
            sellOrders.put(order.getPrice(), order);
        }
    }

    /**
     * Finds matching orders and executes trades.
     * @return boolean indicating if a match was found and executed.
     */
    public boolean matchOrders() {
        if (!buyOrders.isEmpty() && !sellOrders.isEmpty() &&
                buyOrders.firstKey() >= sellOrders.firstKey()) {
            buyOrders.pollFirstEntry();
            sellOrders.pollFirstEntry();
            return true;
        }
        return false;
    }
}

