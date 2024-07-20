#include <iostream>
#include <vector>
#include <string>

// Observer interface
class Observer {
public:
    virtual void update(const std::string& message) = 0;
};

// Subject interface
class Subject {
public:
    virtual void attach(Observer* observer) = 0;
    virtual void detach(Observer* observer) = 0;
    virtual void notify(const std::string& message) = 0;
};

// Concrete Observer
class ConcreteObserver : public Observer {
private:
    std::string name;

public:
    ConcreteObserver(const std::string& name) : name(name) {}

    void update(const std::string& message) override {
        std::cout << name << " received message: " << message << std::endl;
    }
};

// Concrete Subject
class ConcreteSubject : public Subject {
private:
    std::vector<Observer*> observers;

public:
    void attach(Observer* observer) override {
        observers.push_back(observer);
    }

    void detach(Observer* observer) override {
        auto it = std::find(observers.begin(), observers.end(), observer);
        if (it != observers.end()) {
            observers.erase(it);
        }
    }

    void notify(const std::string& message) override {
        for (auto observer : observers) {
            observer->update(message);
        }
    }

    void doSomething() {
        // Perform some actions
        std::cout << "Subject is doing something..." << std::endl;
        // Notify observers
        notify("Something has happened!");
    }
};

// Client code
int main() {
    ConcreteSubject subject;
    Observer* observer1 = new ConcreteObserver("Observer 1");
    Observer* observer2 = new ConcreteObserver("Observer 2");

    subject.attach(observer1);
    subject.attach(observer2);

    subject.doSomething();

    subject.detach(observer2);

    subject.doSomething();

    // Cleanup
    delete observer1;
    delete observer2;

    return 0;
}
