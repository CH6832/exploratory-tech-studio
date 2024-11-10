package com.cms.contract;

import com.cms.contract.datamodel.Contract;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test class for testing the ContractController endpoints.
 * <p>
 * This class uses Spring's @WebMvcTest and Mockito to mock dependencies like
 * the ContractRepository and test the behavior of the controller in isolation.
 * It tests the HTTP endpoints of the ContractController class to ensure the
 * correctness of CRUD operations.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc used for making HTTP requests to the controller

    @Mock
    private ContractRepository contractRepository; // Mocking the repository to isolate controller tests

    @InjectMocks
    private ContractController contractController; // Injecting mocked repository into the controller

    private Contract contract; // Sample contract to be used in tests

    /**
     * Setup method that runs before each test. Initializes mocks and creates a sample contract.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        contract = new Contract();
    }

    /**
     * Test for creating a new contract.
     * <p>
     * This test simulates an HTTP POST request to create a contract and verifies
     * that the contract is saved and the correct response is returned.
     * </p>
     *
     * @throws Exception If the test fails or encounters an issue.
     */
    @Test
    void createContract() throws Exception {
        // Mock the repository save method
        when(contractRepository.save(any(Contract.class))).thenReturn(contract);

        // Perform POST request to create a new contract
        mockMvc.perform(post("/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contract)))
                .andExpect(status().isCreated()) // Expect HTTP status 201 (Created)
                .andExpect(jsonPath("$.title").value("Contract A")) // Verify the title in the response
                .andExpect(jsonPath("$.partyA").value("Company X")) // Verify party A in the response
                .andExpect(jsonPath("$.status").value("draft")); // Verify the status

        // Verify that the repository save method was called once
        verify(contractRepository, times(1)).save(any(Contract.class));
    }

    /**
     * Test for retrieving a contract by its ID when the contract exists.
     * <p>
     * This test simulates an HTTP GET request to retrieve a contract by its ID and verifies
     * that the correct contract is returned.
     * </p>
     *
     * @throws Exception If the test fails or encounters an issue.
     */
    @Test
    void getContractById_Success() throws Exception {
        // Mock the repository findById method to return the contract
        when(contractRepository.findById("1")).thenReturn(Optional.of(contract));

        // Perform GET request to retrieve contract by ID
        mockMvc.perform(get("/contracts/1"))
                .andExpect(status().isOk()) // Expect HTTP status 200 (OK)
                .andExpect(jsonPath("$.title").value("Contract A")) // Verify the title in the response
                .andExpect(jsonPath("$.partyA").value("Company X")) // Verify party A in the response
                .andExpect(jsonPath("$.status").value("draft")); // Verify the status

        // Verify that the repository findById method was called once
        verify(contractRepository, times(1)).findById("1");
    }

    /**
     * Test for retrieving a contract by its ID when the contract is not found.
     * <p>
     * This test simulates an HTTP GET request for a contract that does not exist
     * and verifies that a 404 (Not Found) response is returned.
     * </p>
     *
     * @throws Exception If the test fails or encounters an issue.
     */
    @Test
    void getContractById_NotFound() throws Exception {
        // Mock the repository findById method to return an empty Optional (contract not found)
        when(contractRepository.findById("1")).thenReturn(Optional.empty());

        // Perform GET request to retrieve a non-existing contract by ID
        mockMvc.perform(get("/contracts/1"))
                .andExpect(status().isNotFound()); // Expect HTTP status 404 (Not Found)

        // Verify that the repository findById method was called once
        verify(contractRepository, times(1)).findById("1");
    }

    /**
     * Test for updating an existing contract by its ID.
     * <p>
     * This test simulates an HTTP PUT request to update a contract and verifies
     * that the updated contract is returned with the correct values.
     * </p>
     *
     * @throws Exception If the test fails or encounters an issue.
     */
    @Test
    void updateContract() throws Exception {
        // Create an updated contract object
        Contract updatedContract = new Contract();

        // Mock the repository methods
        when(contractRepository.findById("1")).thenReturn(Optional.of(contract));
        when(contractRepository.save(any(Contract.class))).thenReturn(updatedContract);

        // Perform PUT request to update the contract by ID
        mockMvc.perform(put("/contracts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedContract)))
                .andExpect(status().isOk()) // Expect HTTP status 200 (OK)
                .andExpect(jsonPath("$.title").value("Contract A Updated")) // Verify updated title
                .andExpect(jsonPath("$.partyB").value("Company Z")) // Verify updated party B
                .andExpect(jsonPath("$.status").value("active")); // Verify updated status

        // Verify that the repository findById and save methods were called once each
        verify(contractRepository, times(1)).findById("1");
        verify(contractRepository, times(1)).save(any(Contract.class));
    }

    /**
     * Test for deleting a contract by its ID.
     * <p>
     * This test simulates an HTTP DELETE request to delete a contract and verifies
     * that the correct status (204 No Content) is returned.
     * </p>
     *
     * @throws Exception If the test fails or encounters an issue.
     */
    @Test
    void deleteContract() throws Exception {
        // Mock the repository deleteById method
        doNothing().when(contractRepository).deleteById("1");

        // Perform DELETE request to delete the contract by ID
        mockMvc.perform(delete("/contracts/1"))
                .andExpect(status().isNoContent()); // Expect HTTP status 204 (No Content)

        // Verify that the repository deleteById method was called once
        verify(contractRepository, times(1)).deleteById("1");
    }
}
