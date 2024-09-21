#include <iostream>

// Component interface
class Coffee {
public:
    virtual double cost() const = 0;
    virtual ~Coffee() {}
};

// Concrete component
class SimpleCoffee : public Coffee {
public:
    double cost() const override {
        return 1.0;
    }
};

// Decorator class
class CoffeeDecorator : public Coffee {
protected:
    Coffee* coffee;
public:
    CoffeeDecorator(Coffee* coffee) : coffee(coffee) {}
    virtual double cost() const override {
        return coffee->cost();
    }
    virtual ~CoffeeDecorator() {
        delete coffee;
    }
};

// Concrete decorators
class Milk : public CoffeeDecorator {
public:
    Milk(Coffee* coffee) : CoffeeDecorator(coffee) {}
    double cost() const override {
        return coffee->cost() + 0.5;
    }
};

class Sugar : public CoffeeDecorator {
public:
    Sugar(Coffee* coffee) : CoffeeDecorator(coffee) {}
    double cost() const override {
        return coffee->cost() + 0.2;
    }
};

// Client code
int main() {
    Coffee* coffee = new SimpleCoffee();
    std::cout << "Cost of simple coffee: " << coffee->cost() << std::endl;

    coffee = new Milk(coffee);
    std::cout << "Cost of coffee with milk: " << coffee->cost() << std::endl;

    coffee = new Sugar(coffee);
    std::cout << "Cost of coffee with sugar: " << coffee->cost() << std::endl;

    coffee = new Sugar(new Milk(new SimpleCoffee()));
    std::cout << "Cost of coffee with milk and sugar: " << coffee->cost() << std::endl;

    delete coffee;
    return 0;
}
