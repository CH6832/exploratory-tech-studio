package com.contractmanagement.system;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "contract_queue";

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        System.out.println("Sent message: " + message);
    }
}

