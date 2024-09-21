package com.fintech.algotrading.orderbook;

import java.util.Optional;

public class OrderbookSpread {

    private final Optional<Long> bid;
    private final Optional<Long> ask;

    // Constructor
    public OrderbookSpread(Optional<Long> bid, Optional<Long> ask) {
        this.bid = bid;
        this.ask = ask;
    }

    // Getter for the bid price
    public Optional<Long> getBid() {
        return bid;
    }

    // Getter for the ask price
    public Optional<Long> getAsk() {
        return ask;
    }

    // Getter for the spread between ask and bid prices
    public Optional<Long> getSpread() {
        if (bid.isPresent() && ask.isPresent()) {
            return Optional.of(ask.get() - bid.get());
        }
        return Optional.empty(); // Return Optional.empty() if either bid or ask is not present
    }
}

