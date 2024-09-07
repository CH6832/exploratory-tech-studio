package com.fintech.algotrading.logging;

import java.time.Instant;

public class LogInformation {

    private final LogLevel logLevel;
    private final String module;
    private final Instant now;
    private final int threadId;
    private final String threadName;

    // Constructor to initialize the log information.
    public LogInformation(LogLevel logLevel, String module, Instant now, int threadId, String threadName) {
        this.logLevel = logLevel;
        this.module = module;
        this.now = now;
        this.threadId = threadId;
        this.threadName = threadName;
    }

    // Getter for the log level.
    public LogLevel getLogLevel() {
        return logLevel;
    }

    // Getter for the module name.
    public String getModule() {
        return module;
    }

    // Getter for the time point.
    public Instant getNow() {
        return now;
    }

    // Getter for the thread ID.
    public int getThreadId() {
        return threadId;
    }

    // Getter for the thread name.
    public String getThreadName() {
        return threadName;
    }
}