#include "LogInformation.h"

// Constructor to initialize the log information.
LogInformation::LogInformation(LogLevel logLevel, const std::string& module,
    const std::chrono::system_clock::time_point& now,
    int threadId, const std::string& threadName)
    : _logLevel(logLevel), _module(module), _now(now), _threadId(threadId), _threadName(threadName) {}

// Getter for the log level.
LogLevel LogInformation::GetLogLevel() const {
    return _logLevel;
}

// Getter for the module name.
std::string LogInformation::GetModule() const {
    return _module;
}

// Getter for the time point.
std::chrono::system_clock::time_point LogInformation::GetNow() const {
    return _now;
}

// Getter for the thread ID.
int LogInformation::GetThreadId() const {
    return _threadId;
}

// Getter for the thread name.
std::string LogInformation::GetThreadName() const {
    return _threadName;
}
