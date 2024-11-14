#include "DatabaseServer.h"
#include <iostream>

DatabaseServer::DatabaseServer(const std::string& name) : Device(name) {}

void DatabaseServer::start() {
    std::cout << "Starting database server: " << name_ << std::endl;
    queryDatabase();
}

void DatabaseServer::stop() {
    std::cout << "Stopping database server: " << name_ << std::endl;
}

void DatabaseServer::queryDatabase() {
    std::cout << "Querying database..." << std::endl;
}
