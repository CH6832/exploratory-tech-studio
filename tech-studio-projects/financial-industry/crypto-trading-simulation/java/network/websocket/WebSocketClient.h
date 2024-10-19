#ifndef WEBSOCKET_CLIENT_H
#define WEBSOCKET_CLIENT_H

#include <string>

class WebSocketClient {
public:
    WebSocketClient(const std::string& url);
    void connect();
    void send(const std::string& message);
    void close();
};

#endif // WEBSOCKET_CLIENT_H
