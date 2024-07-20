#ifndef CLI_HPP
#define CLI_HPP

#include <iostream>
#include <string>
#include <filesystem>
#include "dirtree.h"

class CLI {
public:
    static void main(int argc, char* argv[]);

private:
    struct CommandLineArguments {
        std::string rootDir = "./test_folder";
        // std::string rootDir = std::filesystem::current_path().string();
    };

    static CommandLineArguments parseCommandLineArguments(int argc, char* argv[]);
};

#endif // CLI_HPP
