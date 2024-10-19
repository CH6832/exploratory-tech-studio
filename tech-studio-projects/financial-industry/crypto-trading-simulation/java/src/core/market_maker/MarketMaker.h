#ifndef MARKET_MAKER_H
#define MARKET_MAKER_H

#include "OrderRouter.h"
#include "RiskManager.h"
#include "MarketDataFeed.h"

class MarketMaker {
public:
    MarketMaker();
    void start();
    void stop();

private:
    OrderRouter orderRouter;
    RiskManager riskManager;
    MarketDataFeed marketDataFeed;
};

#endif // MARKET_MAKER_H
