package com.example.banking.controller;

import com.example.banking.entity.Transaction;
import com.example.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing transactions.
 * Provides endpoints for creating, retrieving, and processing transactions such as deposits, withdrawals, and transfers.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    /** Service class for transaction-related operations. */
    @Autowired
    private TransactionService transactionService;

    /**
     * Retrieves all transactions.
     * @return a list of all transactions.
     */
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    /**
     * Retrieves a transaction by its ID.
     * @param id the ID of the transaction to retrieve.
     * @return a ResponseEntity containing the transaction if found, or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves transactions by account ID.
     * @param accountId the ID of the account to retrieve transactions for.
     * @return a list of transactions associated with the specified account.
     */
    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.findByAccountId(accountId);
    }

    /**
     * Processes a deposit transaction.
     * @param accountId the ID of the account to deposit into.
     * @param amount the amount to deposit.
     * @return a ResponseEntity containing the created transaction.
     */
    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        Transaction transaction = transactionService.deposit(accountId, amount);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Processes a withdrawal transaction.
     * @param accountId the ID of the account to withdraw from.
     * @param amount the amount to withdraw.
     * @return a ResponseEntity containing the created transaction.
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        Transaction transaction = transactionService.withdraw(accountId, amount);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Processes a transfer transaction between accounts.
     * @param fromAccountNumber the account number to transfer from.
     * @param toAccountNumber the account number to transfer to.
     * @param amount the amount to transfer.
     * @return a ResponseEntity containing the created transfer transaction.
     */
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam BigDecimal amount) {
        Transaction transaction = transactionService.transfer(fromAccountNumber, toAccountNumber, amount);
        return ResponseEntity.ok(transaction);
    }
}
