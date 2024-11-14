#include "WebServer.h"
#include <iostream>

WebServer::WebServer(const std::string& name) : Device(name) {}

void WebServer::start() {
    std::cout << "Starting web server: " << name_ << std::endl;
}

void WebServer::stop() {
    std::cout << "Stopping web server: " << name_ << std::endl;
}
