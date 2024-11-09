package com.system.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {
    private static final Logger logger = LogManager.getLogger(AppLogger.class);

    // Info level logging
    public static void info(String responseMessage) {
        logger.info(responseMessage);
    }
    public static void info(String message, String text) {
        logger.info(message);
    }
    public static void info(String s, String methodName, long elapsedTime) {
        logger.info(s);
    }

    // Debug level logging
    public static void debug(String message) {
        logger.debug(message);
    }

    // Error level logging
    public static void error(String message) {
        logger.error(message);
    }

    // Warning level logging
    public static void warn(String message) {
        logger.warn(message);
    }

    // Fatal level logging
    public static void fatal(String message) {
        logger.fatal(message);
    }

    // Exception logging
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void error(String message, String completedUrl) {
    }

    public static void error(String s, String title, String message) {

    }
}
