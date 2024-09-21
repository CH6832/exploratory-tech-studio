#pragma once

#include <string>

// Enumeration for different log levels.
enum class LogLevel {
    Debug,
    Information,
    Warning,
    Error
};

// Interface for a logger.
class ILogger {
public:
    virtual ~ILogger() = default;

    virtual void Debug(const std::string& module, const std::string& message) = 0;
    virtual void Debug(const std::string& module, const std::exception& exception) = 0;

    virtual void Information(const std::string& module, const std::string& message) = 0;
    virtual void Information(const std::string& module, const std::exception& exception) = 0;

    virtual void Warning(const std::string& module, const std::string& message) = 0;
    virtual void Warning(const std::string& module, const std::exception& exception) = 0;

    virtual void Error(const std::string& module, const std::string& message) = 0;
    virtual void Error(const std::string& module, const std::exception& exception) = 0;
};
