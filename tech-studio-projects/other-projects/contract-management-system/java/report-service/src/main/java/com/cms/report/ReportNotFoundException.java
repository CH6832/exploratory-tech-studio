package com.cms.report;

/**
 * Custom exception class to indicate that a requested report was not found.
 * <p>
 * This exception is thrown by the ReportService when a report type is requested
 * that does not exist or is unsupported. It extends the base Exception class
 * to allow for custom handling of report-not-found scenarios.
 * </p>
 */
public class ReportNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new ReportNotFoundException with the specified detail message.
     * <p>
     * This constructor accepts a message that provides additional information
     * about the exception, typically the name or type of the report that was
     * not found. The message is passed to the superclass (Exception) constructor.
     * </p>
     *
     * @param message A string message describing the exception, usually identifying
     *                the missing report type or other relevant details.
     */
    public ReportNotFoundException(String message) {
        super(message);  // Pass the custom message to the superclass constructor
    }
}
