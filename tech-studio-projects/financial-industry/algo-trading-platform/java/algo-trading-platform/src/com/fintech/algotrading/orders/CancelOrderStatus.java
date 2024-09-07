package com.fintech.algotrading.orders;

public class CancelOrderStatus {
    private String status;

    // Constructor to initialize status
    public CancelOrderStatus(String status) {
        this.status = status;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }
}
