package com.example.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * CommandController class handles the incoming HTTP requests for commands such as echo and file transfers.
 */
@RestController
@RequestMapping("/api")
public class CommandController {

    // Autowiring the FileTransferService to handle file operations
    private final FileTransferService fileTransferService;

    public CommandController(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }

    /**
     * Handles the echo command. It simply returns the received message as an echo response.
     *
     * @param message the message to be echoed
     * @return the echoed message
     */
    @PostMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestBody String message) {
        return ResponseEntity.ok("Echo: " + message);
    }

    /**
     * Handles file transfer requests. Saves the uploaded file to the server.
     *
     * @param file the file to be uploaded
     * @return a response indicating whether the file was uploaded successfully or not
     */
    @PostMapping("/file-transfer")
    public ResponseEntity<String> handleFileTransfer(@RequestParam("file") File file) {
        try {
            fileTransferService.saveFile(file);  // Delegate file saving to FileTransferService
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
    }
}
