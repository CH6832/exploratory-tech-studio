package com.fintech.algotrading.logging;

public abstract class AbstractLogger implements ILogger {

    public void debug(String module, String message) {
        log(LogLevel.DEBUG, module, message);
    }

    public void debug(String module, Exception exception) {
        log(LogLevel.DEBUG, module, exception.getMessage());
    }

    public void information(String module, String message) {
        log(LogLevel.INFORMATION, module, message);
    }

    public void information(String module, Exception exception) {
        log(LogLevel.INFORMATION, module, exception.getMessage());
    }

    public void warning(String module, String message) {
        log(LogLevel.WARNING, module, message);
    }

    public void warning(String module, Exception exception) {
        log(LogLevel.WARNING, module, exception.getMessage());
    }

    public void error(String module, String message) {
        log(LogLevel.ERROR, module, message);
    }

    public void error(String module, Exception exception) {
        log(LogLevel.ERROR, module, exception.getMessage());
    }

    // Abstract method to be implemented by concrete classes
    protected abstract void log(LogLevel level, String module, String message);
}

