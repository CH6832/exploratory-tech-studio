package com.contractmanagement.system;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue contractQueue() {
        return new Queue("contract_queue", true); // true for durable
    }
}