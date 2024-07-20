#ifndef CONCRETE_BUILDER_H
#define CONCRETE_BUILDER_H

#include "Builder.h"

class ConcreteBuilder : public Builder {
public:
    ConcreteBuilder() {
        reset();
    }

    void reset() {
        product_ = Product();
    }

    void buildPart1(const std::string& part1) override {
        product_.setPart1(part1);
    }

    void buildPart2(const std::string& part2) override {
        product_.setPart2(part2);
    }

    void buildPart3(const std::string& part3) override {
        product_.setPart3(part3);
    }

    Product getResult() override {
        Product result = product_;
        reset();
        return result;
    }

private:
    Product product_;
};

#endif // CONCRETE_BUILDER_H
