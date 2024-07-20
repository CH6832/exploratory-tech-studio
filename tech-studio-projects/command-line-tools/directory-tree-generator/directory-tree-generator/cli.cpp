#include "cli.h"

void CLI::main(int argc, char* argv[]) {
    CommandLineArguments args = parseCommandLineArguments(argc, argv);
    DirectoryTree tree(args.rootDir);
    tree.generate();
}

CLI::CommandLineArguments CLI::parseCommandLineArguments(int argc, char* argv[]) {
    CommandLineArguments args;
    if (argc > 1) {
        args.rootDir = argv[1];
    }
    else {
        // Default to the current working directory
        args.rootDir = std::filesystem::current_path().string();
    }

    // Check if the root directory exists
    if (!std::filesystem::exists(args.rootDir)) {
        std::cerr << "Error: Root directory not found: " << args.rootDir << std::endl;
        // Handle the error (e.g., exit the program or provide a default directory)
        // For now, let's exit the program
        exit(EXIT_FAILURE);
    }

    return args;
}
