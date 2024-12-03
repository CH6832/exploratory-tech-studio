package com.efilingusgaap.financialreportingservice;

import com.efilingusgaap.model.FinancialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for FinancialReport entities.
 * Provides CRUD operations and additional query methods through Spring Data JPA.
 */
@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {

    // Additional custom query methods can be added here if required
}
