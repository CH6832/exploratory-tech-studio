package com.system.banking.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    // Create a logger instance
    public static final Logger logger = LoggerFactory.getLogger(Logging.class);

    // Log an info message
    public static void info(String message) {
        logger.info(message);
    }

    // Log a debug message
    public static void debug(String message) {
        logger.debug(message);
    }

    // Log an error message with exception
    public static void severe(String message) {
        logger.error(message);
    }

    // Log a warn message
    public static void warning(String message) {
        logger.warn(message);
    }

    // Log a trace message
    public static void trace(String message) {
        logger.trace(message);
    }
}

