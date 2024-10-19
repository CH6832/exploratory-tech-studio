#include "Device.h"
#include <iostream>

class Printer : public Device {
public:
    Printer(const std::string& name) : Device(name) {}

    void start() override {
        std::cout << "Starting printer: " << name_ << std::endl;
    }

    void stop() override {
        std::cout << "Stopping printer: " << name_ << std::endl;
    }
};
