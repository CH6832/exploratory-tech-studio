package com.contractmanagement.system;

import com.contractmanagement.system.Contract;
import com.contractmanagement.system.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractControllerTests {
    @InjectMocks
    private ContractController contractController;

    @Mock
    private ContractService contractService;

    private Contract contract;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contract = new Contract("Service Agreement", "Client A", new java.util.Date(), new java.util.Date(), 1000.00);
    }

    @Test
    void testCreateContract() {
        when(contractService.createContract(contract)).thenReturn(contract);

        ResponseEntity<Contract> response = contractController.createContract(contract);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(contract, response.getBody());
        verify(contractService, times(1)).createContract(contract);
    }

    @Test
    void testGetContract() {
        when(contractService.getContractById(1L)).thenReturn(contract);

        ResponseEntity<Contract> response = contractController.getContract(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contract, response.getBody());
        verify(contractService, times(1)).getContractById(1L);
    }

    @Test
    void testUpdateContract() {
        when(contractService.updateContract(1L, contract)).thenReturn(contract);

        ResponseEntity<Contract> response = contractController.updateContract(1L, contract);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contract, response.getBody());
        verify(contractService, times(1)).updateContract(1L, contract);
    }

    @Test
    void testDeleteContract() {
        ResponseEntity<Void> response = contractController.deleteContract(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(contractService, times(1)).deleteContract(1L);
    }
}
