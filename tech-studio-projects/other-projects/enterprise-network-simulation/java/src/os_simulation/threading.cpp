#include <iostream>
#include <thread>

/**
 * @brief Simulates multi-threading for processes.
 */
class Threading {
public:
    void run() {
        std::thread t(&Threading::threadFunction, this);
        t.join(); // Wait for the thread to finish
    }

private:
    void threadFunction() {
        std::cout << "Running in a separate thread..." << std::endl;
    }
};
