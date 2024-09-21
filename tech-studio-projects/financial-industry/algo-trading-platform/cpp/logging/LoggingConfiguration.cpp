#include "LoggingConfiguration.h"

// Getter for TextLoggerConfiguration
std::shared_ptr<TextLoggerConfiguration> LoggingConfiguration::GetTextLoggerConfiguration() const {
    return textLoggerConfiguration;
}

// Setter for TextLoggerConfiguration
void LoggingConfiguration::SetTextLoggerConfiguration(const std::shared_ptr<TextLoggerConfiguration>& config) {
    textLoggerConfiguration = config;
}
