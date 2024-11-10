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

/**
 * ContractSearchTests class contains test cases for searching contracts 
 * by their title through the ContractController. It uses MockMvc to simulate 
 * HTTP requests to test the ContractController's search functionality.
 */
@WebMvcTest(ContractController.class)  // Specify the controller to test
public class ContractSearchTests {

    // MockMvc is used to simulate HTTP requests in tests.
    @Autowired
    private MockMvc mockMvc;

    // Mocking the ContractService layer to isolate controller testing.
    @MockBean
    private ContractService contractService;

    // Constants for sample contract titles used in tests
    private static final String CONTRACT_TITLE_A = "Contract A";
    private static final String CONTRACT_TITLE_B = "Big Contract";
    private static final String CONTRACT_TITLE_C = "Special-Contract!";

    /**
     * Initializes mock data or service behaviors before each test.
     * For example, you could set up mock responses from the contractService.
     */
    @BeforeEach
    public void setup() {
        // Initialize any mock data or mock service behaviors here if needed
    }

    /**
     * Tests exact match search by contract title.
     * Expects a 200 OK status and verifies that the first result's title matches CONTRACT_TITLE_A.
     */
    @Test
    public void testSearchByContractTitleExactMatch() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_A))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A));
    }

    /**
     * Tests case-insensitive search by contract title.
     * Expects a 200 OK status and verifies the result matches CONTRACT_TITLE_A.
     */
    @Test
    public void testSearchByContractTitleCaseInsensitive() throws Exception {
        mockMvc.perform(get("/contracts/search?title=contract%20a"))  // Lowercase 'contract a'
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A));
    }

    /**
     * Tests search by title containing multiple words.
     * Expects a 200 OK status and verifies that the title matches CONTRACT_TITLE_B.
     */
    @Test
    public void testSearchWithMultipleWordsInTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_B))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_B));
    }

    /**
     * Tests search with a non-existent title.
     * Expects a 404 Not Found status indicating no contracts matched the search.
     */
    @Test
    public void testSearchWithNonExistentTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=NonExistentContract"))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests search with an empty title parameter.
     * Expects a 400 Bad Request status indicating the search parameter is invalid.
     */
    @Test
    public void testSearchWithEmptyTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title="))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests search with a partial match on contract title.
     * Expects a 200 OK status and verifies multiple matching results.
     */
    @Test
    public void testSearchWithPartialTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A))
                .andExpect(jsonPath("$[1].title").value(CONTRACT_TITLE_B));
    }

    /**
     * Tests search with special characters in the title.
     * Expects a 200 OK status and verifies the title matches CONTRACT_TITLE_C.
     */
    @Test
    public void testSearchWithSpecialCharactersInTitle() throws Exception {
        mockMvc.perform(get("/contracts/search?title=" + CONTRACT_TITLE_C))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_C));
    }

    /**
     * Tests search with multiple query parameters.
     * Expects a 200 OK status and verifies both title and signing date are correct in the result.
     */
    @Test
    public void testSearchWithMultipleParameters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A&signing_date=2024-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A))
                .andExpect(jsonPath("$[0].signing_date").value("2024-01-01"));
    }

    /**
     * Tests search with an invalid query parameter.
     * Expects a 400 Bad Request status, as 'unknown_field' is not valid.
     */
    @Test
    public void testSearchWithInvalidQueryParameter() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A&unknown_field=someValue"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests search with an invalid title format.
     * Expects a 400 Bad Request status due to invalid characters.
     */
    @Test
    public void testSearchWithInvalidTitleFormat() throws Exception {
        mockMvc.perform(get("/contracts/search?title=@@@@@@@"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests search using URL-encoded special characters.
     * Expects a 200 OK status and verifies the correct title is returned.
     */
    @Test
    public void testSearchWithURLSpecialCharacters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract%20A%20with%20spaces"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Contract A with spaces"));
    }

    /**
     * Tests search using non-ASCII characters.
     * Expects a 200 OK status and verifies the result matches the non-ASCII title.
     */
    @Test
    public void testSearchUsingNonASCIICharacters() throws Exception {
        mockMvc.perform(get("/contracts/search?title=契约A"))  // Chinese characters
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("契约A"));
    }

    /**
     * Tests search with multiple matches expected.
     * Expects a 200 OK status and verifies that multiple titles match the search.
     */
    @Test
    public void testSearchWithMultipleMatches() throws Exception {
        mockMvc.perform(get("/contracts/search?title=Contract"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(CONTRACT_TITLE_A))
                .andExpect(jsonPath("$[1].title").value(CONTRACT_TITLE_B));
    }

    /**
     * Tests search with an unsupported HTTP method.
     * Expects a 405 Method Not Allowed status since GET is the allowed method.
     */
    @Test
    public void testSearchWithInvalidHTTPMethod() throws Exception {
        mockMvc.perform(get("/contracts/search").contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isMethodNotAllowed());
    }
}
