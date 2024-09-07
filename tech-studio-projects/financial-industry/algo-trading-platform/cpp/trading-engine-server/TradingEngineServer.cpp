#include "TradingEngineServer.h"
#include <future>

using namespace std;

TradingEngineServer::TradingEngineServer(std::shared_ptr<ILogger> logger, std::shared_ptr<TradingEngineServerConfiguration> config)
    : _logger(logger), _tradingEngineServerConfig(config), _stopping(false) {
    ITextLogger _logger;
    
    if (!_logger) {
        throw std::invalid_argument("logger");
    }
    if (!_tradingEngineServerConfig) {
        throw std::invalid_argument("config");
    }
}

future<void> TradingEngineServer::Run(std::shared_future<void> cancellationToken) {
    _logger->LogInformation("Started TradingServer");
    // _logger.Information(nameof(TradigngEngineServer), Stargin Trading Engine)

    // Create a promise and future to manage thread completion
    _promise = std::promise<void>();
    std::future<void> future = _promise.get_future();

    // Start the Execute method in a separate thread
    _thread = std::thread(&TradingEngineServer::Execute, this, cancellationToken);

    // Return the future that will be set when the thread completes
    return future;
}

void TradingEngineServer::Stop() {
    _stopping = true;
    if (_thread.joinable()) {
        _thread.join();
    }
}

void TradingEngineServer::Execute(std::shared_future<void> cancellationToken) {
    _logger->LogInformation("Started TradingEngineServer::Execute");
    
    try {
        while (cancellationToken.wait_for(std::chrono::milliseconds(1)) == std::future_status::timeout) {
            // Simulate trading operations
            std::this_thread::sleep_for(std::chrono::milliseconds(100));
            _logger->LogInformation("Running trading engine operations...");
        }
    }
    catch (const std::exception& e) {
        _logger->LogError("Exception in TradingEngineServer::Execute: " + std::string(e.what()));
    }
    catch (...) {
        _logger->LogError("Unknown exception in TradingEngineServer::Execute");
    }

    _logger->LogInformation("TradingEngineServer stopped.");

    // Set the promise to signal that the thread has completed
    _promise.set_value();
}

