#ifndef MATCH_RESULT_H
#define MATCH_RESULT_H

#include <vector>
#include <memory>
#include "Order.h"

// Class representing the result of a match operation in the order book.
class MatchResult {
public:
    // Constructor.
    MatchResult() = default;

    // Add other relevant methods and properties here.
    // For example, you might want to store matched orders or other details.

    // List of matched orders as a placeholder.
    std::vector<std::shared_ptr<Order>> MatchedOrders;
};

#endif // MATCH_RESULT_H
