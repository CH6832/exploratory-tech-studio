package com.fintech.algotrading.orderbook;

public interface IMatchingOrderbook extends IRetrievalOrderbook {
    // Method to match orders and return the result.
    MatchResult match();
}
