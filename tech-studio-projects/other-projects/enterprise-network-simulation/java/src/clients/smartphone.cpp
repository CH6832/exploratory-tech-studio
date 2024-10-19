#include "client_base.h"
#include <iostream>

class SmartphoneClient : public ClientBase {
public:
    SmartphoneClient(const std::string& name) : ClientBase(name) {}

    void connect() override {
        std::cout << name_ << " connected to the VPN." << std::endl;
    }

    void disconnect() override {
        std::cout << name_ << " disconnected from the VPN." << std::endl;
    }
};
