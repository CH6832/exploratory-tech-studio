package com.fintech.algotrading.orderbook;

import java.util.List;

public interface IOrderEntryOrderbook {

    // Method to add an order entry.
    void addOrderEntry(OrderbookEntry entry);

    // Method to remove an order entry by order ID.
    void removeOrderEntry(long orderId);

    // Method to retrieve all order entries.
    List<OrderbookEntry> getAllOrderEntries();

    // Note: No destructor needed in Java as garbage collection handles cleanup.
}
