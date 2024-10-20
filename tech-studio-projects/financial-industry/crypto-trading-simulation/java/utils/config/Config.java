import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Config {
    private static String configFilePath = "";
    private static JsonNode configData;

    // Method to load the configuration from a file
    public static void loadConfig(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            configData = objectMapper.readTree(new File(filePath));
            configFilePath = filePath;
        } catch (IOException e) {
            System.err.println("Failed to open config file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to get a value from the configuration
    public static String get(String key) {
        if (configData.has(key)) {
            return configData.get(key).asText();
        } else {
            System.err.println("Key not found in config: " + key);
            return "";
        }
    }

    // Getter for configFilePath (if needed)
    public static String getConfigFilePath() {
        return configFilePath;
    }
}
