package com.example.amazonwebshopclone.exception;

/**
 * Custom exception to be thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
