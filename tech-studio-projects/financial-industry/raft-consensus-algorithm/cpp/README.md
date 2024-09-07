# Raft Simulation

This project demonstrates a basic simulation of a Raft consensus algorithm cluster. The code initializes a cluster of Raft servers, starts them, and allows them to interact for a specified duration before stopping them. 

## Code Overview

The code performs the following tasks:
1. **Initializes the Raft Servers**: Creates a specified number of Raft servers and stores them in a vector.
2. **Starts the Servers**: Begins the execution of each server.
3. **Simulates Interaction**: Keeps the servers running for a short period to allow for interaction.
4. **Stops the Servers**: Gracefully stops each server after the simulation period.

## Code Details

- **`#include "raft.h"`**: Includes the header file for the Raft server implementation. Ensure that this file is correctly implemented and located in the same directory or include path.
- **`#include <iostream>`**: For standard input and output operations.
- **`#include <vector>`**: Provides the `std::vector` container.
- **`#include <thread>`**: Enables multithreading features.
- **`#include <chrono>`**: Allows for time-based operations such as sleeping for a certain duration.

### Key Components

- **`main()` Function**:
  - Defines the total number of servers in the Raft cluster (`totalServers`).
  - Creates a vector of `std::shared_ptr<RaftServer>` to manage the lifecycle of the Raft servers.
  - Iterates to instantiate and store each server in the vector.
  - Starts all the servers using the `start()` method.
  - Pauses execution for 10 seconds to allow server interactions.
  - Stops all the servers using the `stop()` method.

## Compilation

To compile the code, ensure you have a working C++ compiler and the Raft header and source files are correctly referenced. Use a command similar to the following:

```sh
g++ -o raft_simulation main.cpp -pthread
