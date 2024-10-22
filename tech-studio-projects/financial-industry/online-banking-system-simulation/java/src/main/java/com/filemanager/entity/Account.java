package com.example.banking.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a bank account associated with a customer.
 * This entity is mapped to the 'accounts' table in the database.
 */
@Entity
@Table(name = "accounts")
public class Account {

    /** Unique identifier for the account. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique account number for the account. */
    @Column(nullable = false, unique = true)
    private String accountNumber;

    /** Current balance of the account. */
    @Column(nullable = false)
    private BigDecimal balance;

    /** The customer who owns this account. */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /** List of transactions associated with this account. */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    /** 
     * Gets the unique identifier for the account.
     * @return the account ID.
     */
    public Long getId() {
        return id;
    }

    /** 
     * Sets the unique identifier for the account.
     * @param id the account ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** 
     * Gets the account number.
     * @return the account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /** 
     * Sets the account number.
     * @param accountNumber the account number.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /** 
     * Gets the current balance of the account.
     * @return the account balance.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /** 
     * Sets the current balance of the account.
     * @param balance the account balance.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /** 
     * Gets the customer associated with this account.
     * @return the customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /** 
     * Sets the customer for this account.
     * @param customer the customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /** 
     * Gets the list of transactions associated with this account.
     * @return the list of transactions.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /** 
     * Sets the list of transactions associated with this account.
     * @param transactions the list of transactions.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /** 
     * Returns a string representation of the account.
     * @return a string representation of the account.
     */
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", customer=" + customer +
                ", transactions=" + transactions +
                '}';
    }
}
