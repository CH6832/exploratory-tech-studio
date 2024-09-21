#ifndef IRETRIEVAL_ORDERBOOK_H
#define IRETRIEVAL_ORDERBOOK_H

#include <vector>
#include <memory>
#include "OrderbookEntry.h"

// Abstract base class for an order book that can retrieve ask orders.
class IRetrievalOrderbook {
public:
    // Virtual destructor to ensure proper cleanup of derived classes.
    virtual ~IRetrievalOrderbook() = default;

    // Pure virtual method to get a list of ask orders.
    // @return A vector of shared pointers to OrderbookEntry objects representing ask orders.
    virtual std::vector<std::shared_ptr<OrderbookEntry>> GetAskOrders() const = 0;

    // Pure virtual method to get a list of bid orders.
    // @return A vector of shared pointers to OrderbookEntry objects representing bid orders.
    virtual std::vector<std::shared_ptr<OrderbookEntry>> GetBidOrders() const = 0;
};

#endif // IRETRIEVAL_ORDERBOOK_H
