package com.cms.report;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * The ReportService class handles the business logic for generating reports.
 * It interacts with the database or data sources to create reports based on user requests.
 */
@Service
public class ReportService {

    private static final Logger logger = LogManager.getLogger(ReportService.class);

    // Event publisher to publish events instead of directly calling audit service
    private final ApplicationEventPublisher eventPublisher;

    public ReportService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    /**
     * Generates a report for the provided report type.
     *
     * @param reportType The type of report to generate (e.g., "Sales Report", "Contract Report").
     * @return The generated report data (this can be a file or data object).
     * @throws ReportNotFoundException if the report cannot be generated due to missing data or invalid report type.
     */
    public Report generateReport(String reportType) throws ReportNotFoundException {
        try {
            // Publish an audit event for report generation action
            eventPublisher.publishEvent(new ReportAuditEvent(this, "Generating report of type: " + reportType));

            // Placeholder logic for report generation
            if (reportType.equalsIgnoreCase("Sales Report")) {
                // Generate sales report (dummy data here)
                return new Report("Sales Report", "This is a sales report.");
            } else if (reportType.equalsIgnoreCase("Contract Report")) {
                // Generate contract report (dummy data here)
                return new Report("Contract Report", "This is a contract report.");
            } else {
                throw new ReportNotFoundException("Report type '" + reportType + "' is not recognized.");
            }
        } catch (Exception e) {
            // Log the error
            logger.error("Error generating report: " + e.getMessage(), e);
            throw e;
        }
    }
}
