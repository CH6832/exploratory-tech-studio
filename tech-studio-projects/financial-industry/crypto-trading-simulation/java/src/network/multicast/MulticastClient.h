#ifndef MULTICAST_CLIENT_H
#define MULTICAST_CLIENT_H

#include <string>
#include <sys/socket.h>
#include <netinet/in.h>

class MulticastClient {
public:
    MulticastClient();
    ~MulticastClient();
    bool joinGroup(const std::string& group, int port);
    bool sendMessage(const std::string& message);
    
private:
    int sock;
    struct sockaddr_in groupAddr;
};

#endif // MULTICAST_CLIENT_H
