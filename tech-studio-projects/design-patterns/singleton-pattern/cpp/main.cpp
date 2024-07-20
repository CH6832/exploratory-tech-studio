#include <iostream>
#include <thread> // For std::this_thread
#include <chrono> // For std::chrono
#include <atomic> // For std::atomic
#include <mutex> // For std::mutex and std::lock_guard
#include "src/creational/Singleton.h"

std::atomic<bool> exitFlag(false);
std::mutex exitMutex;

// Function to check for ESC key press
void checkForExit() {
    while (!exitFlag) {
        if (std::cin.peek() == 27) { // Check for ASCII code of ESC key
            std::lock_guard<std::mutex> lock(exitMutex);
            exitFlag = true;
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(100)); // Check every 100 milliseconds
    }
}

int main() {

    std::cout << "Press ESC to exit the program." << std::endl;

    /**
     * <Singleton example>
     */

    // Create instances
    Singleton* singleton1 = Singleton::getInstance();
    Singleton* singleton2 = Singleton::getInstance();

    std::cout << "Singleton instance created." << std::endl;
    std::cout << "Singleton instance addresses: " << singleton1 << " and " << singleton2 << std::endl;

    std::cout << "Press any key to delete singleton instances..." << std::endl;
    std::cin.get(); // Wait for any key press

    // Cleanup (optional)
    delete singleton1;
    delete singleton2;

    std::cout << "Singleton instances deleted." << std::endl;

    /**
     * </Singleton example>
     */

    return 0;
}
