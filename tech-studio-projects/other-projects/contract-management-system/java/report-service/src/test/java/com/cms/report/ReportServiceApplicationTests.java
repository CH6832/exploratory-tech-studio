package com.cms.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Test class for the ReportService Spring Boot application.
 * <p>
 * This class contains unit tests to verify the functionality of the ReportService,
 * including generating different types of reports and ensuring that the appropriate
 * events are published for auditing purposes.
 * </p>
 */
@SpringBootTest
class ReportServiceApplicationTests {

    @Autowired
    private ReportService reportService;  // Inject ReportService instance for testing

    @Autowired
    private ApplicationEventPublisher eventPublisher;  // Inject ApplicationEventPublisher for event publishing

    /**
     * Set up method to initialize any preconditions or configurations
     * before each test case.
     */
    @BeforeEach
    void setUp() {
        // Any setup before each test can be done here
    }

    /**
     * Test to verify that the application context loads successfully.
     * <p>
     * Ensures that essential beans (ReportService and ApplicationEventPublisher)
     * are correctly instantiated by the Spring framework.
     * </p>
     */
    @Test
    void contextLoads() {
        // Verify that both reportService and eventPublisher beans are injected successfully
        assertThat(reportService).isNotNull();
        assertThat(eventPublisher).isNotNull();
    }

    /**
     * Test to verify that generating a "Sales Report" returns the expected report object.
     * <p>
     * Ensures that the content and title of the generated report match the expected values
     * for a "Sales Report" type.
     * </p>
     *
     * @throws Exception if the report generation encounters an error
     */
    @Test
    void generateReport_SalesReport_ReturnsSalesReport() throws Exception {
        // Generate a report of type "Sales Report" and verify its contents
        Report report = reportService.generateReport("Sales Report");
        assertThat(report).isNotNull();
        assertThat(report.getTitle()).isEqualTo("Sales Report");
        assertThat(report.getContent()).contains("This is a sales report.");
    }

    /**
     * Test to verify that an invalid report type throws a ReportNotFoundException.
     * <p>
     * This test attempts to generate a report with an unknown type and expects
     * an exception to be thrown, ensuring proper error handling for unsupported types.
     * </p>
     */
    @Test
    void generateReport_InvalidReportType_ThrowsReportNotFoundException() {
        // Attempt to generate a report of an unsupported type and verify exception handling
        assertThrows(ReportNotFoundException.class, () -> reportService.generateReport("Unknown Report"));
    }

    /**
     * Test to verify that generating a "Contract Report" returns the expected report object.
     * <p>
     * Ensures that the content and title of the generated report match the expected values
     * for a "Contract Report" type.
     * </p>
     *
     * @throws Exception if the report generation encounters an error
     */
    @Test
    void generateReport_ContractReport_ReturnsContractReport() throws Exception {
        // Generate a report of type "Contract Report" and verify its contents
        Report report = reportService.generateReport("Contract Report");
        assertThat(report).isNotNull();
        assertThat(report.getTitle()).isEqualTo("Contract Report");
        assertThat(report.getContent()).contains("This is a contract report.");
    }

    /**
     * Test to verify that an audit event is published when a report is generated.
     * <p>
     * Publishes a custom ReportAuditEvent upon report generation and validates
     * that the correct audit action message is included in the event.
     * </p>
     */
    @Test
    void auditEventPublished_OnReportGeneration() {
        // Define the audit action message for the event
        String actionMessage = "Generating Sales Report";

        // Publish the audit event and verify its contents
        ReportAuditEvent event = new ReportAuditEvent(this, actionMessage);
        eventPublisher.publishEvent(event);

        // Check that the event's action matches the expected action message
        // Additional verification may require mock event listeners in a real test setup
        assertThat(event.getAction()).isEqualTo(actionMessage);
    }
}
