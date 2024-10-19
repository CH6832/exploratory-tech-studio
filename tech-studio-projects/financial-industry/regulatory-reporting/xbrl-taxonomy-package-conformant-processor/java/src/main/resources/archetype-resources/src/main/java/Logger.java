package src.helpers; // Update the package name as necessary

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.*;

public class LoggerUtil {

    private static final String DEFAULT_LOG_DIRECTORY = "logs"; // Default log directory
    private static final String DEFAULT_LOG_FILENAME = "application.log"; // Default log filename

    public static Logger setupLogger(String name, String logFilePath, Level level) {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(level);

        // Check if a handler already exists to avoid duplicates.
        if (!logger.getHandlers().length > 0) {
            try {
                // Create a rotating file handler
                Handler fileHandler = new RotatingFileHandler(logFilePath, 1_000_000, 5, true);
                fileHandler.setLevel(level);

                // Create a console handler for output to terminal.
                Handler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.SEVERE); // Console only shows errors

                // Define a log format.
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                consoleHandler.setFormatter(formatter);

                // Add the handlers to the logger.
                logger.addHandler(fileHandler);
                logger.addHandler(consoleHandler);
            } catch (IOException e) {
                logger.severe("Failed to set up logging: " + e.getMessage());
            }
        }

        return logger;
    }

    public static String setLoggingDirectory(String logDir, String logFilename) {
        Path path = Paths.get(logDir);
        try {
            // Create the logging directory if it does not exist.
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Failed to create logging directory: " + e.getMessage());
        }
        return path.resolve(logFilename).toString();
    }

    // Example usage
    public static void main(String[] args) {
        String logFilePath = setLoggingDirectory(DEFAULT_LOG_DIRECTORY, DEFAULT_LOG_FILENAME);
        Logger logger = setupLogger(LoggerUtil.class.getName(), logFilePath, Level.INFO);

        logger.info("This is an info message.");
        logger.severe("This is an error message.");
    }
}
