package com.cms.report;

import org.springframework.context.ApplicationEvent;

/**
 * Event class representing an audit event for report-related actions.
 * <p>
 * This event is published to capture audit information whenever a report is generated
 * or modified. It contains details about the action being performed, which can be
 * used for logging, monitoring, or other auditing purposes.
 * </p>
 */
public class ReportAuditEvent extends ApplicationEvent {

    // Serial version UID for this class, which helps during the serialization process.
    private static final long serialVersionUID = 1L;

    // Action message describing the specific action being audited (e.g., "Generating Sales Report")
    private final String actionMessage;

    /**
     * Constructs a new ReportAuditEvent with the specified source and action message.
     * <p>
     * The constructor initializes the event source and sets the action message, which
     * provides details about the report-related action that triggered this event.
     * </p>
     *
     * @param source       The object that published the event, typically the component
     *                     responsible for generating or managing reports.
     * @param actionMessage A string describing the action being audited, such as the
     *                      name of the report being generated or modified.
     */
    public ReportAuditEvent(Object source, String actionMessage) {
        super(source);  // Pass the event source to the superclass constructor
        this.actionMessage = actionMessage;  // Set the action message for this event
    }

    /**
     * Retrieves the action message associated with this audit event.
     * <p>
     * The action message provides context about the specific report-related action
     * that triggered this event, which is useful for logging or audit purposes.
     * </p>
     *
     * @return A string containing the action message.
     */
    public String getActionMessage() {
        return actionMessage;
    }
}
