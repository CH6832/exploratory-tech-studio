#ifndef SINGLETON_H
#define SINGLETON_H

class Singleton {
private:
    static Singleton* _instance;

    // Private constructor to prevent instantiation
    Singleton() {}

public:
    // Public static method to get the instance
    static Singleton* getInstance();

    // Optional: Define destructor to release resources
    ~Singleton();

    // Disable copy constructor and assignment operator
    Singleton(const Singleton&) = delete;
    Singleton& operator=(const Singleton&) = delete;
};

#endif // SINGLETON_H
