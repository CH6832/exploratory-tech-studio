package com.fintech.algotrading.orders;

import java.util.Objects;

public class RejectionReason {
    private final String reason;

    // Constructor initializes the rejection reason.
    public RejectionReason(String reason) {
        this.reason = reason;
    }

    // Getter for the reason.
    public String getReason() {
        return reason;
    }

    // Comparison method.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RejectionReason that = (RejectionReason) obj;
        return Objects.equals(reason, that.reason);
    }

    // Override hashCode to maintain contract between equals and hashCode.
    @Override
    public int hashCode() {
        return Objects.hash(reason);
    }
}
