package com.example.banking.service;

import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction deposit(Long accountId, BigDecimal amount) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        accountService.deposit(accountId, amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setType("DEPOSIT");

        return save(transaction);
    }

    public Transaction withdraw(Long accountId, BigDecimal amount) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        accountService.withdraw(accountId, amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount.negate());
        transaction.setDate(LocalDateTime.now());
        transaction.setType("WITHDRAWAL");

        return save(transaction);
    }

    public Transaction transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account fromAccount = accountService.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountService.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found");
        }
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        accountService.withdraw(fromAccount.getId(), amount);
        accountService.deposit(toAccount.getId(), amount);

        Transaction fromTransaction = new Transaction();
        fromTransaction.setAccount(fromAccount);
        fromTransaction.setAmount(amount.negate());
        fromTransaction.setDate(LocalDateTime.now());
        fromTransaction.setType("TRANSFER");

        Transaction toTransaction = new Transaction();
        toTransaction.setAccount(toAccount);
        toTransaction.setAmount(amount);
        toTransaction.setDate(LocalDateTime.now());
        toTransaction.setType("TRANSFER");

        save(fromTransaction);
        return save(toTransaction);
    }
}
