# IT Network Simulation System - **Architecture.md**

## **1. Overview**

The architecture of the **IT Network Simulation System** is designed to simulate complex networking scenarios with multiple devices, protocols, and performance metrics. The system is modular, scalable, and built with **Go** (Golang) for high concurrency, efficient memory usage, and performance.

The architecture includes the following key components:

- **Simulation Engine**: Core logic that simulates network devices, protocols, traffic, and performance metrics.
- **User Interface**: Web-based or command-line interface for user interaction with the simulator.
- **Database**: For storing configuration, simulation states, and results (optional).
- **Performance Monitoring**: Module for measuring latency, throughput, and packet loss.
- **Logging and Reporting**: System to log events and generate simulation reports.

---

## **2. High-Level System Architecture**

Below is a **high-level architecture diagram** that represents the overall structure of the simulation system:

```
+---------------------------------------------------------------+
|                          User Interface                      |
|---------------------------------------------------------------|
|      +-------------------+     +---------------------+       |
|      | Web Interface      |     | Command Line Interface |       |
|      +-------------------+     +---------------------+       |
+----------------------------+---------------------------------+
                               |
                               v
                      +----------------------+
                      |   Simulation Engine  |
                      +----------------------+
                               |
           +---------------------------------------+
           |                                       |
+---------------------+              +-----------------------+
|   Device Simulation |              |  Protocol Simulation  |
+---------------------+              +-----------------------+
           |                                       |
+-------------------+               +---------------------+
|  Network Traffic  |               |   Performance Metrics|
|    Simulation     |               |      & Faults        |
+-------------------+               +---------------------+
           |                                       |
           v                                       v
+------------------------------------------------------------+
|                          Database (Optional)               |
|     Stores configuration, results, logs, simulation state   |
+------------------------------------------------------------+
                               |
                               v
                        +---------------------+
                        |   Logging & Reports |
                        +---------------------+
```

### **Key Components Breakdown**

1. **User Interface (UI)**:
   - **Web Interface**: A front-end dashboard that allows users to visualize the network topology, configure devices, and view simulation results in real-time.
   - **CLI Interface**: A command-line interface (CLI) that allows users to configure the network, run simulations, and monitor results via terminal commands.

2. **Simulation Engine**:
   The heart of the system, which simulates devices, protocols, and network traffic. It consists of multiple sub-modules:
   - **Device Simulation**: Models the network devices such as routers, switches, and end devices. Each device simulates its behavior, including packet forwarding, routing, and interactions with other devices.
   - **Protocol Simulation**: Simulates the communication protocols between devices, including TCP, UDP, ARP, and DNS. Protocol handling ensures realistic packet exchanges and error handling.
   - **Network Traffic Simulation**: Models the flow of data across the network. It generates traffic patterns for different protocols and measures network performance metrics like latency and packet loss.
   - **Performance Metrics & Fault Simulation**: Monitors the performance of the network and injects faults (e.g., congestion, link failure, delay) into the simulation to analyze the network's robustness under different conditions.

3. **Database (Optional)**:
   Stores configuration settings, simulation results, logs, and device states. This can help in re-running simulations or performing analysis on historical data.

4. **Logging & Reporting**:
   Tracks and logs all simulation activities. It generates reports based on the simulation's outcome, showing network performance metrics like latency, throughput, packet loss, and congestion, among others.

---

## **3. Detailed Component Architecture**

### **3.1 Simulation Engine**

The **Simulation Engine** is where most of the simulation logic resides. It consists of several interacting modules that perform tasks in parallel (using Go’s goroutines) to simulate network activity:

```
+------------------------------------------------------------+
|                       Simulation Engine                    |
|------------------------------------------------------------|
|                                                            |
|  +---------------------+      +------------------------+  |
|  |  Device Simulation   | <---> |  Protocol Simulation  |  |
|  +---------------------+      +------------------------+  |
|           |                          |                    |
|           v                          v                    |
|  +---------------------+      +------------------------+  |
|  | Network Traffic     | <--> | Performance Metrics     |  |
|  | Simulation          |      | & Fault Injection       |  |
|  +---------------------+      +------------------------+  |
|                                                            |
+------------------------------------------------------------+
```

#### **3.1.1 Device Simulation**

Devices (routers, switches, endpoints) are simulated as individual entities in the network. Each device has its own state, configuration, and behavior.

- **Router**: Routes packets based on static or dynamic routing protocols (e.g., RIP, OSPF). Routes are calculated and updated during simulation.
- **Switch**: Forwards frames based on MAC addresses. Simulates VLANs and MAC address table maintenance.
- **End Devices**: Simulates user devices like computers or servers that generate and consume traffic.

```
+---------------------------+
|   Device Simulation       |
+---------------------------+
|                           |
|  +---------------------+  |
|  |  Router Simulation  |  |
|  +---------------------+  |
|           |               |
|  +---------------------+  |
|  |  Switch Simulation  |  |
|  +---------------------+  |
|           |               |
|  +---------------------+  |
|  | End Device Simulation|  |
|  +---------------------+  |
+---------------------------+
```

#### **3.1.2 Protocol Simulation**

Each protocol has a dedicated module that handles the specific behavior of network communication. This includes the creation, transmission, and reception of packets.

- **TCP/UDP**: These modules handle the connection setup, data transmission, packet acknowledgment (for TCP), and error handling.
- **ICMP (Ping)**: Generates echo requests and responses to measure round-trip time (RTT).
- **ARP**: Maps IP addresses to MAC addresses within local subnets.
- **DNS**: Resolves domain names to IP addresses for simulation of real-world traffic.

```
+-------------------------------+
|   Protocol Simulation         |
+-------------------------------+
|                               |
|  +------------------------+   |
|  | TCP/UDP Simulation     |   |
|  +------------------------+   |
|           |                   |
|  +------------------------+   |
|  | ICMP (Ping) Simulation |   |
|  +------------------------+   |
|           |                   |
|  +------------------------+   |
|  | ARP Simulation         |   |
|  +------------------------+   |
|           |                   |
|  +------------------------+   |
|  | DNS Simulation         |   |
|  +------------------------+   |
+-------------------------------+
```

#### **3.1.3 Network Traffic Simulation**

This module generates network traffic based on user configuration. It simulates various traffic types, including:
- **Real-time traffic**: VoIP, video streaming.
- **Bulk traffic**: File transfers, HTTP downloads/uploads.
- **Interactive traffic**: Web browsing, SSH.

```
+------------------------------+
| Network Traffic Simulation   |
+------------------------------+
|                              |
|  +------------------------+  |
|  | Real-Time Traffic      |  |
|  +------------------------+  |
|  +------------------------+  |
|  | Bulk Traffic           |  |
|  +------------------------+  |
|  +------------------------+  |
|  | Interactive Traffic    |  |
|  +------------------------+  |
+------------------------------+
```

#### **3.1.4 Performance Metrics & Fault Simulation**

- **Performance Monitoring**: Continuously tracks key network metrics like latency, throughput, jitter, and packet loss.
- **Fault Injection**: Simulates network faults such as congestion, packet corruption, link failure, and delays.

```
+---------------------------------+
| Performance Metrics & Fault     |
| Simulation                     |
+---------------------------------+
|                                 |
|  +-------------------------+   |
|  | Latency, Throughput,    |   |
|  | Packet Loss             |   |
|  +-------------------------+   |
|  +-------------------------+   |
|  | Fault Injection (e.g.,  |   |
|  | Link Failures, Delay)   |   |
|  +-------------------------+   |
+---------------------------------+
```

### **3.2 Logging & Reporting**

The **Logging & Reporting** module logs events and generates reports for each simulation run:
- **Event Logging**: Logs network events, packet exchanges, device interactions, and errors.
- **Performance Reports**: Summarizes simulation results, such as latency, packet loss, and throughput.

```
+------------------------------+
| Logging & Reporting          |
+------------------------------+
|                              |
|  +------------------------+  |
|  | Event Logs             |  |
|  +------------------------+  |
|  +------------------------+  |
|  | Performance Reports    |  |
|  +------------------------+  |
+------------------------------+
```
