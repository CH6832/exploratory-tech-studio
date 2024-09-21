#ifndef IREAD_ONLY_ORDERBOOK_H
#define IREAD_ONLY_ORDERBOOK_H

#include <memory>
#include "OrderbookSpread.h"

// Abstract base class for a read-only order book.
class IReadOnlyOrderbook {
public:
    // Virtual destructor to ensure proper cleanup of derived classes.
    virtual ~IReadOnlyOrderbook() = default;

    // Pure virtual method to check if the order book contains a specific order by ID.
    // @param orderId: The ID of the order to check.
    // @return True if the order book contains the order with the given ID; otherwise, false.
    virtual bool ContainsOrder(long orderId) const = 0;

    // Pure virtual method to get the current spread of the order book.
    // @return An OrderbookSpread object representing the current spread.
    virtual OrderbookSpread GetSpread() const = 0;

    // Pure virtual property to get the count of orders in the order book.
    // @return The number of orders in the order book.
    virtual int GetCount() const = 0;
};

#endif // IREAD_ONLY_ORDERBOOK_H
