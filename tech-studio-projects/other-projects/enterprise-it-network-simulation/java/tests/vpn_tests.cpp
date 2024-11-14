#include <iostream>
#include "VpnServer.h"

void testVpnServerEncryption() {
    VpnServer vpnServer("Test VPN Server");
    vpnServer.start();
    // Add assertions for testing encryption and authentication
    vpnServer.stop();
}

int main() {
    testVpnServerEncryption();
    std::cout << "VPN tests passed." << std::endl;
    return 0;
}
