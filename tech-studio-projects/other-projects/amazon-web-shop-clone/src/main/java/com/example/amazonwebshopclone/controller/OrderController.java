package com.example.amazonwebshopclone.controller;

import com.example.amazonwebshopclone.dto.OrderDTO;
import com.example.amazonwebshopclone.model.Order;
import com.example.amazonwebshopclone.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * OrderController handles operations related to user orders,
 * including placing, updating, and retrieving orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * Places a new order.
     *
     * @param orderDTO Data transfer object containing order details.
     * @return ResponseEntity containing the placed order.
     */
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Placing a new order for user ID: {}", orderDTO.getUserId());
        Order order = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * Updates an existing order.
     *
     * @param orderId  ID of the order to be updated.
     * @param orderDTO Data transfer object containing updated order details.
     * @return ResponseEntity containing the updated order.
     */
    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        logger.info("Updating order with ID: {}", orderId);
        Order updatedOrder = orderService.updateOrder(orderId, orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    /**
     * Cancels an existing order.
     *
     * @param orderId ID of the order to be canceled.
     * @return ResponseEntity with the status of the operation.
     */
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        logger.info("Cancelling order with ID: {}", orderId);
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId ID of the order to be retrieved.
     * @return ResponseEntity containing the order details.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long orderId) {
        logger.info("Fetching order with ID: {}", orderId);
        Optional<Order> order = orderService.getOrderById(orderId);
        return new ResponseEntity<Optional<Order>>(order, HttpStatus.OK);
    }

    /**
     * Retrieves all orders for a user.
     *
     * @param userId ID of the user whose orders are to be retrieved.
     * @return ResponseEntity containing the list of orders.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        logger.info("Fetching all orders for user ID: {}", userId);
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
