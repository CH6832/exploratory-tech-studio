package com.example.amazonwebshopclone.service;

import com.example.amazonwebshopclone.dto.ProductDTO;
import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.model.User;
import com.example.amazonwebshopclone.model.Order;
import com.example.amazonwebshopclone.repository.ProductRepository;
import com.example.amazonwebshopclone.repository.UserRepository;
import com.example.amazonwebshopclone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling administrative tasks such as managing users, products, and orders.
 */
@Service
public class AdminService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminService(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Adds a new product to the catalog.
     *
     * @param product The product to be added.
     * @return
     */
    public Product addProduct(ProductDTO product) {
        //return productRepository.save(product);
        return null;
    }

    /**
     * Updates an existing product in the catalog.
     *
     * @param product The product with updated information.
     * @return The updated product.
     */
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product from the catalog by its ID.
     *
     * @param productId The ID of the product to be deleted.
     */
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to be retrieved.
     * @return An Optional containing the user if found, or empty if not found.
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to be retrieved.
     * @return An Optional containing the order if found, or empty if not found.
     */
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * Retrieves all orders for a specific user.
     *
     * @param userId The ID of the user whose orders are to be retrieved.
     * @return A list of orders associated with the specified user.
     */
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Product updateProduct(Long productId, ProductDTO productDTO) {
        return null;
    }
}
