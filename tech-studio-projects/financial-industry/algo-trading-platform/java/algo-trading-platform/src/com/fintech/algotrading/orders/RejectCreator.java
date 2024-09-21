package com.fintech.algotrading.orders;

public class RejectCreator {
    
    // Static method to generate a Rejection object.
    public static Rejection generateOrderRejection(IOrderCore rejectedOrder, RejectionReason rejectionReason) {
        return new Rejection(rejectedOrder, rejectionReason);
    }
}
