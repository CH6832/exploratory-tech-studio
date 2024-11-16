package com.system.simulation.devices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class Printer {

    private static final Logger logger = LoggerFactory.getLogger(Printer.class);

    private String name;
    private Socket socket;
    private String ipAddress;

    // Constructor
    public Printer(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

    /**
     * Connects the printer to the network.
     */
    public void connect(String serverIp, int port) {
        try {
            logger.info("{} attempting to connect to server {}:{}", name, serverIp, port);
            socket = new Socket(serverIp, port);
            logger.info("{} connected to server successfully.", name);
        } catch (IOException e) {
            logger.error("Connection error for {}: {}", name, e.getMessage());
        }
    }

    /**
     * Simulates receiving a print job.
     */
    public void receivePrintJob(String printData) {
        try {
            if (socket != null && !socket.isClosed()) {
                // Here we would simulate print processing
                logger.info("{} received print job: {}", name, printData);
            } else {
                logger.warn("{} is not connected. Unable to print.", name);
            }
        } catch (Exception e) {
            logger.error("Error receiving print job for {}: {}", name, e.getMessage());
        }
    }

    /**
     * Disconnects the printer from the network.
     */
    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                logger.info("{} disconnected successfully.", name);
            }
        } catch (IOException e) {
            logger.error("Error disconnecting {}: {}", name, e.getMessage());
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }
}

