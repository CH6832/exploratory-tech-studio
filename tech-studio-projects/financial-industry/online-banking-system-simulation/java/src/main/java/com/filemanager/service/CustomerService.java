package com.example.banking.service;

import com.example.banking.entity.Customer;
import com.example.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Finds all customers.
     *
     * @return a list of all customers.
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Finds a customer by its ID.
     *
     * @param id the ID of the customer.
     * @return the customer with the given ID or null if not found.
     */
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    /**
     * Saves a customer to the repository.
     *
     * @param customer the customer entity to be saved.
     * @return the saved customer.
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id the ID of the customer to be deleted.
     */
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
