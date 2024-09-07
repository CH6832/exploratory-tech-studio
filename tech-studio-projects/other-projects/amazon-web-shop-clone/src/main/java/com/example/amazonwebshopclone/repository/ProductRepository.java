package com.example.amazonwebshopclone.repository;

import com.example.amazonwebshopclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for handling CRUD operations for Product entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds a Product by its unique ID.
     *
     * @param id The ID of the product to be found.
     * @return An Optional containing the Product if found, or empty if not found.
     */
    Optional<Product> findById(Long id);

    /**
     * Finds all Products by their name containing a specific keyword.
     *
     * @param nameKeyword The keyword to search for in the product names.
     * @return A list of Products whose names contain the specified keyword.
     */
    List<Product> findByNameContainingIgnoreCase(String nameKeyword);

    /**
     * Finds all Products within a specific price range.
     *
     * @param minPrice The minimum price of the range.
     * @param maxPrice The maximum price of the range.
     * @return A list of Products within the specified price range.
     */
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds all Products with stock quantity greater than or equal to a specific value.
     *
     * @param minStockQuantity The minimum stock quantity.
     * @return A list of Products with stock quantity greater than or equal to the specified value.
     */
    List<Product> findByStockQuantityGreaterThanEqual(Integer minStockQuantity);

    List<Product> findByCategory(String category);
}
