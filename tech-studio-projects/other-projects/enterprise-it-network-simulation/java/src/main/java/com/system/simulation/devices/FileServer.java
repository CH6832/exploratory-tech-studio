package com.system.simulation.devices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileServer {

    private static final Logger logger = LoggerFactory.getLogger(FileServer.class);

    private String serverName;
    private String fileDirectory;

    public FileServer(String serverName, String fileDirectory) {
        this.serverName = serverName;
        this.fileDirectory = fileDirectory;
    }

    /**
     * Serves a file request by returning the file from the server's directory.
     */
    public File serveFile(String fileName) {
        File file = new File(fileDirectory + "/" + fileName);
        if (file.exists()) {
            logger.info("Serving file: {}", fileName);
            return file;
        } else {
            logger.error("File not found: {}", fileName);
            return null;
        }
    }
}

