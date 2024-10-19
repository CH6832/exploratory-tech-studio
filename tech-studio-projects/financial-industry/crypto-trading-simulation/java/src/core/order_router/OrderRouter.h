#ifndef ORDER_ROUTER_H
#define ORDER_ROUTER_H

#include <string>

class OrderRouter {
public:
    OrderRouter();                               // Constructor
    void routeOrder(const std::string& symbol, const std::string& side, double quantity, double price);
    void start();                                // Initialize order routing system
    void stop();                                 // Stop order routing system

private:
    std::string api_key;                        // API key for Binance
    std::string api_secret;                     // API secret for Binance
    void loadApiKeys();                         // Load API keys from configuration
};

#endif // ORDER_ROUTER_H
