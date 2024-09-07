package com.example.amazonwebshopclone.repository;

import com.example.amazonwebshopclone.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for Order entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds an Order by its unique ID.
     *
     * @param id The ID of the order to be found.
     * @return An Optional containing the Order if found, or empty if not found.
     */
    Optional<Order> findById(Long id);

    /**
     * Finds all Orders associated with a specific user.
     *
     * @param userId The ID of the user whose orders are to be found.
     * @return A list of Orders associated with the specified user.
     */
    List<Order> findByUserId(Long userId);

    /**
     * Finds all Orders placed within a specific date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of Orders placed within the specified date range.
     */
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
}
