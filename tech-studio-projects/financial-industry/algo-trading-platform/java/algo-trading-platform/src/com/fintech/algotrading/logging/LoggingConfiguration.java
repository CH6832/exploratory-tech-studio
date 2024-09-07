package com.fintech.algotrading.logging;

public class LoggingConfiguration {

    private TextLogger textLoggerConfiguration;

    // Getter for TextLoggerConfiguration
    public TextLogger getTextLoggerConfiguration() {
        return textLoggerConfiguration;
    }

    // Setter for TextLoggerConfiguration
    public void setTextLoggerConfiguration(TextLogger config) {
        this.textLoggerConfiguration = config;
    }
}