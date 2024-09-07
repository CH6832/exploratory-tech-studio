package com.java.ifrscalculator.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom logging manager for the application.
 */
public class LoggingManager {
    private static final String LOG_FILE = "app.log";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs an info message to the console and the log file.
     *
     * @param message The message to log.
     */
    public static void logInfo(String message) {
        log("INFO", message);
    }

    /**
     * Logs a warning message to the console and the log file.
     *
     * @param message The message to log.
     */
    public static void logWarning(String message) {
        log("WARNING", message);
    }

    /**
     * Logs a severe error message to the console and the log file.
     *
     * @param message The message to log.
     */
    public static void logSevere(String message) {
        log("SEVERE", message);
    }

    /**
     * Logs an exception to the console and the log file.
     *
     * @param e The exception to log.
     */
    public static void logException(Exception e) {
        StringBuilder message = new StringBuilder();
        message.append(e.toString()).append("\n");
        for (StackTraceElement elem : e.getStackTrace()) {
            message.append("\t").append(elem.toString()).append("\n");
        }
        log("SEVERE", message.toString());
    }

    /**
     * General log method that writes to both console and log file.
     *
     * @param level   The level of the log (INFO, WARNING, SEVERE).
     * @param message The message to log.
     */
    private static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logMessage = String.format("%s [%s] %s", timestamp, level, message);

        // Log to console
        System.out.println(logMessage);

        // Log to file
        try (FileWriter fw = new FileWriter(LOG_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logMessage);
        } catch (IOException ex) {
            // In case file logging fails, print to console as a fallback
            System.err.println("Failed to write to log file: " + ex.getMessage());
        }
    }
}

