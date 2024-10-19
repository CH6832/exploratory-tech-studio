#include "MarketMaker.h"
#include <iostream>

MarketMaker::MarketMaker() : orderRouter(), riskManager(), marketDataFeed() {}

void MarketMaker::start() {
    std::cout << "Starting Market Maker..." << std::endl;
    marketDataFeed.start();
    orderRouter.start();
}

void MarketMaker::stop() {
    std::cout << "Stopping Market Maker..." << std::endl;
    orderRouter.stop();
    marketDataFeed.stop();
}
