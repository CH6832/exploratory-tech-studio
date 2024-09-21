package com.example.banking.repository;

import com.example.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber the account number.
     * @return the account with the given account number or null if not found.
     */
    Account findByAccountNumber(String accountNumber);
}
