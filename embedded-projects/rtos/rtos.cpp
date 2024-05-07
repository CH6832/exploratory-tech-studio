// Header File (rtos.h): This file will contain the class declarations and any necessary structure definitions and function prototypes.

#include "rtos.h"

void RTOS::addTask(int id, int priority, int duration_ms, bool preemptable, std::function<void()> taskFunction) {
    tasks.push_back({id, priority, duration_ms, preemptable, false, false, {}, {}, taskFunction, nullptr});
}

void RTOS::taskFunction(Task& task) {
    std::unique_lock<std::mutex> lock(task.mtx);
    task.running = true;
    task.cv.notify_all(); // Notify that the task is now running
    lock.unlock();

    // Execute the task
    task.taskFunction();
    lock.lock();
    task.running = false;
    task.cv.notify_all(); // Notify that the task has completed
    if (task.suspended) {
        task.cv.wait(lock, [&]() { return !task.suspended; }); // Wait for task to be resumed
    }
}

void RTOS::scheduler() {
    while (!tasks.empty()) {
        // Find the highest priority non-preempted task
        Task* highest_priority_task = nullptr;
        for (auto& task : tasks) {
            std::unique_lock<std::mutex> lock(task.mtx);
            if (!task.running && !task.suspended && (highest_priority_task == nullptr || task.priority < highest_priority_task->priority)) {
                highest_priority_task = &task;
            }
        }

        if (highest_priority_task) {
            // Execute the highest priority task
            highest_priority_task->taskThread = new std::thread(&RTOS::taskFunction, this, std::ref(*highest_priority_task));
        } else {
            // Wait for a notification indicating a task has finished or has been resumed
            std::unique_lock<std::mutex> lock(mtx);
            cv.wait(lock);
        }
    }
}

// Implement other methods of the RTOS class here...
