#ifndef ADAPTEE_H
#define ADAPTEE_H

#include <string>

class Adaptee {
public:
    std::string specificRequest() const {
        return "Adaptee's specific request";
    }
};

#endif // ADAPTEE_H
