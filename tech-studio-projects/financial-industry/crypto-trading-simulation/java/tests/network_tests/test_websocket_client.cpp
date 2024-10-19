#include <gtest/gtest.h>
#include "network/websocket/WebSocketClient.h"

// Mock class for testing purposes
class MockWebSocketClient : public WebSocketClient {
public:
    MockWebSocketClient() : WebSocketClient() {}

    // Override the connect method for testing
    bool connect(const std::string& uri) override {
        return true; // Simulate successful connection
    }

    // Override the sendMessage method for testing
    bool sendMessage(const std::string& message) override {
        return true; // Simulate successful message sending
    }

    // Override the receiveMessage method for testing
    std::string receiveMessage() override {
        return "test"; // Simulate a received message
    }
};

TEST(WebSocketClientTest, ConnectTest) {
    MockWebSocketClient wsClient;
    EXPECT_NO_THROW(wsClient.connect("ws://localhost:8080"));
}

TEST(WebSocketClientTest, SendMessageTest) {
    MockWebSocketClient wsClient;
    wsClient.connect("ws://localhost:8080");
    EXPECT_NO_THROW(wsClient.sendMessage("Hello, WebSocket!"));
}

TEST(WebSocketClientTest, ReceiveMessageTest) {
    MockWebSocketClient wsClient;
    wsClient.connect("ws://localhost:8080");
    std::string message = wsClient.receiveMessage();
    EXPECT_EQ(message, "test"); // Check if the received message is as expected
}
