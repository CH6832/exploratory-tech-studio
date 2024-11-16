package com.system.simulation.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartphoneClient extends ClientBase {

    private static final Logger logger = LoggerFactory.getLogger(SmartphoneClient.class);

    public SmartphoneClient(String serverIp, int serverPort) {
        super("SmartphoneClient", serverIp, serverPort);
    }

    /**
     * Connects the smartphone client to the VPN server with additional configuration.
     */
    @Override
    public void connect() {
        logger.info("Initializing connection for SmartphoneClient...");
        super.connect();

        // Add smartphone-specific initialization here
        logger.info("SmartphoneClient connection initialized successfully.");
    }

    /**
     * Sends specific data from the smartphone client to the server.
     */
    @Override
    public void sendData(String data) {
        logger.info("SmartphoneClient sending data...");
        super.sendData(data);
    }

    /**
     * Disconnects the smartphone client from the server.
     */
    @Override
    public void disconnect() {
        logger.info("SmartphoneClient disconnecting...");
        super.disconnect();
    }
}

