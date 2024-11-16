package common;

import com.hft.common.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConfigLoaderTest {

    private ConfigLoader configLoader;

    @BeforeEach
    public void setup() {
        configLoader = new ConfigLoader();
    }

    @Test
    public void testLoadValidConfig() {
        configLoader.loadConfig("validConfig.properties");
        assertNotNull(configLoader.getConfigValue("someKey"), "Config value should not be null for valid keys.");
    }

    @Test
    public void testLoadInvalidConfig() {
        configLoader.loadConfig("invalidConfig.properties");
        assertNull(configLoader.getConfigValue("invalidKey"), "Config value should be null for invalid keys.");
    }

    @Test
    public void testGetConfigValue() {
        configLoader.loadConfig("validConfig.properties");
        String value = configLoader.getConfigValue("someKey");
        assertEquals("someValue", value, "Config value should match the expected value.");
    }

    @Test
    public void testGetMissingConfigValue() {
        configLoader.loadConfig("validConfig.properties");
        String missingValue = configLoader.getConfigValue("missingKey");
        assertNull(missingValue, "Config value for missing keys should be null.");
    }

    @Test
    public void testLoadConfigFromDifferentSources() {
        configLoader.loadConfigFromSource("url");
        assertNotNull(configLoader.getConfigValue("urlKey"), "Config value should be loaded from URL source.");
    }
}
