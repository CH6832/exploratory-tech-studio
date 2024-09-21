#ifndef TURNOFFCOMMAND_H
#define TURNOFFCOMMAND_H

#include "Command.h"
#include "Light.h"

// Concrete command
class TurnOffCommand : public Command {
public:
    explicit TurnOffCommand(const Light& light) : light_(light) {}

    void execute() const override {
        light_.turnOff();
    }

private:
    const Light& light_;
};

#endif // TURNOFFCOMMAND_H
