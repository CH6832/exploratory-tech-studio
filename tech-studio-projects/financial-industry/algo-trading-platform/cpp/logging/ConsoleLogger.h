#pragma once

#ifndef CONSOLE_LOGGER_H
#define CONSOLE_LOGGER_H

#include "ILogger.h"
#include <iostream>

class ConsoleLogger : public ILogger {
public:
    void LogInformation(const std::string& message) override {
        std::cout << "[INFO] " << message << std::endl;
    }

    void LogWarning(const std::string& message) override {
        std::cout << "[WARNING] " << message << std::endl;
    }

    void LogError(const std::string& message) override {
        std::cout << "[ERROR] " << message << std::endl;
    }

    void LogCritical(const std::string& message) override {
        std::cout << "[CRITICAL] " << message << std::endl;
    }
};

#endif // CONSOLE_LOGGER_H
