#pragma once

#include <memory>
#include <string>
#include "TextLoggerConfiguration.h"

// Class to hold logging configuration details.
class LoggingConfiguration {
public:
    // Constructor
    LoggingConfiguration() = default;

    // Getter and setter for the TextLoggerConfiguration
    std::shared_ptr<TextLoggerConfiguration> GetTextLoggerConfiguration() const;
    void SetTextLoggerConfiguration(const std::shared_ptr<TextLoggerConfiguration>& config);

private:
    std::shared_ptr<TextLoggerConfiguration> textLoggerConfiguration;
};
