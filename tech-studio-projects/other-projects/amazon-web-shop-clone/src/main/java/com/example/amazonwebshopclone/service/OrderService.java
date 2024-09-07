package com.example.amazonwebshopclone.service;

import com.example.amazonwebshopclone.dto.OrderDTO;
import com.example.amazonwebshopclone.model.Cart;
import com.example.amazonwebshopclone.model.Order;
import com.example.amazonwebshopclone.repository.CartRepository;
import com.example.amazonwebshopclone.repository.OrderRepository;
import com.example.amazonwebshopclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing orders, including creating, retrieving, and managing order details.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    /**
     * Creates a new order from the user's cart.
     *
     * @param userId The ID of the user placing the order.
     * @return The created Order.
     * @throws RuntimeException if the cart or any product in the cart is not found.
     */
    public Order createOrderFromCart(Long userId) {
        // Retrieve the cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Create an order
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderItems(cart.getItems());

        // Calculate order total and set it
//        double total = cart.getItems().size()
//                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
//                .sum();
//        order.setTotalAmount(total);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to be retrieved.
     * @return An Optional containing the Order if found, or empty if not found.
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

    /**
     * Updates the status of an order.
     *
     * @param orderId The ID of the order to be updated.
     * @param status  The new status of the order.
     * @return The updated Order.
     * @throws RuntimeException if the order is not found.
     */
    public Order updateOrderStatus(Long orderId, String status) {
        // Retrieve the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the status
        order.setStatus(status);

        // Save the updated order
        return orderRepository.save(order);
    }

    public Order placeOrder(OrderDTO orderDTO) {
        return null;
    }

    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        return null;
    }

    public Object cancelOrder(Long orderId) {
        return null;
    }
}
