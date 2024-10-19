#include "Device.h"
#include <iostream>

class Computer : public Device {
public:
    Computer(const std::string& name) : Device(name) {}

    void start() override {
        std::cout << "Starting computer: " << name_ << std::endl;
    }

    void stop() override {
        std::cout << "Stopping computer: " << name_ << std::endl;
    }
};
