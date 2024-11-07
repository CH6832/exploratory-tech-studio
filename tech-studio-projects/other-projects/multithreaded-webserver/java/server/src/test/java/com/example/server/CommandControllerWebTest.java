package com.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains integration tests for the CommandController.
 * The tests focus on ensuring that the REST API endpoints in CommandController 
 * (like `/api/echo` and `/api/file-transfer`) work as expected by mocking 
 * the service layer and performing mock HTTP requests.
 *
 * @WebMvcTest is used to test the web layer of the application without loading 
 * the entire Spring Boot context. It focuses on the controller's functionality.
 */
@WebMvcTest(CommandController.class)
class CommandControllerWebTest {

    // The MockMvc instance is used to perform HTTP requests and assertions against the Controller layer.
    @Autowired
    private MockMvc mockMvc;

    // Mocked service used by the CommandController.
    @Mock
    private FileTransferService fileTransferServiceMock;

    // The actual controller that we are testing. This will be injected with the mock service.
    @InjectMocks
    private CommandController commandController;

    // ObjectMapper to convert objects to JSON and vice versa for testing HTTP request bodies.
    private ObjectMapper objectMapper;

    /**
     * Setup method to initialize any objects needed for the tests.
     * This method is run before each test method to ensure proper test isolation.
     */
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(); // Initialize ObjectMapper instance for converting objects to JSON
    }

    /**
     * Test the `/api/echo` endpoint.
     * This test checks that the server correctly processes an "echo" message and returns the expected response.
     * 
     * The test involves:
     * 1. Sending a message to the endpoint.
     * 2. Verifying that the correct response is returned (the server echoes the message).
     * 3. Ensuring that the service method `sendEchoMessage` is called with the correct message.
     * 
     * @throws Exception if the test fails to perform the mock request or assertion
     */
    @Test
    void testEchoMessage() throws Exception {
        // Arrange: Prepare a sample message and define the expected response
        String message = "Hello, Server!"; // Message to be sent to the server
        String expectedResponse = "Echo: Hello, Server!"; // Expected response from the server

        // When: Mock the behavior of the fileTransferServiceMock to return the expected response
        when(fileTransferServiceMock.sendEchoMessage(message)).thenReturn(expectedResponse);

        // Act & Assert: Perform a POST request to the /api/echo endpoint with the message as the request body
        mockMvc.perform(post("/api/echo")
                .contentType(MediaType.APPLICATION_JSON)  // Set the content type to JSON
                .content(objectMapper.writeValueAsString(message)))  // Convert the message to JSON and send as the body
                .andExpect(status().isOk())  // Expect a 200 OK status code
                .andExpect(content().string(expectedResponse));  // Verify that the response matches the expected string

        // Verify that the mock service method sendEchoMessage was called with the correct message
        verify(fileTransferServiceMock, times(1)).sendEchoMessage(message);
    }

    /**
     * Test the `/api/file-transfer` endpoint.
     * This test simulates sending a file's byte data to the server and checks whether it was processed correctly.
     * 
     * The test involves:
     * 1. Sending a file (represented as byte data) to the server.
     * 2. Verifying that the file is saved correctly.
     * 
     * @throws Exception if the test fails to perform the mock request or assertion
     */
    @Test
    void testFileTransfer() throws Exception {
        // Arrange: Prepare file data to be sent
        String fileName = "testFile.txt"; // The name of the file being sent
        byte[] fileData = new byte[]{1, 2, 3, 4, 5}; // Simulated byte data of the file
        
        // Act & Assert: Perform a POST request to the /api/file-transfer endpoint with the file data
        mockMvc.perform(post("/api/file-transfer")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)  // Set the content type to binary/octet-stream (file transfer)
                .content(fileData))  // Send the file data as the body of the request
                .andExpect(status().isOk());  // Expect a 200 OK response

        // Verify that the mock service method saveFile was called with the correct file data and name
        verify(fileTransferServiceMock, times(1)).saveFile(fileName, fileData);
    }

    /**
     * Test the `/api/file-transfer` endpoint when the file does not exist.
     * This test simulates the scenario where a file is attempted to be saved, 
     * but an IOException occurs due to the file not existing or being invalid.
     * 
     * The test involves:
     * 1. Sending file data that would result in an exception being thrown in the service.
     * 2. Verifying that the error is handled appropriately and a proper status is returned.
     * 
     * @throws Exception if the test fails to perform the mock request or assertion
     */
    @Test
    void testHandleFileTransferWhenFileDoesNotExist() throws Exception {
        // Arrange: Prepare file data and mock behavior of the service to throw an exception
        String fileName = "nonExistentFile.txt"; // The name of the non-existent file
        byte[] fileData = new byte[]{1, 2, 3, 4, 5}; // Simulated byte data of the file
        
        // Mock the behavior of the fileTransferServiceMock to throw an IOException when trying to save the file
        // Mockito.when(fileTransferServiceMock.saveFile(fileName, fileData)).thenThrow(new IOException("File does not exist"));

        // Act & Assert: Perform a POST request to the /api/file-transfer endpoint with the file data
        mockMvc.perform(post("/api/file-transfer")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)  // Set content type for binary data
                .content(fileData))  // Send file data
                .andExpect(status().isInternalServerError());  // Expect a 500 Internal Server Error status due to the exception

        // Verify that the mock service method saveFile was called once
        verify(fileTransferServiceMock, times(1)).saveFile(fileName, fileData);
    }

	private OngoingStubbing<Object> when(Object saveFile) {
		// TODO Auto-generated method stub
		return null;
	}
}
