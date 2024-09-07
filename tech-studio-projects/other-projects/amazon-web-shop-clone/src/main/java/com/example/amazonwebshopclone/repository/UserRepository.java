package com.example.amazonwebshopclone.repository;

import com.example.amazonwebshopclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for User entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User by its unique ID.
     *
     * @param id The ID of the user to be found.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findById(Long id);

    /**
     * Finds a User by their username.
     *
     * @param username The username of the user to be found.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a User by their email address.
     *
     * @param email The email address of the user to be found.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findByEmail(String email);
}
