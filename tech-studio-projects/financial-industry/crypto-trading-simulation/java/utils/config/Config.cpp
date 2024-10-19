#include "Config.h"
#include <nlohmann/json.hpp>
#include <fstream>
#include <iostream>

std::string Config::configFilePath = "";
nlohmann::json configData;

void Config::loadConfig(const std::string& filePath) {
    std::ifstream file(filePath);
    if (!file.is_open()) {
        std::cerr << "Failed to open config file: " << filePath << std::endl;
        return;
    }

    file >> configData;
    file.close();
}

std::string Config::get(const std::string& key) {
    if (configData.contains(key)) {
        return configData[key];
    } else {
        std::cerr << "Key not found in config: " << key << std::endl;
        return "";
    }
}
