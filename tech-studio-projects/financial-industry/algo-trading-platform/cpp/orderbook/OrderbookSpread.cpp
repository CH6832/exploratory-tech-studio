#include "OrderbookSpread.h"

// Constructor implementation.
OrderbookSpread::OrderbookSpread(std::optional<long> bid, std::optional<long> ask)
    : Bid(bid), Ask(ask) {
    // Initialization is handled in the member initializer list.
}

// Getter for the bid price.
std::optional<long> OrderbookSpread::GetBid() const {
    return Bid;
}

// Getter for the ask price.
std::optional<long> OrderbookSpread::GetAsk() const {
    return Ask;
}

// Getter for the spread between ask and bid prices.
std::optional<long> OrderbookSpread::GetSpread() const {
    if (Bid.has_value() && Ask.has_value()) {
        return Ask.value() - Bid.value();
    }
    return std::nullopt; // Return std::nullopt if either Bid or Ask is not present.
}
