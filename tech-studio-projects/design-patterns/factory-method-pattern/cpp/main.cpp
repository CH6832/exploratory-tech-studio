#include <iostream>

// Product interface
class Pizza {
public:
    virtual void prepare() = 0;
    virtual void bake() = 0;
    virtual void cut() = 0;
    virtual void box() = 0;
};

// Concrete products
class CheesePizza : public Pizza {
public:
    void prepare() override {
        std::cout << "Preparing Cheese Pizza..." << std::endl;
    }
    void bake() override {
        std::cout << "Baking Cheese Pizza..." << std::endl;
    }
    void cut() override {
        std::cout << "Cutting Cheese Pizza..." << std::endl;
    }
    void box() override {
        std::cout << "Boxing Cheese Pizza..." << std::endl;
    }
};

class PepperoniPizza : public Pizza {
public:
    void prepare() override {
        std::cout << "Preparing Pepperoni Pizza..." << std::endl;
    }
    void bake() override {
        std::cout << "Baking Pepperoni Pizza..." << std::endl;
    }
    void cut() override {
        std::cout << "Cutting Pepperoni Pizza..." << std::endl;
    }
    void box() override {
        std::cout << "Boxing Pepperoni Pizza..." << std::endl;
    }
};

// Creator interface
class PizzaStore {
public:
    virtual Pizza* createPizza() = 0;
    void orderPizza() {
        Pizza* pizza = createPizza();
        pizza->prepare();
        pizza->bake();
        pizza->cut();
        pizza->box();
        delete pizza;
    }
};

// Concrete creators
class NYStylePizzaStore : public PizzaStore {
public:
    Pizza* createPizza() override {
        return new CheesePizza();
    }
};

class ChicagoStylePizzaStore : public PizzaStore {
public:
    Pizza* createPizza() override {
        return new PepperoniPizza();
    }
};

// Client code
int main() {
    PizzaStore* nyStore = new NYStylePizzaStore();
    nyStore->orderPizza();
    delete nyStore;

    PizzaStore* chicagoStore = new ChicagoStylePizzaStore();
    chicagoStore->orderPizza();
    delete chicagoStore;

    return 0;
}
