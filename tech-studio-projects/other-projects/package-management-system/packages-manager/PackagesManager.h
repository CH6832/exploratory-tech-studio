#ifndef PKG_MANAGER_H
#define PKG_MANAGER_H

#include <string>
#include <unordered_map>
#include <filesystem>
#include <fstream>
#include <iostream>
#include <json/json.h>

class PkgManager {
public:
    using PackageInfo = std::unordered_map<std::string, std::string>;
    using Packages = std::unordered_map<std::string, PackageInfo>;

    PkgManager();
    ~PkgManager();

    void loadPackages();
    void savePackages();
    void install(const std::string& pkgId);
    void uninstall(const std::string& pkgId);
    void reset();

private:
    Packages packages;
    const std::string PKG_JSON_PATH = "storage/pkgs.json";
    const std::string STORAGE_FOLDER = "storage";
    const std::string INSTALLED_FOLDER = "pkgs_installed";
    const std::string LOG_FILE = "packages_manager/pkg_manager.log";

    void log(const std::string& message, const std::string& level = "INFO");
    void createFolder(const std::string& folder);
};

#endif // PKG_MANAGER_H
