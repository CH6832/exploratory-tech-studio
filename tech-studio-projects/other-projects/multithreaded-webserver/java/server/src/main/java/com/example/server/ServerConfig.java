package com.example.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * ServerConfig class configures the server’s thread pool and other settings such as timeouts.
 */
@Configuration
public class ServerConfig {

    /**
     * Configures a ThreadPoolTaskExecutor to handle incoming requests efficiently.
     * This will manage a pool of threads for handling concurrent requests.
     *
     * @return the configured ThreadPoolTaskExecutor
     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Set the core pool size to 10 threads
        executor.setMaxPoolSize(50);  // Set the maximum pool size to 50 threads
        executor.setQueueCapacity(100); // Set the queue capacity to 100 requests
        executor.setThreadNamePrefix("server-"); // Set the thread name prefix
        executor.initialize();
        return executor;
    }
}
