package com.efilingusgaap.financialfilingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Filing endpoints.
 * <p>
 * This controller provides endpoints for CRUD operations on financial filings.
 * It acts as the entry point for HTTP requests and delegates processing to the
 * {@link FilingService}.
 */
@RestController
@RequestMapping("/api/filings")
public class FilingController {

    /**
     * Service layer dependency for handling Filing-related business logic.
     */
    @Autowired
    private FilingService filingService;

    /**
     * Retrieves a list of all filings.
     *
     * @return a {@link List} of {@link Filing} objects
     */
    @GetMapping
    public List<Filing> getAllFilings() {
        return filingService.getAllFilings();
    }

    /**
     * Retrieves a specific filing by its unique identifier.
     *
     * @param id the unique identifier of the filing
     * @return the {@link Filing} object with the specified ID
     */
    @GetMapping("/{id}")
    public Filing getFilingById(@PathVariable Long id) {
        return filingService.getFilingById(id);
    }

    /**
     * Creates a new filing.
     *
     * @param filing the {@link Filing} object to be created
     * @return the newly created {@link Filing} object
     */
    @PostMapping
    public Filing createFiling(@RequestBody Filing filing) {
        return filingService.createFiling(filing);
    }

    /**
     * Updates an existing filing with new information.
     *
     * @param id     the unique identifier of the filing to be updated
     * @param filing the updated {@link Filing} object
     * @return the updated {@link Filing} object
     */
    @PutMapping("/{id}")
    public Filing updateFiling(@PathVariable Long id, @RequestBody Filing filing) {
        return filingService.updateFiling(id, filing);
    }

    /**
     * Deletes a filing by its unique identifier.
     *
     * @param id the unique identifier of the filing to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteFiling(@PathVariable Long id) {
        filingService.deleteFiling(id);
    }
}
