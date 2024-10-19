#ifndef P2P_CLIENT_H
#define P2P_CLIENT_H

#include <string>
#include <sys/socket.h>
#include <arpa/inet.h>

class P2PClient {
public:
    P2PClient();
    ~P2PClient();
    bool connect(const std::string& ip, int port);
    bool sendMessage(const std::string& message);
    std::string receiveMessage();

private:
    int sock;
    struct sockaddr_in serverAddr;
};

#endif // P2P_CLIENT_H
