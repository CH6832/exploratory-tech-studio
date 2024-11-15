package com.system.banking.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for centralized logging functionality.
 * Provides static methods to log messages at different levels:
 * info, debug, error, warning, and trace.
 *
 * This class utilizes SLF4J (Simple Logging Facade for Java) with a
 * logger instance specifically for the Logging class.
 */
public class Logging {

    /**
     * Logger instance for the Logging utility class.
     * This instance is shared across all methods to log messages consistently.
     */
    public static final Logger logger = LoggerFactory.getLogger(Logging.class);

    /**
     * Logs an informational message.
     * Used for general application flow or state information that is
     * noteworthy but not necessarily indicative of any issues.
     *
     * @param message the informational message to log
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a debug message.
     * Typically used to provide detailed diagnostic information for debugging purposes.
     * This log level is useful during development and debugging sessions.
     *
     * @param message the debug message to log
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs an error message, generally for significant issues that
     * may prevent application functionality or indicate a failure.
     *
     * @param message the error message to log
     */
    public static void severe(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     * Used for potentially harmful situations that could lead to issues but
     * do not necessarily indicate immediate problems.
     *
     * @param message the warning message to log
     */
    public static void warning(String message) {
        logger.warn(message);
    }

    /**
     * Logs a trace message.
     * Provides very fine-grained logging information, typically used to trace
     * the flow of execution or record highly detailed events in the application.
     *
     * @param message the trace message to log
     */
    public static void trace(String message) {
        logger.trace(message);
    }
}
