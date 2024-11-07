package com.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FileTransferClient is responsible for communicating with the server,
 * including sending echo messages and file transfers.
 */
@Component
public class FileTransferClient {

    private final RestTemplate restTemplate;

    @Value("${server.url}") // Base URL for the server (configured in application.properties)
    private String serverUrl;

    public FileTransferClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sends a message to the server to be echoed back.
     *
     * @param message The message to be sent to the server.
     * @return The server's response.
     */
    public String sendEchoMessage(String message) {
        String url = serverUrl + "/api/echo";
        return restTemplate.postForObject(url, message, String.class);
    }

    /**
     * Sends a file to the server.
     *
     * @param file The file to be sent to the server.
     * @throws IOException If an error occurs while reading or sending the file.
     */
    public void sendFile(File file) throws IOException {
        String url = serverUrl + "/api/file-transfer";
        
        // Prepare the file as a byte array
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileBytes);
        }
        
        // Make the HTTP POST request to the server to send the file
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(fileBytes, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        
        System.out.println("Server Response: " + responseEntity.getBody());
    }
}
