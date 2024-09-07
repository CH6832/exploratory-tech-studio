package com.example.amazonwebshopclone.controller;

import com.example.amazonwebshopclone.dto.ProductDTO;
import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.service.AdminService;
import com.example.amazonwebshopclone.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController handles admin-related operations such as managing products and orders.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    /**
     * Adds a new product to the system.
     *
     * @param productDTO Data transfer object for the product to be added.
     * @return ResponseEntity containing the added product.
     */
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Adding a new product: {}", productDTO.getName());
        Product product = adminService.addProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Updates an existing product in the system.
     *
     * @param productId  ID of the product to be updated.
     * @param productDTO Data transfer object containing updated product details.
     * @return ResponseEntity containing the updated product.
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        logger.info("Updating product with ID: {}", productId);
        Product updatedProduct = adminService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Deletes a product from the system.
     *
     * @param productId ID of the product to be deleted.
     * @return ResponseEntity with the status of the operation.
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        logger.info("Deleting product with ID: {}", productId);
        adminService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetches all products in the system.
     *
     * @return ResponseEntity containing the list of products.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Handles any other admin-related operations that might be required.
     * This is a placeholder for additional admin functionalities.
     */
    // Additional admin-related methods can be added here
}
