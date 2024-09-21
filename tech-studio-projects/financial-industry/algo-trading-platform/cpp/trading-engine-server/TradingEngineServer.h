#pragma once

#ifndef TRADING_ENGINE_SERVER_H
#define TRADING_ENGINE_SERVER_H

#include <thread>
#include <future>
#include <memory>
#include "ITradingEngineServer.h"
#include "TradingEngineServerConfiguration.h"
#include "../logging/ILogger.h"

class TradingEngineServer : public ITradingEngineServerEngine {
public:
    TradingEngineServer(std::shared_ptr<ILogger> logger, std::shared_ptr<TradingEngineServerConfiguration> config);

    std::future<void> Run(std::shared_future<void> cancellationToken) override;
    void Stop();

private:
    void Execute(std::shared_future<void> cancellationToken);

    std::shared_ptr<ILogger> _logger;
    std::shared_ptr<TradingEngineServerConfiguration> _tradingEngineServerConfig;
    std::shared_ptr<TradingEngineServerConfiguration> _config;
    std::thread _thread;
    std::promise<void> _promise;
    std::atomic<bool> _stopping; // Declare _stopping as a member

};

#endif // TRADING_ENGINE_SERVER_H
