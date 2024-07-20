#ifndef BUILDER_H
#define BUILDER_H

#include "Product.h"

class Builder {
public:
    virtual ~Builder() {}
    virtual void buildPart1(const std::string& part1) = 0;
    virtual void buildPart2(const std::string& part2) = 0;
    virtual void buildPart3(const std::string& part3) = 0;
    virtual Product getResult() = 0;
};

#endif // BUILDER_H
