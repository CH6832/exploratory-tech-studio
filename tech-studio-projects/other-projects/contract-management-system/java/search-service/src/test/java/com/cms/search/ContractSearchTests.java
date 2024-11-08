package com.cms.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.cms.contract.ContractController;
import com.cms.contract.ContractService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContractController.class)  // Specify the controller to test
public class ContractSearchTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;

    // Sample contract data
    private static final String CONTRACT_TITLE_A = "Contract A";
    private static final String CONTRACT_TITLE_B = "Big Contract";
    private static final String CONTRACT_TITLE_C = "Special-Contract!";

    @BeforeEach
    public void setup() {
        // Initialize any mock data or mock service behaviors here if needed
    }

    @Test
    public void testSearchByContractTitleExactMatch() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_A))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A));
    }

    @Test
    public void testSearchByContractTitleCaseInsensitive() throws Exception {
        mockMvc.perform(get("/contracts/search?title=contract%20a"))  // Lowercase 'contract a'
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A));  // Should return Contract A
    }

    @Test
    public void testSearchWithMultipleWordsInTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_B))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_B));
    }

    @Test
    public void testSearchWithNonExistentTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=NonExistentContract"))
                .andExpect(status().isNotFound());  // Should return 404 or empty list
    }

    @Test
    public void testSearchWithEmptyTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title="))
                .andExpect(status().isBadRequest());  // Should return a Bad Request
    }

    @Test
    public void testSearchWithPartialTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A)) // Expected partial match
                .andExpect(jsonPath("$[1].title").value(CONTRACT_TITLE_B)); // Another contract that matches
    }

    @Test
    public void testSearchWithSpecialCharactersInTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_C))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_C));
    }

    @Test
    public void testSearchWithMultipleParameters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A&signing_date=2024-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A))
                .andExpect(jsonPath("$[0].signing_date").value("2024-01-01"));
    }

    @Test
    public void testSearchWithInvalidQueryParameter() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A&unknown_field=someValue"))
                .andExpect(status().isBadRequest());  // Invalid parameter
    }

    @Test
    public void testSearchWithInvalidTitleFormat() throws Exception {
        mockMvc.perform(get("/contracts/search?title=@@@@@@@"))
                .andExpect(status().isBadRequest());  // Invalid title format
    }

    @Test
    public void testSearchWithURLSpecialCharacters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A%20with%20spaces"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Contract A with spaces"));
    }

    @Test
    public void testSearchUsingNonASCIICharacters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=契约A"))  // Chinese characters
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("契约A"));
    }

    @Test
    public void testSearchWithMultipleMatches() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A))
                .andExpect(jsonPath("$[1].title").value(CONTRACT_TITLE_B));  // Multiple contracts matching
    }

    @Test
    public void testSearchWithInvalidHTTPMethod() throws Exception {
        mockMvc.perform(get("/contracts/search").contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isMethodNotAllowed());  // Method should not be allowed
    }
}
