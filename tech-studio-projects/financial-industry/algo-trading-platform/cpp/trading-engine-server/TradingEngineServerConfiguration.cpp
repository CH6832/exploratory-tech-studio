#include "TradingEngineServerConfiguration.h"

using namespace std;

TradingEngineServerConfiguration::TradingEngineServerConfiguration() {
    // Initialize with default values if needed
    tradingEngineServerSettings.Port = 0;  // Default port, replace with an appropriate value
}

TradingEngineServerConfiguration::TradingEngineServerSettings&
TradingEngineServerConfiguration::GetTradingEngineServerSettings() {
    return tradingEngineServerSettings;
}

const TradingEngineServerConfiguration::TradingEngineServerSettings&
TradingEngineServerConfiguration::GetTradingEngineServerSettings() const {
    return tradingEngineServerSettings;
}
