package com.fintech.algotrading.orderbook;

public interface IReadOnlyOrderbook {

    // Method to check if the order book contains a specific order by ID.
    // @param orderId The ID of the order to check.
    // @return True if the order book contains the order with the given ID; otherwise, false.
    boolean containsOrder(long orderId);

    // Method to get the current spread of the order book.
    // @return An OrderbookSpread object representing the current spread.
    OrderbookSpread getSpread();

    // Method to get the count of orders in the order book.
    // @return The number of orders in the order book.
    int getCount();
}
