#include "TradingEngineServerHostBuilder.h"
#include <iostream>
#include "../logging/ConsoleLogger.h"

using namespace std;

shared_ptr<TradingEngineServer> TradingEngineServerHostBuilder::BuildTradingEngineServer() {
    // Start with configuration
    auto config = std::make_shared<TradingEngineServerConfiguration>();
    // Set configuration options here (e.g., from a file or environment variables)
    config->GetTradingEngineServerSettings().Port = 8080; // Example: setting the port

    auto logger = make_shared<ConsoleLogger>();
    // services.AddSingleton<ITextLogger, >();
    /*
    * 
    * 
    ** /

    // Create the TradingEngineServer
    auto tradingEngineServer = make_shared<TradingEngineServer>(logger, config);

    // Return the configured server
    return tradingEngineServer;
}
