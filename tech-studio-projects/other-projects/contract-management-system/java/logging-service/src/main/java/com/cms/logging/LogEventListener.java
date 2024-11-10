package com.cms.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LogEventListener {

    private static final Logger logger = LoggerFactory.getLogger(LogEventListener.class);

    @RabbitListener(queuesToDeclare = @Queue("logQueue"))
    public void handleLogEvent(LogEvent logEvent) {
        switch (logEvent.getLevel().toLowerCase()) {
            case "info":
                logger.info("[" + logEvent.getServiceName() + "] " + logEvent.getMessage());
                break;
            case "warn":
                logger.warn("[" + logEvent.getServiceName() + "] " + logEvent.getMessage());
                break;
            case "error":
                logger.error("[" + logEvent.getServiceName() + "] " + logEvent.getMessage());
                break;
            default:
                logger.debug("[" + logEvent.getServiceName() + "] " + logEvent.getMessage());
        }
    }
}
