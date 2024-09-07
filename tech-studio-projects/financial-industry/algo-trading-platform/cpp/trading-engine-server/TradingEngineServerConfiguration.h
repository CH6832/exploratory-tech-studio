#pragma once

#ifndef TRADING_ENGINE_SERVER_CONFIGURATION_H
#define TRADING_ENGINE_SERVER_CONFIGURATION_H

class TradingEngineServerConfiguration {
public:
    struct TradingEngineServerSettings {
        int Port;
    };

    TradingEngineServerConfiguration();

    TradingEngineServerSettings& GetTradingEngineServerSettings();
    const TradingEngineServerSettings& GetTradingEngineServerSettings() const;

private:
    TradingEngineServerSettings tradingEngineServerSettings;
};

#endif // TRADING_ENGINE_SERVER_CONFIGURATION_H
