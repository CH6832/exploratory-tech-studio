package com.example.banking.service;

import com.example.banking.entity.Customer;
import com.example.banking.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(2L);
        List<Customer> customers = Arrays.asList(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> foundCustomers = customerService.findAll();

        // Assert
        assertNotNull(foundCustomers);
        assertEquals(2, foundCustomers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customer foundCustomer = customerService.findById(1L);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(1L, foundCustomer.getId());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdNotFound() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Customer foundCustomer = customerService.findById(1L);

        // Assert
        assertNull(foundCustomer);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.save(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertEquals(1L, savedCustomer.getId());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        doNothing().when(customerRepository).deleteById(1L);

        // Act
        customerService.deleteById(1L);

        // Assert
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
