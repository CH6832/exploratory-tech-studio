#ifndef TARGET_H
#define TARGET_H

#include <string>

class Target {
public:
    virtual ~Target() = default;
    virtual std::string request() const = 0;
};

#endif // TARGET_H
