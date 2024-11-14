#include "client_base.h"
#include <iostream>

class NotebookClient : public ClientBase {
public:
    NotebookClient(const std::string& name) : ClientBase(name) {}

    void connect() override {
        std::cout << name_ << " connected to the VPN." << std::endl;
    }

    void disconnect() override {
        std::cout << name_ << " disconnected from the VPN." << std::endl;
    }
};
