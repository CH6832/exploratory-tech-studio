package com.fintech.algotrading.orders;

public class OrderCore {
    private final long orderId;
    private final String username;
    private final int securityId;

    // Constructor implementation.
    public OrderCore(long orderId, String username, int securityId) {
        this.orderId = orderId;
        this.username = username;
        this.securityId = securityId;
    }

    // Getter for the Order ID.
    public long getOrderId() {
        return orderId;
    }

    // Getter for the Username.
    public String getUsername() {
        return username;
    }

    // Getter for the Security ID.
    public int getSecurityId() {
        return securityId;
    }
}
