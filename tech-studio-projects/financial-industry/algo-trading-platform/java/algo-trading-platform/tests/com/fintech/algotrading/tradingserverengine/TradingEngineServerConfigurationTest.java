package com.fintech.algotrading.tradingserverengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TradingEngineServerConfigurationTest {

    private TradingEngineServerConfiguration config;
    private TradingEngineServerConfiguration.TradingEngineServerSettings settings;

    @BeforeEach
    public void setUp() {
        config = new TradingEngineServerConfiguration();
        settings = config.getTradingEngineServerSettings();
    }

    @Test
    public void testDefaultPort() {
        // Verify that the default port is set correctly
        assertEquals(0, settings.getPort(), "Default port should be 0");
    }

    @Test
    public void testSetPort() {
        // Set a new port value and verify it
        int newPort = 8080;
        settings.setPort(newPort);
        assertEquals(newPort, settings.getPort(), "Port should be updated to 8080");
    }

    @Test
    public void testGetTradingEngineServerSettings() {
        // Verify that the settings object is not null and is the expected instance
        assertNotNull(settings, "TradingEngineServerSettings should not be null");
        assertEquals(TradingEngineServerConfiguration.TradingEngineServerSettings.class,
                settings.getClass(), "Settings should be of type TradingEngineServerSettings");
    }

    @Test
    public void testConfigurationConstructor() {
        // Verify that the configuration object initializes with default settings
        assertNotNull(config.getTradingEngineServerSettings(), "Configuration should initialize with default settings");
    }
}
