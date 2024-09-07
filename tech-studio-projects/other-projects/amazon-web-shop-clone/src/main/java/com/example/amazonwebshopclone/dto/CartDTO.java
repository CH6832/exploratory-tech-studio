package com.example.amazonwebshopclone.dto;

/**
 * Data Transfer Object for Cart operations.
 * Used to carry data related to cart operations between the client and server.
 */
public class CartDTO {

    private Long userId;        // ID of the user who owns the cart
    private Long productId;     // ID of the product being added or updated in the cart
    private Integer quantity;   // Quantity of the product to be added or updated

    // Default constructor
    public CartDTO() {}

    /**
     * Parameterized constructor for CartDTO.
     *
     * @param userId    ID of the user who owns the cart
     * @param productId ID of the product
     * @param quantity  Quantity of the product
     */
    public CartDTO(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters

    /**
     * Gets the ID of the user who owns the cart.
     *
     * @return userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who owns the cart.
     *
     * @param userId ID of the user
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the product.
     *
     * @return productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product.
     *
     * @param productId ID of the product
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity Quantity of the product
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
