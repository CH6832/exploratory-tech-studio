#ifndef TCPCLIENT_H
#define TCPCLIENT_H

#include <string>

class TcpClient {
public:
    TcpClient(const std::string& host, int port);
    void connect();
    void send(const std::string& message);
    std::string receive();

private:
    std::string host;
    int port;
};

#endif // TCPCLIENT_H
