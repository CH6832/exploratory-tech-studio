#include <gtest/gtest.h>
#include "network/tcp/TcpClient.h"

// Mock class for testing purposes
class MockTcpClient : public TcpClient {
public:
    MockTcpClient() : TcpClient() {}

    // Override the connect method for testing
    bool connect(const std::string& ip, int port) override {
        return true; // Simulate successful connection
    }

    // Override the send method for testing
    bool send(const std::string& message) override {
        return true; // Simulate successful sending
    }

    // Override the receive method for testing
    std::string receive() override {
        return "test"; // Simulate a received message
    }
};

TEST(TcpClientTest, ConnectTest) {
    MockTcpClient tcpClient;
    EXPECT_NO_THROW(tcpClient.connect("127.0.0.1", 8080));
}

TEST(TcpClientTest, SendMessageTest) {
    MockTcpClient tcpClient;
    tcpClient.connect("127.0.0.1", 8080);
    EXPECT_NO_THROW(tcpClient.send("Hello, TCP!"));
}

TEST(TcpClientTest, ReceiveMessageTest) {
    MockTcpClient tcpClient;
    tcpClient.connect("127.0.0.1", 8080);
    std::string message = tcpClient.receive();
    EXPECT_EQ(message, "test"); // Check if the received message is as expected
}
