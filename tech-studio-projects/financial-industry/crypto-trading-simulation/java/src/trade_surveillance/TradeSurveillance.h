#ifndef TRADE_SURVEILLANCE_H
#define TRADE_SURVEILLANCE_H

#include "Order.h"
#include "TradeAlert.h"

class TradeSurveillance {
public:
    static TradeSurveillance& getInstance() {
        static TradeSurveillance instance;
        return instance;
    }

    void monitorTrade(Order order);
    
private:
    TradeSurveillance() = default;
    bool isSuspicious(const Order& order);
};

#endif // TRADE_SURVEILLANCE_H
