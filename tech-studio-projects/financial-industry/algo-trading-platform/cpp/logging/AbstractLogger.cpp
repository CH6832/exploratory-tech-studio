#include "AbstractLogger.h"

// Default implementation of logging methods in the base class.
void ILogger::Debug(const std::string& module, const std::string& message) {
    Log(LogLevel::Debug, module, message);
}

void ILogger::Debug(const std::string& module, const std::exception& exception) {
    Log(LogLevel::Debug, module, std::string{ exception.what() });
}

void ILogger::Information(const std::string& module, const std::string& message) {
    Log(LogLevel::Information, module, message);
}

void ILogger::Information(const std::string& module, const std::exception& exception) {
    Log(LogLevel::Information, module, std::string{ exception.what() });
}

void ILogger::Warning(const std::string& module, const std::string& message) {
    Log(LogLevel::Warning, module, message);
}

void ILogger::Warning(const std::string& module, const std::exception& exception) {
    Log(LogLevel::Warning, module, std::string{ exception.what() });
}

void ILogger::Error(const std::string& module, const std::string& message) {
    Log(LogLevel::Error, module, message);
}

void ILogger::Error(const std::string& module, const std::exception& exception) {
    Log(LogLevel::Error, module, std::string{ exception.what() });
}
