#include "PackagesManager.h"
#include <filesystem>
#include <fstream>
#include <iostream>
#include <json/json.h>

namespace fs = std::filesystem;

PkgManager::PkgManager() {
    loadPackages();
}

PkgManager::~PkgManager() {
    savePackages();
}

void PkgManager::loadPackages() {
    std::ifstream file(PKG_JSON_PATH);
    if (file.is_open()) {
        Json::Value root;
        file >> root;
        for (const auto& pkg : root.getMemberNames()) {
            PackageInfo info;
            for (const auto& key : root[pkg].getMemberNames()) {
                info[key] = root[pkg][key].asString();
            }
            packages[pkg] = info;
        }
        file.close();
    }
}

void PkgManager::savePackages() {
    Json::Value root;
    for (const auto& pkg : packages) {
        for (const auto& kv : pkg.second) {
            root[pkg.first][kv.first] = kv.second;
        }
    }
    std::ofstream file(PKG_JSON_PATH);
    if (file.is_open()) {
        file << root;
        file.close();
    }
}

void PkgManager::install(const std::string& pkgId) {
    if (packages.find(pkgId) == packages.end()) {
        log("Package " + pkgId + " not found.", "ERROR");
        std::cerr << "Package ID not found." << std::endl;
        return;
    }

    PackageInfo pkgInfo = packages[pkgId];
    std::string pkgUrl = pkgInfo["_pkgurl"];

    if (!fs::exists(STORAGE_FOLDER)) {
        fs::create_directories(STORAGE_FOLDER);
        log("Folder '" + STORAGE_FOLDER + "' created successfully.");
        std::cout << "Folder '" + STORAGE_FOLDER + "' created successfully." << std::endl;
    }
    else {
        log("Folder '" + STORAGE_FOLDER + "' already exists.");
        std::cout << "Folder '" + STORAGE_FOLDER + "' already exists." << std::endl;
    }

    if (!fs::exists(INSTALLED_FOLDER)) {
        fs::create_directories(INSTALLED_FOLDER);
        log("Folder '" + INSTALLED_FOLDER + "' created successfully.");
        std::cout << "Folder '" + INSTALLED_FOLDER + "' created successfully." << std::endl;
    }
    else {
        log("Folder '" + INSTALLED_FOLDER + "' already exists.");
        std::cout << "Folder '" + INSTALLED_FOLDER + "' already exists." << std::endl;
    }

    std::string pkgPath = STORAGE_FOLDER + "/" + pkgUrl;
    if (!fs::exists(pkgPath)) {
        log("Package file not found.");
        std::cerr << "Package file not found." << std::endl;
        return;
    }

    std::string destPath = INSTALLED_FOLDER + "/" + pkgUrl;
    if (fs::exists(destPath)) {
        log("Package " + pkgId + " is already installed.");
        std::cout << "Package " + pkgId + " is already installed." << std::endl;
        return;
    }

    try {
        fs::copy(pkgPath, destPath);
        log("Package " + pkgId + " installed successfully.");
        std::cout << "Package " + pkgId + " installed successfully." << std::endl;
    }
    catch (const std::exception& e) {
        log(std::string("Failed to install package ") + pkgId + ": " + e.what(), "ERROR");
        std::cerr << "Failed to install package " + pkgId + ": " << e.what() << std::endl;
    }
}

void PkgManager::uninstall(const std::string& pkgId) {
    std::string installedPath = INSTALLED_FOLDER + "/" + pkgId + ".zip";
    if (fs::exists(installedPath)) {
        try {
            fs::remove(installedPath);
            log("Package " + pkgId + " uninstalled successfully.");
            std::cout << "Package " + pkgId + " uninstalled successfully." << std::endl;
        }
        catch (const std::exception& e) {
            log(std::string("Failed to uninstall package ") + pkgId + ": " + e.what(), "ERROR");
            std::cerr << "Failed to uninstall package " + pkgId + ": " << e.what() << std::endl;
        }
    }
    else {
        log("Package is not installed.", "ERROR");
        std::cerr << "Package is not installed." << std::endl;
    }
}

void PkgManager::reset() {
    if (fs::exists(INSTALLED_FOLDER)) {
        try {
            fs::remove_all(INSTALLED_FOLDER);
            log("All packages uninstalled.");
            std::cout << "All packages uninstalled." << std::endl;
        }
        catch (const std::exception& e) {
            log(std::string("Failed to reset package storage: ") + e.what(), "ERROR");
            std::cerr << "Failed to reset package storage: " << e.what() << std::endl;
        }
    }
    else {
        log("No packages installed.", "ERROR");
        std::cerr << "No packages installed." << std::endl;
    }
}

void PkgManager::log(const std::string& message, const std::string& level) {
    std::ofstream logFile(LOG_FILE, std::ios_base::app);
    if (logFile.is_open()) {
        logFile << level << ": " << message << std::endl;
        logFile.close();
    }
}
