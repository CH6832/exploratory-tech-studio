package com.example.banking.service;

import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Account account1 = new Account();
        account1.setId(1L);
        Account account2 = new Account();
        account2.setId(2L);
        List<Account> accounts = Arrays.asList(account1, account2);
        when(accountRepository.findAll()).thenReturn(accounts);

        // Act
        List<Account> foundAccounts = accountService.findAll();

        // Assert
        assertNotNull(foundAccounts);
        assertEquals(2, foundAccounts.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act
        Account foundAccount = accountService.findById(1L);

        // Assert
        assertNotNull(foundAccount);
        assertEquals(1L, foundAccount.getId());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdNotFound() {
        // Arrange
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Account foundAccount = accountService.findById(1L);

        // Assert
        assertNull(foundAccount);
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByAccountNumber() {
        // Arrange
        Account account = new Account();
        account.setAccountNumber("12345");
        when(accountRepository.findByAccountNumber("12345")).thenReturn(account);

        // Act
        Account foundAccount = accountService.findByAccountNumber("12345");

        // Assert
        assertNotNull(foundAccount);
        assertEquals("12345", foundAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("12345");
    }

    @Test
    public void testSave() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        Account savedAccount = accountService.save(account);

        // Assert
        assertNotNull(savedAccount);
        assertEquals(1L, savedAccount.getId());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testDeposit() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        accountService.deposit(1L, BigDecimal.valueOf(500));

        // Assert
        assertEquals(BigDecimal.valueOf(1500), account.getBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testWithdraw() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        accountService.withdraw(1L, BigDecimal.valueOf(300));

        // Assert
        assertEquals(BigDecimal.valueOf(700), account.getBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(100));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.withdraw(1L, BigDecimal.valueOf(200));
        });
        assertEquals("Insufficient funds", exception.getMessage());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(0)).save(account);
    }
}
