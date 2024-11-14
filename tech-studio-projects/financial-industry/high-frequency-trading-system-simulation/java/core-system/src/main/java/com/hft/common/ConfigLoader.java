package com.hft.common;

import java.util.Properties;

/**
 * Loads and manages application configuration, supporting runtime adjustments to avoid restarts.
 */
public class ConfigLoader {

    private Properties config;

    public ConfigLoader() {
        loadConfig();
    }

    private void loadConfig() {
        // Load properties from file and handle caching for optimized access.
    }
}
