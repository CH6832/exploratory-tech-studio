#ifndef CONCRETEFACTORY1_H
#define CONCRETEFACTORY1_H

#include "AbstractFactory.h"
#include "ConcreteProductA.h"

class ConcreteFactory1 : public AbstractFactory {
public:
    AbstractProduct* createProduct() const override {
        return new ConcreteProductA();
    }
};

#endif // CONCRETEFACTORY1_H
