#ifndef LIGHT_H
#define LIGHT_H

#include <iostream>

// Receiver
class Light {
public:
    void turnOn() const {
        std::cout << "Light is on" << std::endl;
    }

    void turnOff() const {
        std::cout << "Light is off" << std::endl;
    }
};

#endif // LIGHT_H
