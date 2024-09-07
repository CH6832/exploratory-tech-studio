#include <iostream>
#include "PackagesManager.h"

int main(int argc, char* argv[]) {
    PkgManager pkgManager;

    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <command> [package_id]" << std::endl;
        std::cerr << "Commands: install, uninstall, reset" << std::endl;
        return 1;
    }

    std::string command = argv[1];
    if (command == "install") {
        if (argc < 3) {
            std::cerr << "Usage: " << argv[0] << " install <package_id>" << std::endl;
            return 1;
        }
        std::string pkgId = argv[2];
        pkgManager.install(pkgId);
    }
    else if (command == "uninstall") {
        if (argc < 3) {
            std::cerr << "Usage: " << argv[0] << " uninstall <package_id>" << std::endl;
            return 1;
        }
        std::string pkgId = argv[2];
        pkgManager.uninstall(pkgId);
    }
    else if (command == "reset") {
        pkgManager.reset();
    }
    else {
        std::cerr << "Unknown command: " << command << std::endl;
        std::cerr << "Commands: install, uninstall, reset" << std::endl;
        return 1;
    }

    return 0;
}
