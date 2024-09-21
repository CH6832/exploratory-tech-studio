#include <iostream>
#include "Component.h"

// Leaf class
class Leaf : public Component {
private:
    std::string name;

public:
    Leaf(const std::string& name) : name(name) {}

    void operation() override {
        std::cout << "Leaf " << name << " operation" << std::endl;
    }
};
