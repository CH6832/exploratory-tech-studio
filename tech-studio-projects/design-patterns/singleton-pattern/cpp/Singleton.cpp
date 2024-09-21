#include "Singleton.h"

// Initialize static member variable
Singleton* Singleton::_instance = nullptr;

// Definition of getInstance() method
Singleton* Singleton::getInstance() {
    if (_instance == nullptr) {
        _instance = new Singleton();
    }
    return _instance;
}

// Definition of destructor
Singleton::~Singleton() {
    delete _instance;
}