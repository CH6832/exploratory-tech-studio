package com.cms.report;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The ReportController class provides endpoints for generating reports.
 * It handles HTTP requests related to generating reports.
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    // Constructor for dependency injection of the ReportService
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Endpoint to generate a report.
     *
     * @param reportType The type of report to generate (e.g., "Sales Report", "Contract Report").
     * @return A ResponseEntity containing the generated report data or an error message.
     */
    @GetMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestParam String reportType) {
        try {
            // Generate the report by calling the service layer
            Report report = reportService.generateReport(reportType);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            // Return 404 Not Found if the report type is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Return 500 Internal Server Error if any other error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating the report");
        }
    }
}
