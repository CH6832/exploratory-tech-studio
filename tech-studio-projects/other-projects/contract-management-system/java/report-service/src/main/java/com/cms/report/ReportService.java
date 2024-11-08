package com.cms.report;

import com.cms.audit.AuditService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuditService auditService;  // For logging actions

    /**
     * Generates a report for the provided report type.
     *
     * @param reportType The type of report to generate (e.g., "Sales Report", "Contract Report").
     * @return The generated report data (this can be a file or data object).
     * @throws ReportNotFoundException if the report cannot be generated due to missing data or invalid report type.
     */
    public Report generateReport(String reportType) throws ReportNotFoundException {
        try {
            // Log the action of report generation
            auditService.logAction("Generating " + reportType);

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
