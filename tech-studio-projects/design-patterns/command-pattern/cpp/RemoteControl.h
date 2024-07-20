#ifndef REMOTECONTROL_H
#define REMOTECONTROL_H

#include "Command.h"

// Invoker
class RemoteControl {
public:
    void setCommand(Command* command) {
        command_ = command;
    }

    void pressButton() const {
        if (command_) {
            command_->execute();
        }
    }

private:
    Command* command_ = nullptr;
};

#endif // REMOTECONTROL_H
