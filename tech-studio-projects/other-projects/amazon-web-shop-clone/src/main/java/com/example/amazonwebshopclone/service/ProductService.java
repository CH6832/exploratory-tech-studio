package com.example.amazonwebshopclone.service;

import com.example.amazonwebshopclone.dto.ProductDTO;
import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing products, including adding, updating, deleting, and retrieving products.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Adds a new product to the catalog.
     *
     * @param product The product to be added.
     * @return The added product.
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product in the catalog.
     *
     * @param product The product with updated information.
     * @return The updated product.
     * @throws RuntimeException if the product is not found.
     */
    public Product updateProduct(Product product) {
        // Check if the product exists before updating
        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product not found");
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product from the catalog by its ID.
     *
     * @param productId The ID of the product to be deleted.
     * @throws RuntimeException if the product is not found.
     */
    public void deleteProduct(Long productId) {
        // Check if the product exists before deleting
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to be retrieved.
     * @return An Optional containing the Product if found, or empty if not found.
     */
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    /**
     * Retrieves all products in the catalog.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves products by their category.
     *
     * @param category The category of the products to be retrieved.
     * @return A list of products in the specified category.
     */
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return productDTO;
    }
}
