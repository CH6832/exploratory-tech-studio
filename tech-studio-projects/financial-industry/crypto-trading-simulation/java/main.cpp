#include "../utils/Config.h"
#include "../core/market_maker/MarketMaker.h"

int main() {
    // Load the configuration from a JSON file
    Config::loadConfig("config.json");

    // Start the MarketMaker strategy
    MarketMaker marketMaker;
    marketMaker.start();

    return 0;
}
