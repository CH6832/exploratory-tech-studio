#ifndef ORDERBOOK_H
#define ORDERBOOK_H

#include <map>
#include <set>
#include <memory>
#include <list>
#include "Order.h"
#include "Limit.h"
#include "OrderbookEntry.h"
#include "OrderbookSpread.h"

// Forward declaration of the Security class.
class Security;

// Compare functor for ordering Limits in the bid and ask SortedSets.
struct AskLimitComparer {
    bool operator()(const std::shared_ptr<Limit>& lhs, const std::shared_ptr<Limit>& rhs) const;
};

struct BidLimitComparer {
    bool operator()(const std::shared_ptr<Limit>& lhs, const std::shared_ptr<Limit>& rhs) const;
};

// The Orderbook class represents an order book and supports order management.
class Orderbook {
public:
    // Constructor to initialize the Orderbook with a security instrument.
    // @param instrument: The security instrument for this order book.
    explicit Orderbook(std::shared_ptr<Security> instrument);

    // Getter for the number of orders in the order book.
    // @return The count of orders in the order book.
    int GetCount() const;

    // Adds an order to the order book.
    // @param order: The order to be added.
    void AddOrder(const std::shared_ptr<Order>& order);

    // Changes an existing order based on a modify order request.
    // @param modifyOrder: The modify order request.
    void ChangeOrder(const std::shared_ptr<ModifyOrder>& modifyOrder);

    // Checks if an order exists in the order book.
    // @param orderId: The ID of the order to check.
    // @return True if the order exists; otherwise, false.
    bool ContainsOrder(long orderId) const;

    // Gets all bid orders in the order book.
    // @return A list of bid order book entries.
    std::list<std::shared_ptr<OrderbookEntry>> GetBidOrders() const;

    // Gets all ask orders in the order book.
    // @return A list of ask order book entries.
    std::list<std::shared_ptr<OrderbookEntry>> GetAskOrders() const;

    // Gets the current spread between the best bid and ask prices.
    // @return An OrderbookSpread representing the current spread.
    OrderbookSpread GetSpread() const;

    // Removes an order from the order book based on a cancel order request.
    // @param cancelOrder: The cancel order request.
    void RemoveOrder(const std::shared_ptr<CancelOrder>& cancelOrder);

private:
    // Adds an order to a specific limit level.
    // @param order: The order to be added.
    // @param baseLimit: The limit to which the order is added.
    // @param limitLevels: The sorted set of limit levels.
    // @param internalBook: The internal book of orders.
    static void AddOrderToLimit(const std::shared_ptr<Order>& order,
        const std::shared_ptr<Limit>& baseLimit,
        std::set<std::shared_ptr<Limit>, BidLimitComparer>& limitLevels,
        std::map<long, std::shared_ptr<OrderbookEntry>>& internalBook);

    // Removes an order from a specific limit level.
    // @param cancelOrder: The cancel order request.
    // @param orderbookEntry: The order book entry to be removed.
    // @param internalBook: The internal book of orders.
    static void RemoveOrderFromLimit(const std::shared_ptr<CancelOrder>& cancelOrder,
        std::shared_ptr<OrderbookEntry> orderbookEntry,
        std::map<long, std::shared_ptr<OrderbookEntry>>& internalBook);

    std::shared_ptr<Security> instrument; // The security instrument associated with this order book.
    std::map<long, std::shared_ptr<OrderbookEntry>> orders; // Map of orders by their ID.
    std::set<std::shared_ptr<Limit>, AskLimitComparer> askLimits; // Set of ask limits.
    std::set<std::shared_ptr<Limit>, BidLimitComparer> bidLimits; // Set of bid limits.
};

#endif // ORDERBOOK_H
