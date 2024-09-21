#ifndef ABSTRACT_LOGGER_H
#define ABSTRACT_LOGGER_H

#include <string>
#include <memory>
#include <iostream>

// Enum for log levels.
enum class LogLevel {
    Debug,
    Information,
    Warning,
    Error
};

// Abstract base class for logging.
class ILogger {
public:
    virtual ~ILogger() = default;

    virtual void Log(LogLevel level, const std::string& module, const std::string& message) = 0;
    virtual void Log(LogLevel level, const std::string& module, const std::exception& exception) = 0;

    // Convenience methods for logging messages at different levels.
    void Debug(const std::string& module, const std::string& message);
    void Debug(const std::string& module, const std::exception& exception);

    void Information(const std::string& module, const std::string& message);
    void Information(const std::string& module, const std::exception& exception);

    void Warning(const std::string& module, const std::string& message);
    void Warning(const std::string& module, const std::exception& exception);

    void Error(const std::string& module, const std::string& message);
    void Error(const std::string& module, const std::exception& exception);
};

#endif // ABSTRACT_LOGGER_H
