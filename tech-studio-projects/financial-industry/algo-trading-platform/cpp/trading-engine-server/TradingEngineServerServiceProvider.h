#pragma once

#ifndef TRADING_ENGINE_SERVER_SERVICE_PROVIDER_H
#define TRADING_ENGINE_SERVER_SERVICE_PROVIDER_H

#include <memory>
#include <unordered_map>
#include <typeindex>
#include <stdexcept>

class TradingEngineServerServiceProvider {
public:
    template<typename T>
    static void RegisterService(std::shared_ptr<T> service) {
        services[typeid(T)] = service;
    }

    template<typename T>
    static std::shared_ptr<T> GetService() {
        auto it = services.find(typeid(T));
        if (it != services.end()) {
            return std::static_pointer_cast<T>(it->second);
        }
        throw std::runtime_error("Service not found");
    }

private:
    static std::unordered_map<std::type_index, std::shared_ptr<void>> services;
};

#endif // TRADING_ENGINE_SERVER_SERVICE_PROVIDER_H
