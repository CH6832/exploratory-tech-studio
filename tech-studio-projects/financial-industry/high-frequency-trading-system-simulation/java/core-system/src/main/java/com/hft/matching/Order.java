package com.hft.matching;

/**
 * Represents a trading order in the system. Optimized for low-latency operations.
 */
public class Order {
    private final String orderId;
    private final double quantity;
    private final double price;
    private final boolean isBuyOrder;

    // Constructor optimized for immutability and efficient memory usage
    public Order(String orderId, double quantity, double price, boolean isBuyOrder) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.isBuyOrder = isBuyOrder;
    }

    // Getters
    public String getOrderId() { return orderId; }
    public double getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public boolean isBuyOrder() { return isBuyOrder; }
}

