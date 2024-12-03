package com.efilingusgaap.financialfilingservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Filing entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in methods for
 * common data access operations such as saving, deleting, and finding records.
 * Additionally, custom query methods can be defined here.
 */
@Repository
public interface FilingRepository extends JpaRepository<Filing, Long> {

    /**
     * Retrieves a Filing entity by its title.
     *
     * @param title the title of the filing to find
     * @return the {@link Filing} object matching the specified title, or {@code null} if none found
     */
    Filing findByTitle(String title);

    // Add more custom query methods as needed
}
