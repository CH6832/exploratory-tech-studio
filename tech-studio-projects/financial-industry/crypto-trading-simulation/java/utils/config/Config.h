#pragma once
#include <string>

class Config {
public:
    static void loadConfig(const std::string& filePath); // Load the config file
    static std::string get(const std::string& key);      // Get value by key
private:
    static std::string configFilePath;                   // File path for the config
};
