package com.fintech.algotrading.orderbook;

import java.util.List;

public interface IRetrievalOrderbook {

    // Method to get a list of ask orders.
    // @return A list of OrderbookEntry objects representing ask orders.
    List<OrderbookEntry> getAskOrders();

    // Method to get a list of bid orders.
    // @return A list of OrderbookEntry objects representing bid orders.
    List<OrderbookEntry> getBidOrders();
}