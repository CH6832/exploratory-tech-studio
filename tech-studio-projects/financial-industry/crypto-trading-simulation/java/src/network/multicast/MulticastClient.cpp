#include "MulticastClient.h"
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <iostream>

MulticastClient::MulticastClient() : sock(-1) {
}

MulticastClient::~MulticastClient() {
    if (sock != -1) {
        close(sock);
    }
}

bool MulticastClient::joinGroup(const std::string& group, int port) {
    sock = socket(AF_INET, SOCK_DGRAM, 0);
    if (sock < 0) {
        std::cerr << "Socket creation failed" << std::endl;
        return false;
    }

    groupAddr.sin_family = AF_INET;
    groupAddr.sin_port = htons(port);
    inet_pton(AF_INET, group.c_str(), &groupAddr.sin_addr);
    
    if (bind(sock, (struct sockaddr*)&groupAddr, sizeof(groupAddr)) < 0) {
        std::cerr << "Bind failed" << std::endl;
        return false;
    }

    return true;
}

bool MulticastClient::sendMessage(const std::string& message) {
    return sendto(sock, message.c_str(), message.size(), 0, (struct sockaddr*)&groupAddr, sizeof(groupAddr)) != -1;
}
