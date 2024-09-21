package com.example.banking.service;

import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Finds all accounts.
     *
     * @return a list of all accounts.
     */
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * Finds an account by its ID.
     *
     * @param id the ID of the account.
     * @return the account with the given ID or null if not found.
     */
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber the account number.
     * @return the account with the given account number or null if not found.
     */
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    /**
     * Saves an account to the repository.
     *
     * @param account the account entity to be saved.
     * @return the saved account.
     */
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id the ID of the account to be deleted.
     */
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    /**
     * Deposits a specified amount into an account.
     *
     * @param accountId the ID of the account to deposit into.
     * @param amount the amount to deposit.
     */
    public void deposit(Long accountId, BigDecimal amount) {
        Account account = findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    /**
     * Withdraws a specified amount from an account.
     *
     * @param accountId the ID of the account to withdraw from.
     * @param amount the amount to withdraw.
     */
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}
