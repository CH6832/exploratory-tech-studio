#ifndef CONCRETEPRODUCTB_H
#define CONCRETEPRODUCTB_H

#include "AbstractProduct.h"

class ConcreteProductB : public AbstractProduct {
public:
    std::string operation() const override {
        return "ConcreteProductB operation";
    }
};

#endif // CONCRETEPRODUCTB_H
