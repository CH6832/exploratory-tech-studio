#include "Orderbook.h"

// Compare functor for ordering Ask Limits.
bool AskLimitComparer::operator()(const std::shared_ptr<Limit>& lhs, const std::shared_ptr<Limit>& rhs) const {
    return lhs->GetPrice() < rhs->GetPrice(); // Replace with actual comparison logic.
}

// Compare functor for ordering Bid Limits.
bool BidLimitComparer::operator()(const std::shared_ptr<Limit>& lhs, const std::shared_ptr<Limit>& rhs) const {
    return lhs->GetPrice() > rhs->GetPrice(); // Replace with actual comparison logic.
}

// Constructor implementation.
Orderbook::Orderbook(std::shared_ptr<Security> instrument)
    : instrument(instrument) {
}

// Getter for the number of orders.
int Orderbook::GetCount() const {
    return orders.size();
}

// Add an order to the order book.
void Orderbook::AddOrder(const std::shared_ptr<Order>& order) {
    auto baseLimit = std::make_shared<Limit>(order->GetPrice());
    AddOrderToLimit(order, baseLimit, order->IsBuySide() ? bidLimits : askLimits, orders);
}

// Static method to add an order to a limit level.
void Orderbook::AddOrderToLimit(const std::shared_ptr<Order>& order,
    const std::shared_ptr<Limit>& baseLimit,
    std::set<std::shared_ptr<Limit>, BidLimitComparer>& limitLevels,
    std::map<long, std::shared_ptr<OrderbookEntry>>& internalBook) {
    auto it = limitLevels.find(baseLimit);
    if (it != limitLevels.end()) {
        auto limit = *it;
        auto orderbookEntry = std::make_shared<OrderbookEntry>(order, baseLimit);
        if (limit->GetHead() == nullptr) {
            limit->SetHead(orderbookEntry);
            limit->SetTail(orderbookEntry);
        }
        else {
            auto tailPointer = limit->GetTail();
            tailPointer->SetNext(orderbookEntry);
            orderbookEntry->SetPrevious(tailPointer);
            limit->SetTail(orderbookEntry);
        }
        internalBook[order->GetOrderId()] = orderbookEntry;
    }
    else {
        limitLevels.insert(baseLimit);
        auto orderbookEntry = std::make_shared<OrderbookEntry>(order, baseLimit);
        baseLimit->SetHead(orderbookEntry);
        baseLimit->SetTail(orderbookEntry);
        internalBook[order->GetOrderId()] = orderbookEntry;
    }
}

// Change an existing order.
void Orderbook::ChangeOrder(const std::shared_ptr<ModifyOrder>& modifyOrder) {
    auto it = orders.find(modifyOrder->GetOrderId());
    if (it != orders.end()) {
        auto orderbookEntry = it->second;
        RemoveOrderFromLimit(modifyOrder->ToCancelOrder(), orderbookEntry, orders);
        AddOrder(modifyOrder->ToNewOrder());
    }
}

// Check if an order exists.
bool Orderbook::ContainsOrder(long orderId) const {
    return orders.find(orderId) != orders.end();
}

// Get all bid orders.
std::list<std::shared_ptr<OrderbookEntry>> Orderbook::GetBidOrders() const {
    std::list<std::shared_ptr<OrderbookEntry>> orderbookEntries;
    for (const auto& bidLimit : bidLimits) {
        if (bidLimit->IsEmpty()) continue;
        auto entry = bidLimit->GetHead();
        while (entry != nullptr) {
            orderbookEntries.push_back(entry);
            entry = entry->GetNext();
        }
    }
    return orderbookEntries;
}

// Get all ask orders.
std::list<std::shared_ptr<OrderbookEntry>> Orderbook::GetAskOrders() const {
    std::list<std::shared_ptr<OrderbookEntry>> orderbookEntries;
    for (const auto& askLimit : askLimits) {
        if (askLimit->IsEmpty()) continue;
        auto entry = askLimit->GetHead();
        while (entry != nullptr) {
            orderbookEntries.push_back(entry);
            entry = entry->GetNext();
        }
    }
    return orderbookEntries;
}

// Get the current spread.
OrderbookSpread Orderbook::GetSpread() const {
    std::shared_ptr<Limit> bestAsk = nullptr;
    std::shared_ptr<Limit> bestBid = nullptr;

    if (!askLimits.empty() && !askLimits.begin()->IsEmpty()) {
        bestAsk = *askLimits.begin();
    }

    if (!bidLimits.empty() && !bidLimits.rbegin()->IsEmpty()) {
        bestBid = *bidLimits.rbegin();
    }

    return OrderbookSpread(bestBid ? std::make_optional(bestBid->GetPrice()) : std::nullopt,
        bestAsk ? std::make_optional(bestAsk->GetPrice()) : std::nullopt);
}

// Remove an order from the order book.
void Orderbook::RemoveOrder(const std::shared_ptr<CancelOrder>& cancelOrder) {
    auto it = orders.find(cancelOrder->GetOrderId());
    if (it != orders.end()) {
        RemoveOrderFromLimit(cancelOrder, it->second, orders);
    }
}

// Static method to remove an order from a limit level.
void Orderbook::RemoveOrderFromLimit(const std::shared_ptr<CancelOrder>& cancelOrder,
    std::shared_ptr<OrderbookEntry> orderbookEntry,
    std::map<long, std::shared_ptr<OrderbookEntry>>& internalBook) {
    auto limit = orderbookEntry->GetParentLimit();
    if (orderbookEntry->GetPrevious() && orderbookEntry->GetNext()) {
        orderbookEntry->GetNext()->SetPrevious(orderbookEntry->GetPrevious());
        orderbookEntry->GetPrevious()->SetNext(orderbookEntry->GetNext());
    }
    else if (orderbookEntry->GetPrevious()) {
        orderbookEntry->GetPrevious()->SetNext(nullptr);
    }
    else if (orderbookEntry->GetNext()) {
        orderbookEntry->GetNext()->SetPrevious(nullptr);
    }

    if (limit->GetHead() == orderbookEntry && limit->GetTail() == orderbookEntry) {
        limit->SetHead(nullptr);
        limit->SetTail(nullptr);
    }
    else if (limit->GetHead() == orderbookEntry) {
        limit->SetHead(orderbookEntry->GetNext());
    }
    else if (limit->GetTail() == orderbookEntry) {
        limit->SetTail(orderbookEntry->GetPrevious());
    }

    internalBook.erase(cancelOrder->GetOrderId());
}
