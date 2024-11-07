package com.example.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

import java.io.File;

/**
 * This is the integration test class for the CommandLineClient class. It tests the integration between
 * the CommandLineClient and FileTransferClient, ensuring that the client behaves as expected when interacting
 * with the server. The tests simulate actual user commands (echoing messages and sending files) to verify the 
 * behavior of the client.
 */
@SpringBootTest
@ActiveProfiles("test")  // Use a test profile to isolate testing environment (such as database settings)
public class CommandLineClientIntegrationTest {

    // Mock the FileTransferClient bean so it doesn't make real network calls during tests
    @MockBean
    private FileTransferClient fileTransferClient;

    private CommandLineClient commandLineClient;

    /**
     * Set up method runs before each test case. It initializes the CommandLineClient 
     * instance with the mocked FileTransferClient, ensuring that tests are isolated and controlled.
     */
    @BeforeEach
    public void setUp() {
        // Initialize CommandLineClient with the mocked fileTransferClient
        commandLineClient = new CommandLineClient(fileTransferClient);
    }

    /**
     * Integration test to simulate the "echo" command from the user input.
     * This test checks if the CommandLineClient correctly interacts with the FileTransferClient
     * to send an echo message to the server and retrieve the correct response.
     * 
     * The `when` method is used to mock the behavior of `sendEchoMessage` in the FileTransferClient,
     * and the `verify` method ensures that the interaction with the mock was performed as expected.
     */
    @Test
    void testEchoCommandIntegration() {
        // Simulate user input message to echo
        String message = "Hello Server";

        // Mock the expected response when the sendEchoMessage method is called
        when(fileTransferClient.sendEchoMessage(message)).thenReturn("Echo: Hello Server");

        // Simulate running the CommandLineClient with the "echo" command
        commandLineClient.sendEchoMessage(message);

        // Verify that the sendEchoMessage method of FileTransferClient was called with the correct input
        verify(fileTransferClient).sendEchoMessage(message);  // Check that the method was called once with the expected message
    }

    /**
     * Integration test to simulate the "send file" command. This test verifies that 
     * the CommandLineClient can successfully pass the correct file path to the FileTransferClient
     * for file transfer.
     * 
     * The test simulates sending a file to the server, ensuring that the interaction between 
     * the CommandLineClient and FileTransferClient is correctly handled.
     */
    @Test
    void testSendFileCommandIntegration() throws Exception {
        // Specify a path to a sample file (ensure this file exists in the test resources)
        String filePath = "src/test/resources/sample.txt";
        File file = new File(filePath);

        // Simulate running the client with the send file command
        commandLineClient.sendFile(filePath);

        // Verify that the FileTransferClient's sendFile method was called with the correct file
        verify(fileTransferClient).sendFile(file);  // Check that the sendFile method was called once with the file
    }
}
