package com.example.amazonwebshopclone.model;

import io.github.classgraph.json.Id;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

/**
 * Entity representing a product review.
 * Contains details about the review, including the review text, rating, and the user who wrote the review.
 */
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  // Unique identifier for the review

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;         // The product being reviewed

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;              // The user who wrote the review

    @Column(name = "rating", nullable = false)
    private Integer rating;         // Rating given by the user (e.g., 1-5)

    @Column(name = "review_text")
    private String reviewText;      // Text of the review

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "review_date", nullable = false)
    private Date reviewDate;        // Date and time when the review was written

    // Default constructor
    public Review() {}

    /**
     * Parameterized constructor for Review.
     *
     * @param product      The product being reviewed
     * @param user         The user who wrote the review
     * @param rating       Rating given by the user
     * @param reviewText   Text of the review
     * @param reviewDate   Date and time when the review was written
     */
    public Review(Product product, User user, Integer rating, String reviewText, Date reviewDate) {
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the review.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the review.
     *
     * @param id Unique identifier for the review
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product being reviewed.
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product being reviewed.
     *
     * @param product The product being reviewed
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the user who wrote the review.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who wrote the review.
     *
     * @param user The user who wrote the review
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the rating given by the user.
     *
     * @return rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the rating given by the user.
     *
     * @param rating Rating given by the user
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets the text of the review.
     *
     * @return reviewText
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Sets the text of the review.
     *
     * @param reviewText Text of the review
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Gets the date and time when the review was written.
     *
     * @return reviewDate
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * Sets the date and time when the review was written.
     *
     * @param reviewDate Date and time when the review was written
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
