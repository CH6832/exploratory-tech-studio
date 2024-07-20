#include <iostream>
#include <vector>
#include "Component.h"

// Composite class
class Composite : public Component {
private:
    std::string name;
    std::vector<Component*> children;

public:
    Composite(const std::string& name) : name(name) {}

    void add(Component* component) {
        children.push_back(component);
    }

    void remove(Component* component) {
        // Not implementing remove for simplicity
    }

    void operation() override {
        std::cout << "Composite " << name << " operation:" << std::endl;
        for (Component* child : children) {
            child->operation();
        }
    }

    ~Composite() {
        for (Component* child : children) {
            delete child;
        }
    }
};
