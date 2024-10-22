package com.example.banking.controller;

import com.example.banking.entity.Customer;
import com.example.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customers.
 * Provides endpoints for customer operations such as creating, updating, deleting,
 * and retrieving customer information.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    /** Service class for customer-related operations. */
    @Autowired
    private CustomerService customerService;

    /**
     * Retrieves all customers.
     * @return a list of all customers.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    /**
     * Retrieves a customer by their ID.
     * @param id the ID of the customer to retrieve.
     * @return a ResponseEntity containing the customer if found, or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new customer.
     * @param customer the customer details to create.
     * @return the created customer.
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    /**
     * Updates an existing customer.
     * @param id the ID of the customer to update.
     * @param customer the updated customer details.
     * @return a ResponseEntity containing the updated customer if found, or a 404 Not Found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.findById(id);
        if (existingCustomer != null) {
            customer.setId(id); // Ensure the ID remains the same
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a customer by their ID.
     * @param id the ID of the customer to delete.
     * @return a ResponseEntity with no content if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
