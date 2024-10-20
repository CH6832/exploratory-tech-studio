import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // Enum for log levels
    public enum LogLevel {
        INFO,
        WARN,
        ERROR
    }

    // Method to log messages
    public static void log(LogLevel level, String message) {
        String timestamp = getCurrentTimestamp();
        System.out.println(String.format("%s [%s]: %s", timestamp, level, message));
    }

    // Method to log info messages
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    // Method to log warning messages
    public static void warn(String message) {
        log(LogLevel.WARN, message);
    }

    // Method to log error messages
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    // Helper method to get the current timestamp
    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
