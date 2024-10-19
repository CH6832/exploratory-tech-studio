#include "P2PClient.h"
#include <cstring>
#include <unistd.h>
#include <iostream>

P2PClient::P2PClient() : sock(-1) {
}

P2PClient::~P2PClient() {
    if (sock != -1) {
        close(sock);
    }
}

bool P2PClient::connect(const std::string& ip, int port) {
    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        std::cerr << "Socket creation failed" << std::endl;
        return false;
    }
    
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(port);
    inet_pton(AF_INET, ip.c_str(), &serverAddr.sin_addr);
    
    if (::connect(sock, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0) {
        std::cerr << "Connection failed" << std::endl;
        return false;
    }
    
    return true;
}

bool P2PClient::sendMessage(const std::string& message) {
    return send(sock, message.c_str(), message.size(), 0) != -1;
}

std::string P2PClient::receiveMessage() {
    char buffer[1024] = {0};
    int bytesRead = recv(sock, buffer, sizeof(buffer), 0);
    return bytesRead > 0 ? std::string(buffer, bytesRead) : "";
}
