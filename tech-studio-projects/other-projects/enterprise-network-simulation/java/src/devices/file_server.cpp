#include "FileServer.h"
#include <iostream>

FileServer::FileServer(const std::string& name) : Device(name) {}

void FileServer::start() {
    std::cout << "Starting file server: " << name_ << std::endl;
    accessFile();
}

void FileServer::stop() {
    std::cout << "Stopping file server: " << name_ << std::endl;
}

void FileServer::accessFile() {
    std::cout << "Accessing file..." << std::endl;
}
