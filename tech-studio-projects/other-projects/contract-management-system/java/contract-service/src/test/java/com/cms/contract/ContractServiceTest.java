package com.cms.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cms.contract.datamodel.Contract;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit test class for testing the ContractService methods.
 * <p>
 * This class uses Mockito for mocking the ContractRepository and verifies the behavior
 * of the ContractService class methods. It tests the CRUD operations within the service layer.
 * </p>
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository; // Mocking the repository dependency

    @InjectMocks
    private ContractService contractService; // Injecting the mocked repository into the service

    /**
     * Test for creating a new contract.
     * <p>
     * This test verifies that the createContract method in ContractService works as expected,
     * by calling the save method of the contractRepository and ensuring the returned contract
     * has the correct title and is not null.
     * </p>
     */
    @Test
    public void testCreateContract() {
        // Create a sample contract
        Contract contract = new Contract();

        // Mock the repository save method to return the sample contract
        when(contractRepository.save(any(Contract.class))).thenReturn(contract);

        // Call the createContract method
        Contract createdContract = contractService.createContract(contract);

        // Verify the result
        assertNotNull(createdContract); // Assert that the created contract is not null
        assertEquals("Contract A", createdContract.getTitle()); // Assert the title is correct

        // Verify that the repository save method was called once with any contract
        verify(contractRepository, times(1)).save(any(Contract.class));
    }

    /**
     * Test for retrieving a contract by its ID.
     * <p>
     * This test verifies that the getContractById method in ContractService works correctly,
     * by mocking the repository to return an existing contract and ensuring the contract is found.
     * </p>
     */
    @Test
    public void testGetContractById_Success() {
        // Create a sample contract
        Contract contract = new Contract();

        // Mock the repository to return the sample contract when findById is called
        when(contractRepository.findById("1")).thenReturn(Optional.of(contract));

        // Call the getContractById method
        Optional<Contract> retrievedContract = contractService.getContractById("1");

        // Verify the result
        assertTrue(retrievedContract.isPresent()); // Assert that the contract is present
        assertEquals("Contract A", retrievedContract.get().getTitle()); // Assert the title is correct

        // Verify that the repository findById method was called once
        verify(contractRepository, times(1)).findById("1");
    }

    /**
     * Test for updating an existing contract.
     * <p>
     * This test verifies that the updateContract method in ContractService works correctly,
     * by mocking the repository to return the original contract, updating the contract, and
     * verifying the updated details.
     * </p>
     */
    @Test
    public void testUpdateContract() {
        // Create a sample contract
        Contract originalContract = new Contract();
        
        // Create an updated contract
        Contract updatedContract = new Contract();
        
        // Mock the repository to return the original contract when findById is called
        when(contractRepository.findById("1")).thenReturn(Optional.of(originalContract));
        
        // Mock the repository to return the updated contract when save is called
        when(contractRepository.save(any(Contract.class))).thenReturn(updatedContract);
        
        // Call the updateContract method
        Contract result = contractService.updateContract("1", updatedContract);

        // Verify the result
        assertNotNull(result); // Assert that the updated contract is not null
        assertEquals("Contract A Updated", result.getTitle()); // Assert the title is updated

        // Verify that the repository findById and save methods were called once
        verify(contractRepository, times(1)).findById("1");
        verify(contractRepository, times(1)).save(any(Contract.class));
    }

    /**
     * Test for deleting a contract by its ID.
     * <p>
     * This test verifies that the deleteContract method in ContractService works correctly,
     * by mocking the repository to confirm that the deleteById method is called as expected.
     * </p>
     */
    @Test
    public void testDeleteContract() {
        // Mock the repository to do nothing when deleteById is called
        doNothing().when(contractRepository).deleteById("1");

        // Call the deleteContract method
        contractService.deleteContract("1");

        // Verify that the repository deleteById method was called once
        verify(contractRepository, times(1)).deleteById("1");
    }
}
