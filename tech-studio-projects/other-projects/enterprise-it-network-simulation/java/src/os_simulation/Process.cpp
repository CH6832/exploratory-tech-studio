#include <iostream>
#include <vector>
#include <queue>
#include <iomanip>
#include <stdexcept>

class Process {
public:
    int id;          // Process ID
    int burstTime;   // Burst time for the process
    int waitingTime; // Waiting time before the process starts execution
    int priority;    // Priority for SJF (lower values indicate higher priority)

    Process(int id, int burstTime, int priority = 0) 
        : id(id), burstTime(burstTime), waitingTime(0), priority(priority) {}
};
