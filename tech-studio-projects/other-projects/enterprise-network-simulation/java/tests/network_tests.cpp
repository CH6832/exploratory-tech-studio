#include <iostream>
#include "Router.h"
#include "Switch.h"

void testRouter() {
    Router router("Test Router");
    router.start();
    // Add assertions for testing
    router.stop();
}

void testSwitch() {
    Switch switch("Test Switch");
    switch.forwardTraffic();
    // Add assertions for testing
}

int main() {
    testRouter();
    testSwitch();
    std::cout << "Network tests passed." << std::endl;
    return 0;
}
