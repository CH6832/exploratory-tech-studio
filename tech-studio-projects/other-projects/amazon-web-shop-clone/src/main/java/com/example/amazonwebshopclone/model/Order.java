package com.example.amazonwebshopclone.model;

import io.github.classgraph.json.Id;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity representing an order.
 * Contains details about the order, including the user, order date, and order items.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  // Unique identifier for the order

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;               // The user who placed the order

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;          // Date and time when the order was placed

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> items = new HashMap<>();  // Products and their quantities in the order

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;      // Total amount for the order

    @Column(name = "status", nullable = false)
    private String status;           // Status of the order (e.g., PENDING, SHIPPED, DELIVERED)

    // Default constructor
    public Order() {}

    /**
     * Parameterized constructor for Order.
     *
     * @param user        The user who placed the order
     * @param orderDate   Date and time when the order was placed
     * @param items       Products and their quantities in the order
     * @param totalAmount Total amount for the order
     * @param status      Status of the order
     */
    public Order(User user, Date orderDate, Map<Product, Integer> items, Double totalAmount, String status) {
        this.user = user;
        this.orderDate = orderDate;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the order.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the order.
     *
     * @param id Unique identifier for the order
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user who placed the order.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who placed the order.
     *
     * @param user The user who placed the order
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the date and time when the order was placed.
     *
     * @return orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date and time when the order was placed.
     *
     * @param orderDate Date and time when the order was placed
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the products and their quantities in the order.
     *
     * @return A map of products and their quantities
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Sets the products and their quantities in the order.
     *
     * @param items A map of products and their quantities
     */
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    /**
     * Gets the total amount for the order.
     *
     * @return totalAmount
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the total amount for the order.
     *
     * @param totalAmount Total amount for the order
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Gets the status of the order.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status Status of the order
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }

    public void setUserId(Long userId) {
    }

    public void setOrderItems(Map<Product, Integer> items) {

    }
}
