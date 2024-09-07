package com.example.amazonwebshopclone.repository;

import com.example.amazonwebshopclone.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for Cart entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Finds a Cart by its associated user ID.
     *
     * @param userId The ID of the user whose cart is to be found.
     * @return An Optional containing the Cart if found, or empty if not found.
     */
    Optional<Cart> findByUserId(Long userId);
}
