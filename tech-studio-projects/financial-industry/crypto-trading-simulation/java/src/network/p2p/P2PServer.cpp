#include "P2PServer.h"
#include <unistd.h>
#include <iostream>

P2PServer::P2PServer(int port) : serverSock(-1), clientSock(-1), running(false) {
    serverSock = socket(AF_INET, SOCK_STREAM, 0);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(port);
}

P2PServer::~P2PServer() {
    stop();
}

void P2PServer::start() {
    if (bind(serverSock, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0) {
        std::cerr << "Bind failed" << std::endl;
        return;
    }
    listen(serverSock, 3);
    running = true;

    std::cout << "Server is listening..." << std::endl;
    while (running) {
        socklen_t addrLen = sizeof(serverAddr);
        clientSock = accept(serverSock, (struct sockaddr*)&serverAddr, &addrLen);
        if (clientSock >= 0) {
            char buffer[1024] = {0};
            int bytesRead = recv(clientSock, buffer, sizeof(buffer), 0);
            if (bytesRead > 0) {
                std::cout << "Received: " << std::string(buffer, bytesRead) << std::endl;
            }
            close(clientSock);
        }
    }
}

void P2PServer::stop() {
    if (running) {
        running = false;
        close(serverSock);
    }
}
