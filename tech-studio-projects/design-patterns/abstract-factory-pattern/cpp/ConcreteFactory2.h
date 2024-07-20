#ifndef CONCRETEFACTORY2_H
#define CONCRETEFACTORY2_H

#include "AbstractFactory.h"
#include "ConcreteProductB.h"

class ConcreteFactory2 : public AbstractFactory {
public:
    AbstractProduct* createProduct() const override {
        return new ConcreteProductB();
    }
};

#endif // CONCRETEFACTORY2_H
