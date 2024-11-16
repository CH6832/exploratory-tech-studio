package com.system.simulation.network.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * ConfigLoader is used to load configuration files into corresponding Java objects.
 */
public class ConfigLoader {

    private ObjectMapper objectMapper = null;

    public ConfigLoader() {
        this.objectMapper = objectMapper;
    }

    /**
     * Loads configuration from a specified file.
     *
     * @param <T>      the type of the configuration object.
     * @param filePath the path to the configuration file.
     * @return the configuration object.
     * @throws IOException if there is an error reading the file.
     */
    public <T> T load(String filePath) throws IOException {
        Class<? extends T> clazz = null;
        return objectMapper.readValue(new File(filePath), clazz);
    }
}
