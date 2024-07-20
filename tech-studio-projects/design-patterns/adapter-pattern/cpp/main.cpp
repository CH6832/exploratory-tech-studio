#include <iostream>
#include "Target.h"
#include "Adaptee.h"
#include "Adapter.h"

void clientCode(const Target* target) {
    std::cout << target->request() << std::endl;
}

int main() {
    Adaptee* adaptee = new Adaptee();
    Adapter* adapter = new Adapter(adaptee);
    clientCode(adapter);

    delete adaptee;
    delete adapter;

    return 0;
}
