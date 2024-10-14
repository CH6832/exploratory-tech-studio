package com.contractmanagement.system;

import com.contractmanagement.system.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(Notification notification) {
        // Send the notification to the message broker (RabbitMQ)
        rabbitTemplate.convertAndSend("notificationExchange", "notificationRoutingKey", notification);
    }
}
