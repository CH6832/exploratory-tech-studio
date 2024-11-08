package com.cms.search;

/**
 * Custom exception for errors that occur within the SearchService.
 */
public class SearchServiceException extends RuntimeException {

	/**
	 * The serialVersionUID is a unique identifier used during the deserialization process.
	 * It ensures that a loaded class is compatible with the serialized object.
	 * If no matching serialVersionUID is found during deserialization, an {@link java.io.InvalidClassException} will be thrown.
	 * 
	 * It is important to define serialVersionUID when implementing Serializable to maintain 
	 * compatibility between different versions of a class during the serialization and deserialization process.
	 * 
	 * This field is used for version control of serialized data.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new SearchServiceException with a specific message.
     * 
     * @param message The error message.
     */
    public SearchServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new SearchServiceException with a specific message and cause.
     * 
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public SearchServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
