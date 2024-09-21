#include <iostream>

// Subject interface
class Subject {
public:
    virtual void request() = 0;
};

// RealSubject class
class RealSubject : public Subject {
public:
    void request() override {
        std::cout << "RealSubject: Handling request." << std::endl;
    }
};

// Proxy class
class Proxy : public Subject {
private:
    RealSubject* realSubject;

    bool checkAccess() {
        std::cout << "Proxy: Checking access permissions." << std::endl;
        return true;
    }

    void logAccess() {
        std::cout << "Proxy: Logging the time of request." << std::endl;
    }

public:
    Proxy(RealSubject* realSubject) : realSubject(realSubject) {}

    void request() override {
        if (checkAccess()) {
            realSubject->request();
            logAccess();
        }
    }
};

// Client code
int main() {
    RealSubject realSubject;
    Proxy proxy(&realSubject);

    proxy.request();

    return 0;
}
