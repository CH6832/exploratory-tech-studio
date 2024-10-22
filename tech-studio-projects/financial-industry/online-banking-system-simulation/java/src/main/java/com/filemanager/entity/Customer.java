package com.example.banking.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a customer in the banking system.
 * This entity is mapped to the 'customers' table in the database.
 */
@Entity
@Table(name = "customers")
public class Customer {

    /** Unique identifier for the customer. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the customer. */
    @Column(nullable = false)
    private String name;

    /** Email of the customer, must be unique. */
    @Column(nullable = false, unique = true)
    private String email;

    /** List of accounts associated with this customer. */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    /** 
     * Gets the unique identifier for the customer.
     * @return the customer ID.
     */
    public Long getId() {
        return id;
    }

    /** 
     * Sets the unique identifier for the customer.
     * @param id the customer ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** 
     * Gets the name of the customer.
     * @return the customer's name.
     */
    public String getName() {
        return name;
    }

    /** 
     * Sets the name of the customer.
     * @param name the customer's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Gets the email of the customer.
     * @return the customer's email.
     */
    public String getEmail() {
        return email;
    }

    /** 
     * Sets the email of the customer.
     * @param email the customer's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     * Gets the list of accounts associated with this customer.
     * @return the list of accounts.
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /** 
     * Sets the list of accounts associated with this customer.
     * @param accounts the list of accounts.
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /** 
     * Returns a string representation of the customer.
     * @return a string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
