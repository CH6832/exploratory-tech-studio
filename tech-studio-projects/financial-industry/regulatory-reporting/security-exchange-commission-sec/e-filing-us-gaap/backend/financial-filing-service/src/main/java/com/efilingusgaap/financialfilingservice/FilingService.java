package com.efilingusgaap.financialfilingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing filing operations.
 * <p>
 * This service provides business logic for managing {@link Filing} entities. It interacts with the
 * {@link FilingRepository} to perform CRUD operations such as retrieving, creating, updating, and
 * deleting filings.
 */
@Service
public class FilingService {

    @Autowired
    private FilingRepository filingRepository;

    /**
     * Retrieves all filings from the repository.
     *
     * @return a list of all {@link Filing} entities
     */
    public List<Filing> getAllFilings() {
        return filingRepository.findAll();
    }

    /**
     * Retrieves a specific filing by its ID.
     *
     * @param id the ID of the filing to retrieve
     * @return the {@link Filing} entity if found, or {@code null} if no filing with the specified ID exists
     */
    public Filing getFilingById(Long id) {
        return filingRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new filing.
     *
     * @param filing the {@link Filing} entity to create
     * @return the created {@link Filing} entity
     */
    public Filing createFiling(Filing filing) {
        return filingRepository.save(filing);
    }

    /**
     * Updates an existing filing.
     * <p>
     * If the filing with the specified ID exists, the provided filing entity is saved with the given ID.
     * Otherwise, returns {@code null} to indicate that no such filing exists to update.
     *
     * @param id     the ID of the filing to update
     * @param filing the {@link Filing} entity with updated data
     * @return the updated {@link Filing} entity if found, or {@code null} if no filing with the specified ID exists
     */
    public Filing updateFiling(Long id, Filing filing) {
        if (filingRepository.existsById(id)) {
            filing.setId(id); // Ensures that the filing's ID is preserved during the update
            return filingRepository.save(filing);
        } else {
            return null;
        }
    }

    /**
     * Deletes a filing by its ID.
     *
     * @param id the ID of the filing to delete
     */
    public void deleteFiling(Long id) {
        filingRepository.deleteById(id);
    }
}
