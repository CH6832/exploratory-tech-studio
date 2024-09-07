#pragma once

#include <string>
#include <chrono>

enum class LogLevel {
    Debug,
    Information,
    Warning,
    Error
};

// Class representing log information.
class LogInformation {
public:
    // Constructor to initialize the log information.
    LogInformation(LogLevel logLevel, const std::string& module,
        const std::chrono::system_clock::time_point& now,
        int threadId, const std::string& threadName);

    // Getters for the properties.
    LogLevel GetLogLevel() const;
    std::string GetModule() const;
    std::chrono::system_clock::time_point GetNow() const;
    int GetThreadId() const;
    std::string GetThreadName() const;

private:
    LogLevel _logLevel;
    std::string _module;
    std::chrono::system_clock::time_point _now;
    int _threadId;
    std::string _threadName;
};
