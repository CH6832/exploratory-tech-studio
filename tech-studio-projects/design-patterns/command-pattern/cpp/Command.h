#ifndef COMMAND_H
#define COMMAND_H

// Command interface
class Command {
public:
    virtual ~Command() = default;
    virtual void execute() const = 0;
};

#endif // COMMAND_H
