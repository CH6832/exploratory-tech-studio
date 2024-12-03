package com.efilingusgaap.financialfilingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link FilingRepository} to ensure the correct functionality of CRUD operations.
 * <p>
 * This test verifies the ability to save a {@link Filing} entity to the database and retrieve it
 * using its ID. The test ensures that the repository is functioning as expected by comparing the
 * saved and retrieved {@link Filing} data.
 * </p>
 */
@SpringBootTest
public class FilingRepositoryTest {

    @Autowired
    private FilingRepository filingRepository;

    /**
     * Test for saving and finding a Filing entity in the database.
     * <p>
     * This test method creates a new {@link Filing}, saves it to the repository, and then attempts
     * to retrieve it using its ID. The test asserts that the retrieved filing is not null and that
     * the title of the saved filing matches the title of the retrieved filing.
     * </p>
     */
    @Test
    public void testSaveAndFindFiling() {
        // Create a new Filing instance with sample data
        Filing filing = new Filing();
        filing.setId(1L); // Set an ID for the filing
        filing.setTitle("Sample Filing"); // Set a title for the filing
        filing.setDescription("Sample Filing Description"); // Set a description for the filing

        // Save the Filing instance to the database
        filingRepository.save(filing);

        // Retrieve the Filing by its ID from the database
        Filing foundFiling = filingRepository.findById(1L).orElse(null);

        // Assert that the retrieved filing is not null (i.e., it exists in the database)
        assertNotNull(foundFiling, "The filing should not be null");

        // Assert that the title of the saved filing matches the title of the retrieved filing
        assertEquals(filing.getTitle(), foundFiling.getTitle(), "The titles should be the same");
    }
}
