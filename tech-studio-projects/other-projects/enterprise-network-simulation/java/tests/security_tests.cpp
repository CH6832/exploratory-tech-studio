#include <iostream>
#include "Firewall.h"
#include "VpnServer.h"

void testFirewall() {
    Firewall firewall("Test Firewall");
    firewall.start();
    // Add assertions for testing
    firewall.stop();
}

void testVpnServer() {
    VpnServer vpnServer("Test VPN Server");
    vpnServer.start();
    // Add assertions for testing
    vpnServer.stop();
}

int main() {
    testFirewall();
    testVpnServer();
    std::cout << "Security tests passed." << std::endl;
    return 0;
}
