package com.system.simulation.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotebookClient extends ClientBase {

    private static final Logger logger = LoggerFactory.getLogger(NotebookClient.class);

    private static final int DEFAULT_SERVER_PORT = 8080;  // Replace with the appropriate port if known

    /**
     * Constructor for NotebookClient
     *
     * @param serverIp the IP address of the server
     * @param serverPort the port number of the server
     */
    public NotebookClient(String serverIp, int serverPort) {
        super("NotebookClient", serverIp, serverPort);
    }

    /**
     * Constructor for NotebookClient with a default server port.
     *
     * @param serverIp the IP address of the server
     */
    public NotebookClient(String serverIp) {
        this(serverIp, DEFAULT_SERVER_PORT);  // Calls the main constructor with a default port
    }

    // Additional methods as needed

    /**
     * Connects the notebook client to the VPN server with additional configuration.
     */
    @Override
    public void connect() {
        logger.info("Initializing connection for NotebookClient...");
        super.connect();

        // Add any notebook-specific initialization here
        logger.info("NotebookClient connection initialized successfully.");
    }

    /**
     * Sends specific data from the notebook client to the server.
     */
    @Override
    public void sendData(String data) {
        logger.info("NotebookClient sending data...");
        super.sendData(data);
    }

    /**
     * Disconnects the notebook client from the server.
     */
    @Override
    public void disconnect() {
        logger.info("NotebookClient disconnecting...");
        super.disconnect();
    }
}

