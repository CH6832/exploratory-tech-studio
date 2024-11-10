package com.cms.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the LoggingServiceApplication.
 * <p>
 * This test class is used to ensure that the Spring Boot application context is loading properly and that 
 * logging functionality works as expected. It tests the integration between the controller and the logging 
 * functionality.
 * </p>
 */
@SpringBootTest
class LoggingServiceApplicationTests {

    // Autowired MockMvc to simulate HTTP requests for testing REST controllers
    @Autowired
    private LoggingController loggingController;

    // MockMvc is used to simulate HTTP requests to the LoggingController
    private MockMvc mockMvc;

    /**
     * This method sets up the MockMvc instance before each test.
     * It ensures that the LoggingController is used to handle requests.
     */
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(loggingController).build();
    }

    /**
     * Test to ensure that the Spring Boot application context is loading properly.
     * <p>
     * This simple test verifies that the Spring Boot context loads without issues. It does not perform any
     * assertions on the application behavior, but it ensures that all the beans are correctly wired.
     * </p>
     */
    @Test
    void contextLoads() {
        // This test ensures that the Spring Boot application context loads without exceptions.
    }

    /**
     * Test to check if the LoggingController handles log requests correctly.
     * <p>
     * This test sends a POST request with a log entry to the /logs endpoint and checks if the response
     * status is OK (200). It verifies that the LoggingController processes the log request and responds
     * appropriately.
     * </p>
     *
     * @throws Exception if the request fails or the response is not as expected
     */
    @Test
    void testLogMessage() throws Exception {
        // Setting up the log entry to be sent in the POST request
        String logEntryJson = "{\"level\": \"info\", \"message\": \"This is an informational log message.\"}";

        // Sending a POST request to the /logs endpoint
        mockMvc.perform(post("/logs")
                .contentType("application/json")
                .content(logEntryJson))
                .andExpect(status().isOk()); // Expecting HTTP 200 OK response
    }

    /**
     * Test to check that an invalid log level does not cause failures in the system.
     * <p>
     * This test checks if the LoggingController can handle an unrecognized log level gracefully.
     * In this case, the controller should default to "DEBUG" if the log level is not recognized.
     * </p>
     *
     * @throws Exception if the request fails or the response is not as expected
     */
    @Test
    void testInvalidLogLevel() throws Exception {
        // Setting up the log entry with an invalid log level
        String logEntryJson = "{\"level\": \"invalid\", \"message\": \"This should default to debug.\"}";

        // Sending a POST request with the invalid log level
        mockMvc.perform(post("/logs")
                .contentType("application/json")
                .content(logEntryJson))
                .andExpect(status().isOk()); // Expecting HTTP 200 OK, as the system should default to "DEBUG"
    }
}
