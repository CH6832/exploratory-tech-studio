#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>

// Prototype interface
class Prototype {
public:
    virtual Prototype* clone() const = 0;
    virtual ~Prototype() {}
};

// Concrete prototype 1
class ConcretePrototype1 : public Prototype {
private:
    int attr1;
    std::string attr2;

public:
    ConcretePrototype1(int attr1, const std::string& attr2) : attr1(attr1), attr2(attr2) {}

    Prototype* clone() const override {
        return new ConcretePrototype1(attr1, attr2);
    }

    void print() const {
        std::cout << "ConcretePrototype1: { attr1: " << attr1 << ", attr2: " << attr2 << " }" << std::endl;
    }
};

// Concrete prototype 2
class ConcretePrototype2 : public Prototype {
private:
    float attr3;
    std::vector<int> attr4;

public:
    ConcretePrototype2(float attr3, const std::vector<int>& attr4) : attr3(attr3), attr4(attr4) {}

    Prototype* clone() const override {
        return new ConcretePrototype2(attr3, attr4);
    }

    void print() const {
        std::cout << "ConcretePrototype2: { attr3: " << attr3 << ", attr4: [";
        for (const auto& item : attr4) {
            std::cout << item << ", ";
        }
        std::cout << "] }" << std::endl;
    }
};

// Prototype Factory
class PrototypeFactory {
private:
    static std::unordered_map<std::string, Prototype*> prototypes;

public:
    static void registerPrototype(const std::string& name, Prototype* prototype) {
        prototypes[name] = prototype;
    }

    static Prototype* createPrototype(const std::string& name) {
        if (prototypes.find(name) != prototypes.end()) {
            return prototypes[name]->clone();
        }
        return nullptr;
    }
};

std::unordered_map<std::string, Prototype*> PrototypeFactory::prototypes;

// Client code
int main() {
    // Register prototypes
    PrototypeFactory::registerPrototype("prototype1", new ConcretePrototype1(10, "foo"));
    PrototypeFactory::registerPrototype("prototype2", new ConcretePrototype2(3.14, {1, 2, 3}));

    // Clone prototypes
    Prototype* clone1 = PrototypeFactory::createPrototype("prototype1");
    Prototype* clone2 = PrototypeFactory::createPrototype("prototype2");

    // Output cloned objects
    if (clone1) {
        dynamic_cast<ConcretePrototype1*>(clone1)->print();
        delete clone1;
    }
    if (clone2) {
        dynamic_cast<ConcretePrototype2*>(clone2)->print();
        delete clone2;
    }

    return 0;
}
