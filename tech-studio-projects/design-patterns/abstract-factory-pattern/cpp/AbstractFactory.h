#ifndef ABSTRACTFACTORY_H
#define ABSTRACTFACTORY_H

#include "AbstractProduct.h"

class AbstractFactory {
public:
    virtual ~AbstractFactory() = default;
    virtual AbstractProduct* createProduct() const = 0;
};

#endif // ABSTRACTFACTORY_H
