#include "Router.h"
#include <iostream>

Router::Router(const std::string& name) : Device(name) {}

void Router::start() {
    std::cout << "Starting router: " << name_ << std::endl;
    // Start routing algorithms here
}

void Router::stop() {
    std::cout << "Stopping router: " << name_ << std::endl;
}
