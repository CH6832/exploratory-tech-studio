package com.efilingusgaap.financialreportingservice;

import com.efilingusgaap.model.FinancialReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for financial report endpoints with versioning.
 */
@RestController
@RequestMapping("/api/financial-reports")
public class FinancialReportController {

    @Autowired
    private FinancialReportService financialReportService;

    /**
     * Version 1: Get all financial reports.
     *
     * @return List of financial reports.
     */
    @GetMapping("/v1")
    public List<FinancialReport> getAllFinancialReportsV1() {
        return financialReportService.getAllReports();
    }

    /**
     * Version 1: Get a specific financial report by ID.
     *
     * @param id The ID of the financial report.
     * @return The requested financial report.
     */
    @GetMapping("/v1/{id}")
    public FinancialReport getFinancialReportV1(@PathVariable Long id) {
        return financialReportService.getReportById(id);
    }

    /**
     * Version 1: Create a new financial report.
     *
     * @param financialReport The financial report to create.
     * @return The created financial report.
     */
    @PostMapping("/v1")
    public FinancialReport createFinancialReportV1(@RequestBody FinancialReport financialReport) {
        return financialReportService.createReport(financialReport);
    }

    /**
     * Version 1: Update an existing financial report.
     *
     * @param id              The ID of the financial report.
     * @param financialReport The updated financial report data.
     * @return The updated financial report.
     */
    @PutMapping("/v1/{id}")
    public FinancialReport updateFinancialReportV1(@PathVariable Long id, @RequestBody FinancialReport financialReport) {
        return financialReportService.updateReport(id, financialReport);
    }

    /**
     * Version 1: Delete a financial report by ID.
     *
     * @param id The ID of the financial report.
     */
    @DeleteMapping("/v1/{id}")
    public void deleteFinancialReportV1(@PathVariable Long id) {
        financialReportService.deleteReport(id);
    }

    /**
     * Version 2: Get all financial reports.
     *
     * @return List of financial reports.
     */
    @GetMapping("/v2")
    public List<FinancialReport> getAllFinancialReportsV2() {
        return financialReportService.getAllReports();
    }

    /**
     * Version 2: Get a specific financial report by ID.
     *
     * @param id The ID of the financial report.
     * @return The requested financial report.
     */
    @GetMapping("/v2/{id}")
    public FinancialReport getFinancialReportV2(@PathVariable Long id) {
        return financialReportService.getReportById(id);
    }

    /**
     * Version 2: Create a new financial report.
     *
     * @param financialReport The financial report to create.
     * @return The created financial report.
     */
    @PostMapping("/v2")
    public FinancialReport createFinancialReportV2(@RequestBody FinancialReport financialReport) {
        return financialReportService.createReport(financialReport);
    }

    /**
     * Version 2: Update an existing financial report.
     *
     * @param id              The ID of the financial report.
     * @param financialReport The updated financial report data.
     * @return The updated financial report.
     */
    @PutMapping("/v2/{id}")
    public FinancialReport updateFinancialReportV2(@PathVariable Long id, @RequestBody FinancialReport financialReport) {
        return financialReportService.updateReport(id, financialReport);
    }

    /**
     * Version 2: Delete a financial report by ID.
     *
     * @param id The ID of the financial report.
     */
    @DeleteMapping("/v2/{id}")
    public void deleteFinancialReportV2(@PathVariable Long id) {
        financialReportService.deleteReport(id);
    }
}
