#include <iostream>
#include "ConcreteBuilder.h"
#include "Director.h"

int main() {
    ConcreteBuilder builder;
    Director director(&builder);
    director.construct();
    Product product = builder.getResult();
    product.display();

    return 0;
}
