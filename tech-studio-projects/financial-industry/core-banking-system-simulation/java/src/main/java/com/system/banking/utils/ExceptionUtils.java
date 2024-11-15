package com.system.banking.utils;

/**
 * Utility class for handling custom exceptions within the banking system.
 * Contains definitions for custom exception types, which help to signal specific error conditions in the application.
 */
public class ExceptionUtils {

    /**
     * Custom exception to signal that a required file is missing.
     * This exception extends {@link RuntimeException}, making it an unchecked exception.
     */
    public static class FileMissingException extends RuntimeException {

        /**
         * Constructs a new FileMissingException with the specified detail message.
         *
         * @param message Detailed message describing the reason for the exception.
         */
        public FileMissingException(String message) {
            super(message);
        }
    }
}
