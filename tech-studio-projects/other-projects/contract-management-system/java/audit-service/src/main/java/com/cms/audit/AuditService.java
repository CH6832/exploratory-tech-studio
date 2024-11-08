package com.cms.audit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The AuditService class provides logging functionality for auditing actions
 * within the system. It logs actions at different log levels (debug, info, warn, error)
 * to track important events and possible issues in the system.
 * 
 * This service is intended to be used wherever an action needs to be logged for
 * auditing purposes, such as tracking user actions or system behavior.
 * 
 * The log levels allow the flexibility to adjust the verbosity of logs for different environments:
 * - DEBUG for detailed information useful in development or debugging.
 * - INFO for general operational messages.
 * - WARN for potentially harmful situations.
 * - ERROR for severe issues that need attention.
 */
@Service  // This annotation marks the class as a Spring service, making it available for dependency injection
public class AuditService {

    /**
     * Logger instance for the AuditService class.
     * The LogManager is used to create a logger object to log messages at various levels.
     * This logger will write log entries to the configured destinations (e.g., console, files).
     */
    private static final Logger logger = LogManager.getLogger(AuditService.class);

    /**
     * Logs an action with different log levels to capture varying degrees of information.
     * This method demonstrates how different log levels can be used to log the same action
     * with different levels of severity.
     * 
     * @param action A string representing the action to be logged.
     *               This could be an action such as "User created contract" or "System processed payment".
     */
    public void logAction(String action) {

        // Log at the DEBUG level for detailed, verbose information useful for debugging
        // This would include details about the action being performed that may be useful for development
        logger.debug("Debug log for action: {}", action);

        // Log at the INFO level to record standard operational information
        // This logs the general action being performed, useful for tracking routine operations
        logger.info("Info log for action: {}", action);

        // Log at the WARN level for situations that might indicate a potential issue or unexpected state
        // This is helpful when something might be wrong but does not stop the application from functioning
        logger.warn("Warning log for action: {}", action);

        // Log at the ERROR level for severe issues that require immediate attention
        // This should be used when something goes wrong and needs to be investigated
        logger.error("Error log for action: {}", action);
    }
}
