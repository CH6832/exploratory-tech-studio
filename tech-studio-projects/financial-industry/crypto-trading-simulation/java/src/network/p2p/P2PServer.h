#ifndef P2P_SERVER_H
#define P2P_SERVER_H

#include <string>
#include <sys/socket.h>
#include <netinet/in.h>

class P2PServer {
public:
    P2PServer(int port);
    ~P2PServer();
    void start();
    void stop();
    
private:
    int serverSock;
    struct sockaddr_in serverAddr;
    int clientSock;
    bool running;
};

#endif // P2P_SERVER_H
