#pragma once

#include "ILogger.h"

// Interface for text-based logging that also requires resource cleanup.
class ITextLogger : public ILogger {
public:
    // Virtual destructor to ensure proper cleanup of derived classes.
    virtual ~ITextLogger() = default;

    // Optionally, you can define additional pure virtual methods specific to text logging.
    // Example: virtual void SetLogFile(const std::string& filePath) = 0;
};
