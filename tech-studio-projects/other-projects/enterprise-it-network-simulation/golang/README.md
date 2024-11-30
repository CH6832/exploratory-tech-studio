# IT Network Simulation System

## Overview

The **IT Network Simulation System** is a powerful and flexible simulation platform designed to model and simulate network traffic and performance in a virtualized IT environment. Built using **Go (Golang)**, the system allows for the creation and management of network devices (routers, switches, endpoints), traffic generation, and protocol simulation (TCP, UDP, ICMP, ARP, DNS). It enables performance measurement and fault detection, making it an ideal tool for testing and optimizing network configurations and protocols in a controlled environment.

This system can be easily extended and integrated with new devices, protocols, and traffic types, making it suitable for a wide range of use cases, from academic research to enterprise network management.

---

## Repository Structure

The repository is structured to allow for scalability and modularity. Below is an overview of the core directory structure:

```
├── internal/                           # Internal packages
│   ├── devices/                        # Network device implementations
│   │   ├── router.go                   # Router device logic
│   │   ├── switch.go                   # Switch device logic
│   │   └── endpoint.go                 # Endpoint device logic
│   ├── protocols/                      # Protocol-related logic (TCP, UDP, ICMP, etc.)
│   │   ├── tcp.go                      # TCP protocol logic
│   │   ├── udp.go                      # UDP protocol logic
│   │   ├── icmp.go                     # ICMP protocol logic
│   │   ├── arp.go                      # ARP protocol logic
│   │   └── dns.go                      # DNS protocol logic
│   ├── traffic/                        # Network traffic generation and analysis
│   │   ├── generator.go                # Traffic generation logic
│   │   ├── flow.go                     # Network traffic flow management
│   │   └── analysis.go                 # Traffic analysis logic
│   ├── performance/                    # Performance metrics and fault detection
│   │   ├── metrics.go                  # Performance metrics
│   │   └── fault.go                    # Fault detection logic
│   ├── topology/                       # Network topology management
│   │   ├── topology.go                 # Topology definition
│   │   ├── config.go                   # Topology configuration
│   │   └── manager.go                  # Topology management logic
│   ├── simulation/                     # Simulation controller and results management
│   │   ├── controller.go               # Simulation controller
│   │   ├── state.go                    # Simulation state management
│   │   └── results.go                  # Simulation results handling
├── pkg/                                # Shared packages
│   ├── logger/                         # Logging package
│   │   └── logger.go                   # Logger configuration
│   ├── db/                             # Database-related utilities (e.g., migrations)
│   │   ├── db.go                       # Database connection logic
│   │   └── migrations.go               # Database migrations
│   ├── api/                            # API server and handlers
│   │   ├── api.go                      # API server logic
│   │   └── handlers.go                 # API route handlers
├── main.go                             # Entry point for the simulation program
├── go.mod                              # Go module definition
├── go.sum                              # Go module checksum
├── LICENSE                             # Project license file
└── README.md                           # Project documentation (this file)
```

---

## Technologies Used

- **Go (Golang)**: The core programming language for the simulation system.
- **IntelliJ IDEA Ultimate Edition**: The primary IDE used for development, providing advanced Go support and tools for efficient coding and debugging.
- **Protocols**: The system supports key networking protocols such as TCP, UDP, ICMP, ARP, and DNS.
- **HTTP Server**: For API-based interaction with the simulation system (optional).
- **Logging**: The `logger` package ensures detailed and configurable logging throughout the simulation process.
- **Database**: The system supports logging simulation results and managing configurations via a database, leveraging migrations for version control.

---

## How to Build and Run

### Prerequisites

Before you begin, ensure you have the following installed:

- **Go (Golang)**: Version 1.18 or higher. [Download Go](https://golang.org/dl/)
- **IntelliJ IDEA Ultimate Edition**: [Download IntelliJ IDEA](https://www.jetbrains.com/idea/)
- **Database** (Optional, for result logging): Ensure you have a database setup if you're using database functionality.

### Build the Project

To build the project, you can use the following command:

```bash
go build -o enterprise-it-network-simulation main.go
```

This will compile the application and generate an executable named `enterprise-it-network-simulation`.

### Run the Simulation

To run the simulation, execute the compiled binary:

```bash
./enterprise-it-network-simulation
```

Alternatively, if you want to run it directly without building first:

```bash
go run main.go
```

### Example Runs

1. **TCP Traffic Generation**:
   When running the simulation with the default settings (TCP protocol), you will see output similar to:

   ```go
   Initializing IT Network Simulation...
   Initializing TCP protocol on port 8080...
   Creating TrafficGenerator with TCP protocol...
   Generating network traffic...
   Sending TCP packet: Hello, Network!
   Traffic generation complete.
   Simulation complete. Exiting program.
   ```

2. **UDP Traffic Generation**:
   To use UDP instead of TCP, modify the protocol initialization in `main.go` as follows:

   ```go
   udpProtocol := &protocols.UDP{Port: 8081}
   tg := traffic.NewTrafficGenerator(udpProtocol)
   ```

   The output will then show:

   ```go
   Initializing IT Network Simulation...
   Initializing UDP protocol on port 8081...
   Creating TrafficGenerator with UDP protocol...
   Generating network traffic...
   Sending UDP packet: Hello, Network!
   Traffic generation complete.
   Simulation complete. Exiting program.
   ```

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
