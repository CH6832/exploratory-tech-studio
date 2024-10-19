#include "MulticastServer.h"
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <iostream>

MulticastServer::MulticastServer(int port) : sock(-1), running(false) {
    sock = socket(AF_INET, SOCK_DGRAM, 0);
    localAddr.sin_family = AF_INET;
    localAddr.sin_addr.s_addr = INADDR_ANY;
    localAddr.sin_port = htons(port);
    
    group.imr_multiaddr.s_addr = inet_addr("239.0.0.1"); // Multicast group
    group.imr_interface.s_addr = htonl(INADDR_ANY);
}

MulticastServer::~MulticastServer() {
    stop();
}

void MulticastServer::start() {
    if (bind(sock, (struct sockaddr*)&localAddr, sizeof(localAddr)) < 0) {
        std::cerr << "Bind failed" << std::endl;
        return;
    }
    setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (void*)&group, sizeof(group));
    
    running = true;
    char buffer[1024] = {0};

    std::cout << "Multicast server is listening..." << std::endl;
    while (running) {
        struct sockaddr_in fromAddr;
        socklen_t fromLen = sizeof(fromAddr);
        int bytesRead = recvfrom(sock, buffer, sizeof(buffer), 0, (struct sockaddr*)&fromAddr, &fromLen);
        if (bytesRead > 0) {
            std::cout << "Received: " << std::string(buffer, bytesRead) << std::endl;
        }
    }
}

void MulticastServer::stop() {
    if (running) {
        running = false;
        close(sock);
    }
}
