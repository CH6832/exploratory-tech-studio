#pragma once

#ifndef I_TRADING_ENGINE_SERVER_ENGINE_H
#define I_TRADING_ENGINE_SERVER_ENGINE_H

#include <future>
#include <atomic>

class ITradingEngineServerEngine {
public:
    virtual std::future<void> Run(std::shared_future<void> cancellationToken) = 0;
    virtual ~ITradingEngineServerEngine() = default;
};

#endif // I_TRADING_ENGINE_SERVER_ENGINE_H
