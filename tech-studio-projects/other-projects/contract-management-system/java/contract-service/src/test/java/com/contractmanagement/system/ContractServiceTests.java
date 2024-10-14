package com.contractmanagement.system;

import com.contractmanagement.system.Contract;
import com.contractmanagement.system.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContractServiceTests {

    @InjectMocks
    private ContractService contractService;

    @Mock
    private ContractRepository contractRepository;

    private Contract contract;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contract = new Contract("Service Agreement", "Client A", new java.util.Date(), new java.util.Date(), 1000.00);
    }

    @Test
    void testCreateContract() {
        when(contractRepository.save(contract)).thenReturn(contract);
        
        Contract createdContract = contractService.createContract(contract);
        
        assertNotNull(createdContract);
        assertEquals("Service Agreement", createdContract.getContractName());
        verify(contractRepository, times(1)).save(contract);
    }

    @Test
    void testGetContractById() {
        when(contractRepository.findById(1L)).thenReturn(Optional.of(contract));
        
        Contract foundContract = contractService.getContractById(1L);
        
        assertNotNull(foundContract);
        assertEquals("Service Agreement", foundContract.getContractName());
        verify(contractRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateContract() {
        when(contractRepository.existsById(1L)).thenReturn(true);
        when(contractRepository.save(contract)).thenReturn(contract);
        
        Contract updatedContract = contractService.updateContract(1L, contract);
        
        assertNotNull(updatedContract);
        assertEquals("Service Agreement", updatedContract.getContractName());
        verify(contractRepository, times(1)).save(contract);
    }

    @Test
    void testDeleteContract() {
        when(contractRepository.existsById(1L)).thenReturn(true);
        
        contractService.deleteContract(1L);
        
        verify(contractRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetContractNotFound() {
        when(contractRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            contractService.getContractById(1L);
        });

        assertEquals("Contract not found with id: 1", exception.getMessage());
    }
}
