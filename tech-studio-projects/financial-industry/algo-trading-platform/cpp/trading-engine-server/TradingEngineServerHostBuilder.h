#pragma once

#ifndef TRADING_ENGINE_SERVER_HOST_BUILDER_H
#define TRADING_ENGINE_SERVER_HOST_BUILDER_H

#include <memory>
#include "TradingEngineServer.h"
#include "TradingEngineServerConfiguration.h"

class TradingEngineServerHostBuilder {
public:
    static std::shared_ptr<TradingEngineServer> BuildTradingEngineServer();
};

#endif // TRADING_ENGINE_SERVER_HOST_BUILDER_H
