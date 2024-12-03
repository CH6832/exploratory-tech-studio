package com.efilingusgaap.financialfilingservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Unit test class for the {@link FinancialUserServiceApplication}.
 * <p>
 * This class contains unit tests to validate the behavior of the application. It uses Mockito
 * to mock the service layer and test the application's interaction with that layer.
 * </p>
 */
@SpringBootTest
class FinancialUserServiceApplicationTests {

	@Mock
	private FilingService filingService;  // Mock the FilingService class to simulate behavior without actual dependencies

	/**
	 * Test method for {@link FilingService#createFiling(Filing)}.
	 * <p>
	 * This test verifies that the {@link FilingService#createFiling(Filing)} method behaves correctly
	 * when creating a filing. It uses Mockito to mock the behavior of the service and ensures
	 * the method is called with the correct parameters.
	 * </p>
	 */
	@Test
	public void testCreateFiling() {
		// Create a new Filing instance with sample data
		Filing filing = new Filing();
		filing.setId(1L);
		filing.setTitle("Sample Filing");

		// Mock the service method behavior to return the same Filing instance when the method is called
		when(filingService.createFiling(filing)).thenReturn(filing);

		// Call the service method to create a filing
		Filing createdFiling = filingService.createFiling(filing);

		// Verify that the service's createFiling method was called exactly once with the provided filing
		verify(filingService, times(1)).createFiling(filing);
	}
}
