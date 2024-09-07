package com.example.amazonwebshopclone.model;

import io.github.classgraph.json.Id;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity representing a shopping cart.
 * Contains a list of items added to the cart by a user.
 */
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the cart

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // The user who owns the cart

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> items = new HashMap<>();  // Products and their quantities in the cart

    // Default constructor
    public Cart(Long userId) {}

    /**
     * Parameterized constructor for Cart.
     *
     * @param user  The user who owns the cart
     */
    public Cart(User user) {
        this.user = user;
    }

    public Cart(Product product, int quantity) {
        return;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the cart.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the cart.
     *
     * @param id Unique identifier for the cart
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user who owns the cart.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who owns the cart.
     *
     * @param user The user who owns the cart
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the items in the cart.
     *
     * @return A map of products and their quantities
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Sets the items in the cart.
     *
     * @param items A map of products and their quantities
     */
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    /**
     * Adds a product to the cart or updates the quantity if it already exists.
     *
     * @param product  The product to be added
     * @param quantity The quantity to be added
     */
    public void addProduct(Product product, Integer quantity) {
        if (quantity > 0) {
            items.merge(product, quantity, Integer::sum);
        } else {
            removeProduct(product);
        }
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed
     */
    public void removeProduct(Product product) {
        items.remove(product);
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                '}';
    }

    public void setQuantity(int quantity) {

    }
}
