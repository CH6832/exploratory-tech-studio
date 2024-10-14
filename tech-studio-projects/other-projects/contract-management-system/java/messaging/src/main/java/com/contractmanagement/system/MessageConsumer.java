package com.contractmanagement.system;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "contract_queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // Process the message
    }
}
