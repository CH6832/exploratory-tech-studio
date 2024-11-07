package com.example.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the FileTransferClient class. The tests simulate the behavior
 * of the client-side logic for interacting with the server's echo and file transfer APIs.
 * The interactions with the RestTemplate are mocked to isolate tests from actual HTTP requests.
 */
class FileTransferClientTest {

    // The instance of FileTransferClient we are testing
    private FileTransferClient fileTransferClient;

    // Mocking the RestTemplate to simulate HTTP requests without actually making them
    private RestTemplate restTemplateMock;

    // Mocked server URL (typically would be injected via application.properties or configuration)
    @Value("${server.url}")
    private String serverUrl;

    /**
     * Setup method that runs before each test. Initializes the FileTransferClient 
     * and RestTemplate mocks, so the tests can simulate HTTP interactions without 
     * making actual network calls.
     */
    @BeforeEach
    void setUp() {
        restTemplateMock = mock(RestTemplate.class);  // Mocking RestTemplate to simulate HTTP calls
        fileTransferClient = new FileTransferClient(restTemplateMock);  // Initializing FileTransferClient with mocked RestTemplate
    }

    /**
     * Unit test to verify the behavior of the sendEchoMessage method in the FileTransferClient.
     * This test simulates a successful echo request and checks if the correct response is returned.
     * 
     * It ensures that the RestTemplate's postForObject method is called with the correct URL and message, 
     * and that the expected response is returned.
     */
    @Test
    void testSendEchoMessage() {
        // Arrange
        String message = "Hello, Server!";  // The message to be echoed
        String expectedResponse = "Echo: Hello, Server!";  // The expected server response
        String url = serverUrl + "/api/echo";  // The URL to send the echo request to

        // Mocking the RestTemplate's postForObject to simulate server response
        when(restTemplateMock.postForObject(url, message, String.class)).thenReturn(expectedResponse);

        // Act
        String response = fileTransferClient.sendEchoMessage(message);  // Sending the message through the client

        // Assert
        assertEquals(expectedResponse, response);  // Verifying the response matches the expected result
        verify(restTemplateMock, times(1)).postForObject(url, message, String.class);  // Verifying that the method was called exactly once
    }

    /**
     * Unit test to verify the sendFile method of FileTransferClient.
     * This test simulates sending a file to the server and checks if the correct HTTP request is made.
     * 
     * It mocks the creation of an HTTP request entity (including headers and file data) and verifies 
     * that RestTemplate's postForEntity method is called with the correct parameters.
     */
    @Test
    void testSendFile() throws IOException {
        // Arrange
        String url = serverUrl + "/api/file-transfer";  // URL for file transfer API
        File file = mock(File.class);  // Mock the File object
        when(file.exists()).thenReturn(true);  // Simulating that the file exists
        when(file.isFile()).thenReturn(true);  // Simulating that it is a valid file

        byte[] fileBytes = new byte[] {1, 2, 3};  // Mock file content
        when(file.length()).thenReturn((long) fileBytes.length);  // Mock file length

        HttpHeaders headers = new HttpHeaders();  // Set the content type header to indicate a file transfer
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  // Indicating file upload
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(fileBytes, headers);  // Wrap file data in HTTP entity

        ResponseEntity<String> responseEntity = ResponseEntity.ok("File uploaded successfully!");  // Mocking server response for a successful file upload
        when(restTemplateMock.postForEntity(url, requestEntity, String.class)).thenReturn(responseEntity);

        // Act
        fileTransferClient.sendFile(file);  // Calling the sendFile method to simulate sending the file

        // Assert
        // Verifying that the postForEntity method is called once with the correct parameters
        verify(restTemplateMock, times(1)).postForEntity(url, requestEntity, String.class);  // Ensure it was called once
    }

    /**
     * Unit test to verify the behavior of sendFile when the file does not exist.
     * This test ensures that an exception is thrown when the user attempts to send a non-existing file.
     * 
     * It mocks a file that does not exist and checks if the method throws an IllegalArgumentException.
     */
    @Test
    void testSendFileWhenFileDoesNotExist() throws IOException {
        // Arrange
        File file = mock(File.class);  // Mocking the File object
        when(file.exists()).thenReturn(false);  // Simulating that the file does not exist

        // Act & Assert
        // We expect the sendFile method to throw an IllegalArgumentException when the file does not exist
        assertThrows(IllegalArgumentException.class, () -> fileTransferClient.sendFile(file));  // Verifying that the exception is thrown
    }
}
