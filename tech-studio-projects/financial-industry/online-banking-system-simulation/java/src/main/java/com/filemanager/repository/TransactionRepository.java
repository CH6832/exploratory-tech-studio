package com.example.banking.repository;

import com.example.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds all transactions related to a specific account.
     *
     * @param accountId the ID of the account.
     * @return a list of transactions associated with the given account ID.
     */
    List<Transaction> findByAccountId(Long accountId);
}
