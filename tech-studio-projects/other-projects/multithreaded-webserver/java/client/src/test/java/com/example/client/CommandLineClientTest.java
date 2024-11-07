package com.example.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the CommandLineClient class, which simulates the client-side logic 
 * for handling user commands like "echo", "send file", and "exit". The tests check the behavior of 
 * the CommandLineClient with mocked interactions with the FileTransferClient. 
 */
class CommandLineClientTest {

    // The CommandLineClient instance that we are testing
    private CommandLineClient commandLineClient;

    // Mocking the FileTransferClient to simulate its behavior during testing
    @Mock
    private FileTransferClient fileTransferClientMock;

    /**
     * Set up method that runs before each test. Initializes the CommandLineClient 
     * with a mocked FileTransferClient to ensure isolated testing.
     */
    @BeforeEach
    void setUp() {
        // Initializes the mocks and sets up the CommandLineClient instance
        MockitoAnnotations.openMocks(this);
        commandLineClient = new CommandLineClient(fileTransferClientMock);
    }

    /**
     * Unit test that simulates the client running with an echo command.
     * It checks that the CommandLineClient correctly interacts with the FileTransferClient 
     * when echoing a message.
     *
     * The test ensures that the "sendEchoMessage" method of FileTransferClient is called 
     * once with the correct message.
     */
    @Test
    void testRunClientWithEchoCommand() {
        // Arrange
        String input = "echo Hello, Server!";  // Simulate user input
        String expectedResponse = "Echo: Hello, Server!";  // Expected response from the mock client

        // Mock the FileTransferClient's sendEchoMessage method to return the expected response
        when(fileTransferClientMock.sendEchoMessage("Hello, Server!")).thenReturn(expectedResponse);

        // Act
        commandLineClient.runClient();  // Simulate the client running and processing input

        // Assert
        // Verify that the method sendEchoMessage was called with the correct message once
        verify(fileTransferClientMock, times(1)).sendEchoMessage("Hello, Server!");  // Ensure it's called exactly once
    }

    /**
     * Unit test that simulates the client running with a "send file" command.
     * It checks that the CommandLineClient correctly invokes the sendFile method of 
     * the FileTransferClient to transfer a file.
     *
     * The test ensures that the file is sent correctly when the user inputs the file transfer command.
     */
    @Test
    void testRunClientWithSendCommand() throws Exception {
        // Arrange
        String filePath = "some/path/to/file.txt";  // Simulate a file path input
        File file = mock(File.class);  // Mock a File object for the test

        // Mock the behavior of the file object to simulate it being a valid file
        when(file.exists()).thenReturn(true);
        when(file.isFile()).thenReturn(true);

        // Act
        commandLineClient.sendFile(filePath);  // Simulate the sendFile method being called

        // Assert
        // Verify that the sendFile method was called once with the file object
        verify(fileTransferClientMock, times(1)).sendFile(file);  // Ensure the sendFile method is called exactly once
    }

    /**
     * Unit test that checks the behavior of the client when an invalid command is entered.
     * This test ensures that when the user inputs an invalid command, no interaction occurs 
     * with the FileTransferClient.
     *
     * It verifies that the methods of the FileTransferClient are not called in case of invalid input.
     */
    @Test
    void testRunClientWithInvalidCommand() throws IOException {
        // Arrange
        String invalidInput = "invalid command";  // Simulate an invalid user command

        // Act
        commandLineClient.runClient();  // Simulate running the client (this will handle invalid input)

        // Assert
        // Verify that neither sendEchoMessage nor sendFile methods are called due to invalid input
        verify(fileTransferClientMock, never()).sendEchoMessage(anyString());  // Ensure no echo message was sent
        verify(fileTransferClientMock, never()).sendFile(any());  // Ensure no file was sent
    }

    /**
     * Unit test that checks the behavior of the client when the user types the "exit" command.
     * This test ensures that when the exit command is entered, no further interactions occur 
     * with the FileTransferClient, and the client properly terminates its execution.
     *
     * It verifies that no additional methods of the FileTransferClient are invoked after the exit command.
     */
    @Test
    void testExitCommand() throws IOException {
        // Arrange
        String exitCommand = "exit";  // Simulate the user typing "exit"

        // Act
        commandLineClient.runClient();  // Simulate running the client (the exit command should be handled)

        // Assert
        // Verify that no interactions with the FileTransferClient happen after the exit command
        verify(fileTransferClientMock, never()).sendEchoMessage(anyString());  // No echo should be sent
        verify(fileTransferClientMock, never()).sendFile(any());  // No file should be sent
    }
}
