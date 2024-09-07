#pragma once

#include <vector>
#include "OrderbookEntry.h"

// Interface for an order book that manages order entries.
class IOrderEntryOrderbook {
public:
    // Pure virtual method to add an order entry.
    virtual void AddOrderEntry(const OrderbookEntry& entry) = 0;

    // Pure virtual method to remove an order entry by order ID.
    virtual void RemoveOrderEntry(long orderId) = 0;

    // Pure virtual method to retrieve all order entries.
    virtual std::vector<OrderbookEntry> GetAllOrderEntries() const = 0;

    // Virtual destructor to ensure proper cleanup of derived classes.
    virtual ~IOrderEntryOrderbook() = default;
};
