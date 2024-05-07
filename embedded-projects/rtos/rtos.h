// Source File (rtos.cpp): This file will contain the implementation of the RTOS class methods defined in the header file.

#ifndef RTOS_H
#define RTOS_H

#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <functional>

// Task structure representing a task in the RTOS
struct Task {
    int id;
    int priority;
    int duration_ms;
    bool preemptable;
    bool running;
    bool suspended;
    std::mutex mtx;
    std::condition_variable cv;
    std::function<void()> taskFunction;
    std::thread* taskThread;
};

// RTOS class responsible for task scheduling and execution
class RTOS {
private:
    std::vector<Task> tasks;
    std::mutex mtx;
    std::condition_variable cv;
    std::queue<int> message_queue;
    std::vector<std::thread> interrupt_threads;

public:
    // Add a task to the RTOS
    void addTask(int id, int priority, int duration_ms, bool preemptable, std::function<void()> taskFunction);

    // Task function to execute
    void taskFunction(Task& task);

    // Scheduler to execute tasks based on priority and time constraints
    void scheduler();

    // Send message to a task
    void sendMessage(int taskId, int message);

    // Receive message from the message queue
    std::pair<int, int> receiveMessage();

    // Signal task completion
    void taskComplete();

    // Suspend a task
    void suspendTask(int taskId);

    // Resume a suspended task
    void resumeTask(int taskId);

    // Create an interrupt thread
    void createInterruptThread(std::function<void()> interruptFunction);

    // Destroy all interrupt threads
    void destroyInterruptThreads();
};

#endif /* RTOS_H */
