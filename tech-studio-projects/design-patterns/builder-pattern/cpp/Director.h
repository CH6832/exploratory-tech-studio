#ifndef DIRECTOR_H
#define DIRECTOR_H

#include "Builder.h"

class Director {
public:
    Director(Builder* builder) : builder_(builder) {}

    void construct() {
        builder_->buildPart1("Part 1 built");
        builder_->buildPart2("Part 2 built");
        builder_->buildPart3("Part 3 built");
    }

private:
    Builder* builder_;
};

#endif // DIRECTOR_H
