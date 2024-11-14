class Scheduler {
private:
    std::vector<Process> processes;
    int quantum; // Time quantum for Round Robin

public:
    Scheduler() : quantum(2) {} // Default time quantum for Round Robin

    void setQuantum(int q) {
        if (q <= 0) throw std::invalid_argument("Quantum must be greater than 0.");
        quantum = q;
    }

    void addProcess(int burstTime, int priority = 0) {
        if (burstTime <= 0) throw std::invalid_argument("Burst time must be greater than 0.");
        int id = processes.size() + 1; // Simple ID assignment
        processes.push_back(Process(id, burstTime, priority));
    }

    void scheduleFCFS() {
        // FCFS Scheduling Logic...
    }

    void scheduleSJF() {
        std::sort(processes.begin(), processes.end(), [](const Process &a, const Process &b) {
            return a.burstTime < b.burstTime; // Sort by burst time
        });

        // Similar to FCFS but with sorted order
        // Implementing SJF logic...
    }

    void scheduleRoundRobin() {
        std::queue<Process> q;
        int currentTime = 0;

        std::cout << "\nScheduling processes using Round Robin (Quantum: " << quantum << "):\n";

        for (auto &p : processes) {
            q.push(p);
        }

        while (!q.empty()) {
            Process p = q.front();
            q.pop();

            if (p.burstTime > quantum) {
                currentTime += quantum;
                p.burstTime -= quantum;
                q.push(p);
            } else {
                currentTime += p.burstTime;
                p.waitingTime = currentTime - p.burstTime; // Calculate waiting time
                p.burstTime = 0; // Process finished
                std::cout << "Process ID: " << p.id 
                          << ", Waiting Time: " << p.waitingTime << std::endl;
            }
        }
    }
};
