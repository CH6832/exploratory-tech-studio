#ifndef MULTICAST_SERVER_H
#define MULTICAST_SERVER_H

#include <string>
#include <sys/socket.h>
#include <netinet/in.h>

class MulticastServer {
public:
    MulticastServer(int port);
    ~MulticastServer();
    void start();
    void stop();
    
private:
    int sock;
    struct sockaddr_in localAddr;
    struct ip_mreq group;
    bool running;
};

#endif // MULTICAST_SERVER_H
