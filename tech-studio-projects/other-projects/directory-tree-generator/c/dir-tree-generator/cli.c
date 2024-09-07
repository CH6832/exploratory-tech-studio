#include "cli.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h> // for checking directory existence

#ifdef _WIN32
#define IS_DIR(mode) ((mode) & _S_IFDIR)
#else
#define IS_DIR(mode) S_ISDIR(mode)
#endif


// Main function equivalent
void CLI_main(int argc, char* argv[]) {
    CommandLineArguments args = parseCommandLineArguments(argc, argv);
    generateDirectoryTree(args.rootDir);
}

// Function to parse command line arguments
CommandLineArguments parseCommandLineArguments(int argc, char* argv[]) {
    CommandLineArguments args;

    if (argc > 1) {
        // Use the provided directory
        strncpy(args.rootDir, argv[1], sizeof(args.rootDir));
    }
    else {
        // Default to the current working directory
        if (getcwd(args.rootDir, sizeof(args.rootDir)) == NULL) {
            perror("getcwd() error");
            exit(EXIT_FAILURE);
        }
        strcat(args.rootDir, "/test_folder"); // Adding test_folder
    }

    // Check if the root directory exists
    struct stat st;
    if (stat(args.rootDir, &st) != 0 || !IS_DIR(st.st_mode)) {
        fprintf(stderr, "Error: Root directory not found: %s\n", args.rootDir);
        exit(EXIT_FAILURE);
    }

    return args;
}

// Placeholder function to generate directory tree (to be implemented)
void generateDirectoryTree(const char* rootDir) {
    // Implementation of tree generation would go here
    printf("Generating directory tree for: %s\n", rootDir);
}
