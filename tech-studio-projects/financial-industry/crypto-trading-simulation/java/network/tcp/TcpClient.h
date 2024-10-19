#ifndef TCP_CLIENT_H
#define TCP_CLIENT_H

#include <string>

class TcpClient {
public:
    TcpClient(const std::string& ip, int port);
    void connect();
    void send(const std::string& message);
    void close();
};

#endif // TCP_CLIENT_H
