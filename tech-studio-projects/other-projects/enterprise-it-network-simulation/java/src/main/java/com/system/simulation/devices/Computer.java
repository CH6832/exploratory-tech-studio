package com.system.simulation.devices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class Computer {

    private static final Logger logger = LoggerFactory.getLogger(Computer.class);

    private String name;
    private Socket socket;
    private String ipAddress;

    // Constructor
    public Computer(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

    /**
     * Connects the computer to the specified network (represented by a socket connection).
     */
    public void connect(String serverIp, int port) {
        try {
            logger.info("{} attempting to connect to server {}:{}", name, serverIp, port);
            socket = new Socket(serverIp, port);
            logger.info("{} successfully connected to server.", name);
        } catch (IOException e) {
            logger.error("Connection error for {}: {}", name, e.getMessage());
        }
    }

    /**
     * Simulates sending data from the computer to the network.
     */
    public void sendData(String data) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.getOutputStream().write(data.getBytes());
                socket.getOutputStream().flush();
                logger.info("{} sent data: {}", name, data);
            } else {
                logger.warn("{} is not connected. Data not sent.", name);
            }
        } catch (IOException e) {
            logger.error("Error sending data from {}: {}", name, e.getMessage());
        }
    }

    /**
     * Disconnects the computer from the network.
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

