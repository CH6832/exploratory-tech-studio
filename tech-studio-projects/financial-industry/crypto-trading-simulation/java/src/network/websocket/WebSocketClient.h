#ifndef WEBSOCKETCLIENT_H
#define WEBSOCKETCLIENT_H

#include <string>

class WebSocketClient {
public:
    WebSocketClient(const std::string& uri);
    void connect();
    void send(const std::string& message);
    void receive();

private:
    std::string uri;
    // Socket-related members
};

#endif // WEBSOCKETCLIENT_H
