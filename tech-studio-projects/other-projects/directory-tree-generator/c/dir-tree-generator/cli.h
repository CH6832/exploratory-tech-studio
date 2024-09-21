#ifndef CLI_H
#define CLI_H

// Struct to hold command line arguments
typedef struct {
    char rootDir[1024];
} CommandLineArguments;

// Function prototypes
CommandLineArguments parseCommandLineArguments(int argc, char* argv[]);
void generateDirectoryTree(const char* rootDir);

#endif // CLI_H
