package com.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClientConfig is a configuration class that provides a RestTemplate bean.
 * The RestTemplate is used to make HTTP requests to the server.
 */
@Configuration
public class ClientConfig {

    /**
     * Provides a RestTemplate bean to make HTTP requests.
     * 
     * @return a configured RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
