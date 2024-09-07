#ifndef ORDERBOOK_SPREAD_H
#define ORDERBOOK_SPREAD_H

#include <optional>

// The OrderbookSpread class represents the spread between bid and ask prices.
class OrderbookSpread {
public:
    // Constructor to initialize the OrderbookSpread object.
    // @param bid: Optional bid price.
    // @param ask: Optional ask price.
    OrderbookSpread(std::optional<long> bid, std::optional<long> ask);

    // Getter for the bid price.
    // @return The bid price if it is present.
    std::optional<long> GetBid() const;

    // Getter for the ask price.
    // @return The ask price if it is present.
    std::optional<long> GetAsk() const;

    // Getter for the spread between ask and bid prices.
    // @return The spread if both bid and ask are present; otherwise, returns std::nullopt.
    std::optional<long> GetSpread() const;

private:
    std::optional<long> Bid; // Optional bid price.
    std::optional<long> Ask; // Optional ask price.
};

#endif // ORDERBOOK_SPREAD_H
