package com.fintech.algotrading.orders;

public class Rejection {
    private final IOrderCore rejectedOrder;
    private final RejectionReason rejectionReason;

    // Constructor initializes the Rejection with the order and reason.
    public Rejection(IOrderCore rejectedOrder, RejectionReason rejectionReason) {
        this.rejectedOrder = rejectedOrder;
        this.rejectionReason = rejectionReason;
    }

    // Getter for the order ID.
    public long getOrderId() {
        return rejectedOrder.getOrderId();
    }

    // Getter for the username.
    public long getUsername() {  // Assuming username is a String, adjust the type if necessary
        return rejectedOrder.getUsername();
    }

    // Getter for the security ID.
    public long getSecurityId() {
        return rejectedOrder.getSecurityId();
    }

    // Getter for the rejection reason.
    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }
}
