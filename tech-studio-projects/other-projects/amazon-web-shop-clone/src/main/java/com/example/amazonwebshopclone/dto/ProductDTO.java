package com.example.amazonwebshopclone.dto;

/**
 * Data Transfer Object for Product operations.
 * Used to carry data related to products between the client and server.
 */
public class ProductDTO {

    private Long id;                   // ID of the product
    private String name;              // Name of the product
    private String description;       // Description of the product
    private Double price;             // Price of the product
    private Integer stockQuantity;    // Quantity of the product in stock

    // Default constructor
    public ProductDTO() {}

    /**
     * Parameterized constructor for ProductDTO.
     *
     * @param id              ID of the product
     * @param name            Name of the product
     * @param description     Description of the product
     * @param price           Price of the product
     * @param stockQuantity  Quantity of the product in stock
     */
    public ProductDTO(Long id, String name, String description, Double price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters

    /**
     * Gets the ID of the product.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id ID of the product
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name Name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description Description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     *
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price Price of the product
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the product in stock.
     *
     * @return stockQuantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the quantity of the product in stock.
     *
     * @param stockQuantity Quantity of the product in stock
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
