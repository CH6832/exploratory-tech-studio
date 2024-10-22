package com.filemanager.controller;

import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing bank accounts.
 * Provides endpoints for account operations such as creating, updating, deleting,
 * and retrieving accounts, as well as handling deposits and withdrawals.
 */
/*
 * The @RestController annotation is a specialized version of the @Controller annotation, 
 * indicating that this class serves RESTful web services. It combines @Controller and @ResponseBody, 
 * allowing methods to return data directly as JSON or XML instead of rendering views, making it suitable for building REST APIs.
 */
@RestController
/*
 * The @RequestMapping("/api/accounts") annotation specifies the base URL path for all 
 * HTTP requests handled by this controller, allowing it to group related endpoints 
 * under the "/api/accounts" route for account management operations.
 */
@RequestMapping("/api/accounts")
public class AccountController {

    /** Service class for account-related operations. */
    @Autowired
    private AccountService accountService;

    /**
     * Retrieves all bank accounts.
     * @return a list of all accounts.
     */
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    /**
     * Retrieves a bank account by its ID.
     * @param id the ID of the account to retrieve.
     * @return a ResponseEntity containing the account if found, or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new bank account.
     * @param account the account details to create.
     * @return the created account.
     */
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    /**
     * Updates an existing bank account.
     * @param id the ID of the account to update.
     * @param account the updated account details.
     * @return a ResponseEntity containing the updated account if found, or a 404 Not Found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account existingAccount = accountService.findById(id);
        if (existingAccount != null) {
            account.setId(id); // Ensure the ID remains the same
            return ResponseEntity.ok(accountService.save(account));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a bank account by its ID.
     * @param id the ID of the account to delete.
     * @return a ResponseEntity with no content if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deposits an amount into a bank account.
     * @param id the ID of the account to deposit into.
     * @param amount the amount to deposit.
     * @return a ResponseEntity with an OK status if the deposit is successful.
     */
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        accountService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }

    /**
     * Withdraws an amount from a bank account.
     * @param id the ID of the account to withdraw from.
     * @param amount the amount to withdraw.
     * @return a ResponseEntity with an OK status if the withdrawal is successful.
     */
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        accountService.withdraw(id, amount);
        return ResponseEntity.ok().build();
    }
}
