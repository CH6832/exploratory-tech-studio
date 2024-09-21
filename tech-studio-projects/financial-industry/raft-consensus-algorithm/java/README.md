Here's a condensed `README.md` for the Java version of the Raft simulation:

```markdown
# Raft Simulation

This project demonstrates a basic simulation of a Raft consensus algorithm cluster in Java. The code initializes a cluster of Raft servers, starts them, and allows them to interact for a specified duration before stopping them.

## Code Overview

The code performs the following tasks:
1. **Initializes the Raft Servers**: Creates a specified number of Raft servers and stores them in a list.
2. **Starts the Servers**: Begins the execution of each server in a separate thread.
3. **Simulates Interaction**: Keeps the servers running for a short period to allow interaction.
4. **Stops the Servers**: Gracefully stops each server after the simulation period.

## Code Details

- **`RaftServer.java`**: Contains the implementation of the Raft server, including state management, election routines, and thread handling.
- **`Main.java`**: Entry point that initializes, starts, and stops the Raft servers.

### Key Components

- **`RaftServer` Class**:
  - Manages server states (`Follower`, `Candidate`, `Leader`).
  - Handles timeout events and state transitions.
  - Implements multithreading for election routines.
- **`Main` Class**:
  - Defines the total number of servers in the Raft cluster (`totalServers`).
  - Creates and manages a list of `RaftServer` instances.
  - Starts all servers and pauses execution for 10 seconds to simulate server interactions.
  - Stops all servers after the simulation period.

## Compilation and Execution

To compile and run the Java code, use the following commands:

```sh
javac RaftServer.java Main.java
java Main
```
```

This README provides a clear overview of the Java project, detailing its purpose, key components, and how to compile and run the code.