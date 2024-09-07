package com.fintech.algotrading.logging;

public class LogLevelTest{

    private ILogger logger = null;

    public LogLevelTest(ILogger logger) {
        this.logger = logger;
    }

    public void log(LogLevel level, String module, String message) {
        switch (level) {
            case DEBUG:
                logger.debug(module, message);
                break;
            case INFORMATION:
                logger.information(module, message);
                break;
            case WARNING:
                logger.warning(module, message);
                break;
            case ERROR:
                logger.error(module, message);
                break;
            default:
                throw new IllegalArgumentException("Unknown LogLevel: " + level);
        }
    }

    public static void main(String[] args) {
        ILogger consoleLogger = new ConsoleLogger();
        LogLevelTest example = new LogLevelTest(consoleLogger);

        example.log(LogLevel.DEBUG, "MainModule", "This is a debug message.");
        example.log(LogLevel.INFORMATION, "MainModule", "This is an informational message.");
        example.log(LogLevel.WARNING, "MainModule", "This is a warning message.");
        example.log(LogLevel.ERROR, "MainModule", "This is an error message.");
    }
}
