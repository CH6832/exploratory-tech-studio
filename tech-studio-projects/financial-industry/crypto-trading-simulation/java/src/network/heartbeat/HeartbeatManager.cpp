#include "HeartbeatManager.h"
#include <iostream>
#include <chrono>

HeartbeatManager::HeartbeatManager() : running(false) {}

void HeartbeatManager::start() {
    running = true;
    std::thread(&HeartbeatManager::sendHeartbeats, this).detach();
}

void HeartbeatManager::stop() {
    running = false;
}

void HeartbeatManager::sendHeartbeats() {
    while (running) {
        std::this_thread::sleep_for(std::chrono::seconds(5));
        std::cout << "Sending heartbeat..." << std::endl; // Placeholder for actual heartbeat logic
    }
}
