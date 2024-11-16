package com.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson configuration class for customizing the Jackson ObjectMapper.
 * Jackson is a popular JSON library for Java, used to convert Java objects to JSON and vice versa.
 * This configuration class sets up a customized ObjectMapper for the application.
 */
@Configuration
public class JacksonConfig {

    /**
     * Bean definition for ObjectMapper.
     * The ObjectMapper is used to map Java objects to JSON and vice versa. It is highly configurable.
     * Here, we disable a specific feature to prevent errors when serializing empty beans.
     *
     * @return A customized ObjectMapper with specific configuration.
     */
    @Bean
    ObjectMapper objectMapper() {
        // Create a new instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Disable FAIL_ON_EMPTY_BEANS feature to prevent errors when trying to serialize empty beans
        // By default, Jackson throws an exception if it encounters an empty Java bean.
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        // Return the configured ObjectMapper
        return objectMapper;
    }
}
