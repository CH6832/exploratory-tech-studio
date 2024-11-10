package com.cms.contract;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cms.logging.LogEvent;

/**
 * ContractLoggingClient is a service responsible for sending log events to a messaging queue.
 * <p>
 * This service allows for logging contract-related activities by publishing 
 * log events to an AMQP message queue, making it possible to handle logs in 
 * a centralized logging service.
 * </p>
 */
@Service
public class ContractLoggingClient {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Constructor for ContractLoggingClient.
     * 
     * @param rabbitTemplate the RabbitTemplate used to send messages to the queue
     */
    public ContractLoggingClient(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a log event to the specified queue.
     * <p>
     * This method creates a LogEvent object with the provided level and message
     * and sends it to the "logQueue" for processing by the logging service.
     * </p>
     * 
     * @param level the severity level of the log (e.g., "INFO", "ERROR")
     * @param message the log message providing details of the event
     */
    public void log(String level, String message) {
        try {
            LogEvent logEvent = new LogEvent();
            logEvent.setLevel(level);
            logEvent.setMessage(message);
            
            rabbitTemplate.convertAndSend("logQueue", logEvent);
        } catch (AmqpException e) {
            // Handle the error (e.g., log to a file, or rethrow with more context)
            e.printStackTrace();
        }
    }
}
