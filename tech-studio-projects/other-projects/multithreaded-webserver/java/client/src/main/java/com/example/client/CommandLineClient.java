package com.example.client;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * CommandLineClient class is responsible for interacting with the server.
 * It provides a CLI for sending messages and files to the server.
 */
@Component
public class CommandLineClient {

    private final FileTransferClient fileTransferClient;

    public CommandLineClient(FileTransferClient fileTransferClient) {
        this.fileTransferClient = fileTransferClient;
    }

    /**
     * Method to run the client-side application. This method continuously prompts
     * the user for input (messages or file commands) and communicates with the server.
     */
    public void runClient() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Infinite loop to keep accepting user input until 'exit' is typed
        while (true) {
            System.out.println("Enter a command (echo <message>, send <file-path>, exit):");
            userInput = scanner.nextLine();
            
            if (userInput.startsWith("echo")) {
                // If the user types 'echo <message>', send it to the server
                String message = userInput.substring(5);
                sendEchoMessage(message);
            } else if (userInput.startsWith("send")) {
                // If the user types 'send <file-path>', send the file to the server
                String filePath = userInput.substring(5);
                sendFile(filePath);
            } else if (userInput.equals("exit")) {
                // Exit the client if 'exit' is typed
                System.out.println("Exiting client...");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Sends a message to the server to be echoed back.
     *
     * @param message The message to be sent to the server.
     */
    void sendEchoMessage(String message) {
        String serverResponse = fileTransferClient.sendEchoMessage(message);
        System.out.println("Server Response: " + serverResponse);
    }

    /**
     * Sends a file to the server for processing.
     *
     * @param filePath The path to the file to be sent.
     */
    void sendFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                fileTransferClient.sendFile(file);
            } catch (IOException e) {
                System.out.println("Error sending file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid file path.");
        }
    }
}
