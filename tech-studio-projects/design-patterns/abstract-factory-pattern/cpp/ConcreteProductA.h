#ifndef CONCRETEPRODUCTA_H
#define CONCRETEPRODUCTA_H

#include "AbstractProduct.h"

class ConcreteProductA : public AbstractProduct {
public:
    std::string operation() const override {
        return "ConcreteProductA operation";
    }
};

#endif // CONCRETEPRODUCTA_H
