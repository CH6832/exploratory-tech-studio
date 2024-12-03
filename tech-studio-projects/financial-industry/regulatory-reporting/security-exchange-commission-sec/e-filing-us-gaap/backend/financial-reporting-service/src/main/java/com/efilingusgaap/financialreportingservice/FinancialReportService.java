package com.efilingusgaap.financialreportingservice;

import com.efilingusgaap.model.FinancialReport;
import com.efilingusgaap.repository.FinancialReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing financial reports.
 */
@Service
public class FinancialReportService {

    @Autowired
    private FinancialReportRepository financialReportRepository;

    /**
     * Retrieve all financial reports.
     *
     * @return List of all financial reports.
     */
    public List<FinancialReport> getAllReports() {
        return financialReportRepository.findAll();
    }

    /**
     * Retrieve a financial report by its ID.
     *
     * @param id The ID of the financial report.
     * @return The financial report, or null if not found.
     */
    public FinancialReport getReportById(Long id) {
        return financialReportRepository.findById(id).orElse(null);
    }

    /**
     * Create a new financial report.
     *
     * @param financialReport The financial report to create.
     * @return The created financial report.
     */
    public FinancialReport createReport(FinancialReport financialReport) {
        return financialReportRepository.save(financialReport);
    }

    /**
     * Update an existing financial report.
     *
     * @param id The ID of the report to update.
     * @param financialReport The updated financial report data.
     * @return The updated financial report, or null if the report does not exist.
     */
    public FinancialReport updateReport(Long id, FinancialReport financialReport) {
        if (financialReportRepository.existsById(id)) {
            financialReport.setId(id);
            return financialReportRepository.save(financialReport);
        } else {
            return null;
        }
    }

    /**
     * Delete a financial report by its ID.
     *
     * @param id The ID of the report to delete.
     */
    public void deleteReport(Long id) {
        financialReportRepository.deleteById(id);
    }
}
