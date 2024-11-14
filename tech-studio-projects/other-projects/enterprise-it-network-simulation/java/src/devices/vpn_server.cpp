#include "VpnServer.h"
#include <iostream>

VpnServer::VpnServer(const std::string& name) : Device(name) {}

void VpnServer::start() {
    std::cout << "Starting VPN server: " << name_ << std::endl;
}

void VpnServer::stop() {
    std::cout << "Stopping VPN server: " << name_ << std::endl;
}
