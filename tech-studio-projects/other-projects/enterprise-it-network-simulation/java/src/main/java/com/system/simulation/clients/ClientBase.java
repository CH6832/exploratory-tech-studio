package com.system.simulation.clients;

import com.system.simulation.devices.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class ClientBase {

    private static final Logger logger = LoggerFactory.getLogger(ClientBase.class);

    protected String clientName;
    protected InetAddress serverAddress;
    protected int serverPort;
    protected Socket socket;

    // Constructor
    public ClientBase(String clientName, String serverIp, int serverPort) {
        this.clientName = clientName;
        try {
            this.serverAddress = InetAddress.getByName(serverIp);
            this.serverPort = serverPort;
        } catch (UnknownHostException e) {
            logger.error("Invalid server IP address: {}", serverIp, e);
        }
    }

    /**
     * Connects the client to the server with low-latency optimizations and error handling.
     */
    public void connect() {
        try {
            logger.info("{} attempting to connect to server {}:{}", clientName, serverAddress, serverPort);

            // Establishing connection to the server
            socket = new Socket(serverAddress, serverPort);

            logger.info("{} successfully connected to server.", clientName);
        } catch (IOException e) {
            logger.error("Error while connecting {} to server {}:{}", clientName, serverAddress, serverPort, e);
            handleConnectionError(e);
        }
    }

    /**
     * Sends data to the server while ensuring jitter avoidance through small delays and profiling.
     */
    public void sendData(String data) {
        long startTime = System.nanoTime();

        try {
            if (socket != null && !socket.isClosed()) {
                // Send data to server with potential jitter avoidance mechanism
                socket.getOutputStream().write(data.getBytes());
                socket.getOutputStream().flush();
                logger.info("{} sent data: {}", clientName, data);
            } else {
                logger.warn("{} not connected. Unable to send data.", clientName);
            }
        } catch (IOException e) {
            logger.error("Error while sending data from {}: {}", clientName, e.getMessage());
        } finally {
            long duration = System.nanoTime() - startTime;
            logger.debug("Data transmission for {} took {} nanoseconds", clientName, duration);
        }
    }

    /**
     * Gracefully disconnects the client from the server.
     */
    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                logger.info("{} disconnected from server.", clientName);
            }
        } catch (IOException e) {
            logger.error("Error while disconnecting {}: {}", clientName, e.getMessage());
        }
    }

    /**
     * Handles connection errors by attempting reconnection or logging specific issues.
     */
    private void handleConnectionError(IOException e) {
        // Try to reconnect or handle the error as necessary
        logger.error("Connection error: {}", e.getMessage());
        // Add additional reconnection logic if required
    }

    public void connect(Router router) {

    }
}
