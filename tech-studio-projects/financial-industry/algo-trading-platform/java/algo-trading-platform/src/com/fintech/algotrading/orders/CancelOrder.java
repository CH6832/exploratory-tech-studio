package com.fintech.algotrading.orders;

public class CancelOrder {
	
    // Assuming CancelOrder class has some attributes like orderId
    private long orderId;	
	
    // Static method to generate a CancelOrderStatus
    public static CancelOrderStatus generateCancelOrderStatus(CancelOrder co) {
        // Create and return a new CancelOrderStatus object.
        return new CancelOrderStatus(null);
    }

    // Static method to generate a NewOrderStatus
    public static NewOrderStatus generateNewOrderStatus(Order order) {
        // Create and return a new NewOrderStatus object.
        return new NewOrderStatus();
    }

    // Static method to generate a ModifyOrderStatus
    public static ModifyOrderStatus generateModifyOrderStatus(ModifyOrder modifyOrder) {
        // Create and return a new ModifyOrderStatus object.
        return new ModifyOrderStatus();
    }

    public long getOrderId() {
        return orderId;
    }
}
