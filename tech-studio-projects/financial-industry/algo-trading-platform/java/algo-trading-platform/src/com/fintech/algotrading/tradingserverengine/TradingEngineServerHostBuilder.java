package com.fintech.algotrading.tradingserverengine;

import com.fintech.algotrading.logging.ConsoleLogger;

//TradingEngineServerHostBuilder
public class TradingEngineServerHostBuilder {
 public static TradingEngineServer buildTradingEngineServer() {
     // Start with configuration
     TradingEngineServerConfiguration config = new TradingEngineServerConfiguration();
     // Set configuration options here (e.g., from a file or environment variables)
     config.getTradingEngineServerSettings().setPort(8080); // Example: setting the port

     // Create a logger
     ConsoleLogger consoleLogger = new ConsoleLogger();

     // Create the TradingEngineServer
     TradingEngineServer tradingEngineServer = new TradingEngineServer(consoleLogger, config);

     // Return the configured server
     return tradingEngineServer;
 }
}