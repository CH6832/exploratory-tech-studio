package com.fintech.algotrading.orderbook;

public class OrderRecord {

    private final long orderId;
    private final int quantity;
    private final long price;
    private final boolean isBuySide;
    private final String username;
    private final int securityId;
    private final int theoreticalQueuePosition;

    // Constructor to initialize all members
    public OrderRecord(long orderId, int quantity, long price, boolean isBuySide,
                       String username, int securityId, int theoreticalQueuePosition) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.isBuySide = isBuySide;
        this.username = username;
        this.securityId = securityId;
        this.theoreticalQueuePosition = theoreticalQueuePosition;
    }

    // Getter methods for all fields
    public long getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public boolean isBuySide() {
        return isBuySide;
    }

    public String getUsername() {
        return username;
    }

    public int getSecurityId() {
        return securityId;
    }

    public int getTheoreticalQueuePosition() {
        return theoreticalQueuePosition;
    }
}
