package com.example.banking.controller;

import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        Account account1 = new Account();
        account1.setId(1L);
        Account account2 = new Account();
        account2.setId(2L);
        when(accountService.findAll()).thenReturn(Arrays.asList(account1, account2));

        // Act & Assert
        mockMvc.perform(get("/api/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        when(accountService.findById(anyLong())).thenReturn(account);

        // Act & Assert
        mockMvc.perform(get("/api/accounts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        // Arrange
        when(accountService.findById(anyLong())).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/accounts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        when(accountService.save(any(Account.class))).thenReturn(account);

        // Act & Assert
        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Account())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testDeposit() throws Exception {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        when(accountService.findById(anyLong())).thenReturn(account);

        // Act & Assert
        mockMvc.perform(put("/api/accounts/1/deposit")
                        .param("amount", "500")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(accountService, times(1)).deposit(anyLong(), any(BigDecimal.class));
    }

    @Test
    public void testWithdraw() throws Exception {
        // Arrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        when(accountService.findById(anyLong())).thenReturn(account);

        // Act & Assert
        mockMvc.perform(put("/api/accounts/1/withdraw")
                        .param("amount", "300")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(accountService, times(1)).withdraw(anyLong(), any(BigDecimal.class));
    }

    @Test
    public void testWithdrawInsufficientFunds() throws Exception {
        // Arrange
        when(accountService.findById(anyLong())).thenThrow(new IllegalArgumentException("Insufficient funds"));

        // Act & Assert
        mockMvc.perform(put("/api/accounts/1/withdraw")
                        .param("amount", "2000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Insufficient funds"));
    }
}
