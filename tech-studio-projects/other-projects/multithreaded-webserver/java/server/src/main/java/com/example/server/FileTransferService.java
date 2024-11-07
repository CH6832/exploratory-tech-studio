package com.example.server;

import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileTransferService class handles the logic for saving files to the server
 * and responding to echo messages.
 */
@Service
public class FileTransferService {

    private static final Logger logger = LogManager.getLogger(FileTransferService.class);
    private final String fileStorageLocation = "/uploads";

    /**
     * Saves the uploaded file to the server.
     *
     * @param file the file to be saved
     * @throws IOException if an error occurs while saving the file
     */
    public void saveFile(File file) throws IOException {
        // Destination directory where files will be saved
        File destination = new File(fileStorageLocation, file.getName());
        
        logger.info("Attempting to save file: {}", file.getName());
        
        // Check if the source file exists
        if (!file.exists()) {
        	logger.error("File does not exist: {}", file.getName());
            throw new IOException("File does not exist.");
        }
        
        // Create directories if they do not exist
        if (!destination.getParentFile().exists()) {
        	logger.warn("Destination directory does not exist, creating it: {}", destination.getParent());
            destination.getParentFile().mkdirs();
        }

        // Rename the file and move it to the destination directory
        boolean isSaved = file.renameTo(destination);
        if (!isSaved) {
        	logger.error("Failed to save file: {}", file.getName());
            throw new IOException("Failed to save the file.");
        }
        
        logger.info("File saved successfully: {}", file.getName());
    }

    /**
     * Echoes the given message by returning it prefixed with "Echo: ".
     *
     * @param message the message to be echoed
     * @return the echoed message
     */
    public String sendEchoMessage(String message) {
    	logger.debug("Received echo request for message: {}", message);
        // Return the message prefixed with "Echo: "
        return "Echo: " + message;
    }

    /**
     * Saves a file using the given file name and byte data.
     * This method creates a new file with the provided data and saves it
     * in the defined storage location.
     *
     * @param fileName the name of the file
     * @param fileData the byte data of the file
     * @throws IOException if an error occurs while saving the file
     */
    public void saveFile(String fileName, byte[] fileData) throws IOException {
    	logger.info("Attempting to save file: {}", fileName);
    	
    	// Create a new file object based on the file name in the storage location
        File destination = new File(fileStorageLocation, fileName);
        
        // Create directories if they do not exist
        if (!destination.getParentFile().exists()) {
        	logger.warn("Destination directory does not exist, creating it: {}", destination.getParent());
            destination.getParentFile().mkdirs();
        }

        // Write the byte data to the file
        try (FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
            fileOutputStream.write(fileData); // Write file data
            logger.info("File saved successfully: {}", fileName);
        } catch (IOException e) {
        	logger.error("Failed to save file: {}", fileName, e);
            throw new IOException("Failed to save the file.", e);
        }
    }
}
