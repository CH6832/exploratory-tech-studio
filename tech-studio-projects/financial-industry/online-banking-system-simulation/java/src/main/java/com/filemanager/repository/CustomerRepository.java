package com.example.banking.repository;

import com.example.banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Customer entities.
 * Extends JpaRepository to provide standard CRUD operations and
 * query capabilities without requiring boilerplate code.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional custom queries can be added here if needed, such as:
    List<Customer> findByEmail(String email);
}
