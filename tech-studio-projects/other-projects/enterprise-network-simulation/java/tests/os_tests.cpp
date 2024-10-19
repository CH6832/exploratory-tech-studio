#include <iostream>
#include "FileSystem.h"
#include "ProcessManagement.h"

void testFileSystem() {
    FileSystem fs;
    fs.createFile();
    // Add assertions for testing
    fs.deleteFile();
}

void testProcessManagement() {
    ProcessManagement pm;
    pm.createProcess();
    pm.terminateProcess();
    // Add assertions for testing
}

int main() {
    testFileSystem();
    testProcessManagement();
    std::cout << "OS tests passed." << std::endl;
    return 0;
}
