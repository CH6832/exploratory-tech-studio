#include "raft.h"
#include <iostream>
#include <vector>
#include <thread>
#include <chrono>

int main() {
    // Number of servers in the Raft cluster
    const int totalServers = 5;
    
    // Create and start all Raft servers
    std::vector<std::shared_ptr<RaftServer>> servers;
    for (int i = 0; i < totalServers; ++i) {
        servers.push_back(std::make_shared<RaftServer>(i, totalServers));
    }

    // Start all servers
    for (auto& server : servers) {
        server->start();
    }

    // Run for a while to let the servers interact
    std::this_thread::sleep_for(std::chrono::seconds(10));

    // Stop all servers
    for (auto& server : servers) {
        server->stop();
    }

    return 0;
}
