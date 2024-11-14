#include "Firewall.h"
#include <iostream>

Firewall::Firewall(const std::string& name) : Device(name) {}

void Firewall::start() {
    std::cout << "Starting firewall: " << name_ << std::endl;
}

void Firewall::stop() {
    std::cout << "Stopping firewall: " << name_ << std::endl;
}
