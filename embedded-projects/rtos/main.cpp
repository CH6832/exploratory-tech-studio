#include "rtos.h"

int main() {
    // Create an instance of the RTOS
    RTOS rtos;

    // Add tasks to the RTOS
    rtos.addTask(1, 2, 100, false, []() {
        // Task function
        std::cout << "Task 1 running..." << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(100));
        std::cout << "Task 1 completed." << std::endl;
    });

    rtos.addTask(2, 1, 200, true, [&]() {
        // Task function with preemption
        std::cout << "Task 2 running..." << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(50));
        std::cout << "Suspending Task 1..." << std::endl;
        rtos.suspendTask(1); // Suspend Task 1
        std::this_thread::sleep_for(std::chrono::milliseconds(150));
        std::cout << "Resuming Task 1..." << std::endl;
        rtos.resumeTask(1); // Resume Task 1
        std::cout << "Task 2 completed." << std::endl;
    });

    rtos.addTask(3, 3, 150, false, []() {
        // Task function
        std::cout << "Task 3 running..." << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(150));
        std::cout << "Task 3 completed." << std::endl;
    });

    // Create an interrupt thread
    rtos.createInterruptThread([]() {
        std::cout << "Interrupt thread running..." << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(500));
        std::cout << "Interrupt thread completed." << std::endl;
    });

    // Run the RTOS scheduler
    rtos.scheduler();

    // Destroy all interrupt threads
    rtos.destroyInterruptThreads();

    return 0;
}
