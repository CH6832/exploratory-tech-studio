package com.fintech.algotrading.tradingserverengine;

public class TradingEngineServerConfiguration {

    // Inner class to represent the server settings
    public static class TradingEngineServerSettings {
        private int port;  // Default port value

        // Constructor with default values
        public TradingEngineServerSettings() {
            this.port = 0;  // Default port value, replace with an appropriate value
        }

        // Getter for port
        public int getPort() {
            return port;
        }

        // Setter for port
        public void setPort(int port) {
            this.port = port;
        }
    }

    private final TradingEngineServerSettings tradingEngineServerSettings;

    // Constructor
    public TradingEngineServerConfiguration() {
        this.tradingEngineServerSettings = new TradingEngineServerSettings();
    }

    // Getter for TradingEngineServerSettings (read-only)
    public TradingEngineServerSettings getTradingEngineServerSettings() {
        return tradingEngineServerSettings;
    }
}
