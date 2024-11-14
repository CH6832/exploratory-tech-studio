int main() {
    Scheduler scheduler;
    FileSystem fileSystem;
    int choice;

    do {
        std::cout << "\nOperating System Simulation Menu:\n";
        std::cout << "1. Add Process\n";
        std::cout << "2. Schedule Processes (FCFS)\n";
        std::cout << "3. Schedule Processes (SJF)\n";
        std::cout << "4. Schedule Processes (Round Robin)\n";
        std::cout << "5. Set Round Robin Quantum\n";
        std::cout << "6. Create File\n";
        std::cout << "7. List Files\n";
        std::cout << "8. Delete File\n";
        std::cout << "9. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1: {
                int burstTime, priority;
                std::cout << "Enter Burst Time for Process: ";
                std::cin >> burstTime;
                std::cout << "Enter Priority for Process (default 0): ";
                std::cin >> priority;
                try {
                    scheduler.addProcess(burstTime, priority);
                    std::cout << "Process added!\n";
                } catch (const std::invalid_argument& e) {
                    std::cout << "Error: " << e.what() << std::endl;
                }
                break;
            }
            case 2:
                scheduler.scheduleFCFS();
                break;
            case 3:
                scheduler.scheduleSJF();
                break;
            case 4:
                scheduler.scheduleRoundRobin();
                break;
            case 5: {
                int q;
                std::cout << "Enter new time quantum for Round Robin: ";
                std::cin >> q;
                try {
                    scheduler.setQuantum(q);
                    std::cout << "Quantum set to " << q << ".\n";
                } catch (const std::invalid_argument& e) {
                    std::cout << "Error: " << e.what() << std::endl;
                }
                break;
            }
            case 6: {
                std::string filename;
                std::cout << "Enter filename to create: ";
                std::cin >> filename;
                fileSystem.createFile(filename);
                break;
            }
            case 7:
                fileSystem.listFiles();
                break;
            case 8: {
                std::string filename;
                std::cout << "Enter filename to delete: ";
                std::cin >> filename;
                fileSystem.deleteFile(filename);
                break;
            }
            case 9:
                std::cout << "Exiting...\n";
                break;
            default:
                std::cout << "Invalid choice, please try again.\n";
        }
    } while (choice != 9);

    return 0;
}
