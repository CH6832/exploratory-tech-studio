#include <iostream>
#include "AbstractFactory.h"
#include "ConcreteFactory1.h"
#include "ConcreteFactory2.h"

void clientCode(const AbstractFactory& factory) {
    AbstractProduct* product = factory.createProduct();
    std::cout << product->operation() << std::endl;
    delete product;
}

int main() {
    ConcreteFactory1 factory1;
    clientCode(factory1);

    ConcreteFactory2 factory2;
    clientCode(factory2);

    return 0;
}
