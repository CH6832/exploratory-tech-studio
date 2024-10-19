package com.system.ids;


/**
 * Custom exception for the IDS application.
 */
public class CustomException extends RuntimeException {
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
