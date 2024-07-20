#ifndef TURNONCOMMAND_H
#define TURNONCOMMAND_H

#include "Command.h"
#include "Light.h"

// Concrete command
class TurnOnCommand : public Command {
public:
    explicit TurnOnCommand(const Light& light) : light_(light) {}

    void execute() const override {
        light_.turnOn();
    }

private:
    const Light& light_;
};

#endif // TURNONCOMMAND_H
