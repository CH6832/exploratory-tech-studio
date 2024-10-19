#include "MailServer.h"
#include <iostream>

MailServer::MailServer(const std::string& name) : Device(name) {}

void MailServer::start() {
    std::cout << "Starting mail server: " << name_ << std::endl;
}

void MailServer::stop() {
    std::cout << "Stopping mail server: " << name_ << std::endl;
}
