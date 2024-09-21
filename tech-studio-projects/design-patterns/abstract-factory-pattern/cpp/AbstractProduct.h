#ifndef ABSTRACTPRODUCT_H
#define ABSTRACTPRODUCT_H

#include <string>

class AbstractProduct {
public:
    virtual ~AbstractProduct() = default;
    virtual std::string operation() const = 0;
};

#endif // ABSTRACTPRODUCT_H
