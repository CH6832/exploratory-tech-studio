#pragma once

#include "IRetrievalOrderbook.h"
#include "MatchResult.h"

// Interface for an order book that supports matching orders.
class IMatchingOrderbook : public IRetrievalOrderbook {
public:
    // Pure virtual method to match orders and return the result.
    virtual MatchResult Match() = 0;
};
