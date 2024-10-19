#include "TcpClient.h"
#include <iostream>

TcpClient::TcpClient(const std::string& ip, int port) {
    std::cout << "TcpClient created for IP: " << ip << " Port: " << port << std::endl;
}

void TcpClient::connect() {
    std::cout << "Connecting to TCP server..." << std::endl;
    // Implement connection logic
}

void TcpClient::send(const std::string& message) {
    std::cout << "Sending message: " << message << std::endl;
    // Implement sending logic
}

void TcpClient::close() {
    std::cout << "Closing TCP connection..." << std::endl;
    // Implement close logic
}
