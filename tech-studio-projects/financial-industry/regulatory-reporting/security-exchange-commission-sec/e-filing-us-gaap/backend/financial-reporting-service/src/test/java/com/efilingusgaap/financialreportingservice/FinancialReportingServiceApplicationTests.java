package com.efilingusgaap.financialreportingservice;

import com.efilingusgaap.model.FinancialReport;
import com.efilingusgaap.service.FinancialReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FinancialReportingServiceApplicationTests {

    @Autowired
    private FinancialReportService financialReportService;

    private FinancialReport financialReport;

    @BeforeEach
    public void setUp() {
        financialReport = new FinancialReport();
        financialReport.setReportName("Annual Report 2024");
        financialReport.setReportType("10-K");
        financialReport.setFilingDate("2024-01-01");
    }

    @Test
    public void createReportTest() {
        FinancialReport createdReport = financialReportService.createReport(financialReport);
        assertNotNull(createdReport.getId());
    }

    @Test
    public void getAllReportsTest() {
        assertTrue(financialReportService.getAllReports().size() > 0);
    }
}

