package com.fintech.algotrading.tradingserverengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.fintech.algotrading.logging.ConsoleLogger;
import com.fintech.algotrading.tradingserverengine.TradingEngineServerConfiguration.TradingEngineServerSettings;

public class TradingEngineServerHostBuilderTest {

    @Test
    public void testBuildTradingEngineServer() {
        // Arrange
        // Mock the ConsoleLogger if necessary
        ConsoleLogger mockConsoleLogger = mock(ConsoleLogger.class);

        // Optionally, you can also mock the TradingEngineServerConfiguration
        TradingEngineServerConfiguration mockConfig = mock(TradingEngineServerConfiguration.class);
        TradingEngineServerSettings mockSettings = mock(TradingEngineServerSettings.class);
        when(mockConfig.getTradingEngineServerSettings()).thenReturn(mockSettings);

        // Stub the settings method to return a specific port
        when(mockSettings.getPort()).thenReturn(8080);

        // Replace the static method with mocked configuration and logger
        TradingEngineServerHostBuilder builder = new TradingEngineServerHostBuilder() {
            public TradingEngineServer buildTradingEngineServer() {
                return new TradingEngineServer(mockConsoleLogger, mockConfig);
            }
        };

        // Act
        TradingEngineServer server = builder.buildTradingEngineServer();

        // Assert
        assertNotNull(server, "TradingEngineServer should be created.");
        assertEquals(mockConsoleLogger, ((Object) server).getLogger(), "Logger should match the provided ConsoleLogger.");
        assertEquals(8080, server.getConfiguration().getTradingEngineServerSettings().getPort(), "Port should be set correctly.");
    }

	private ConsoleLogger mock(Class<TradingEngineServerSettings> class1) {
		// TODO Auto-generated method stub
		return null;
	}
}
