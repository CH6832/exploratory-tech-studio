# System Architecture Documentation

This document describes the modular architecture of the network simulation system. It details each core component, its functionality, and interactions within the system.

---

## System Architecture Overview

The following ASCII diagram illustrates the high-level architecture of the simulation system. This modular design enhances flexibility, enabling customization and scaling for various network configurations and security setups.

```plaintext
┌────────────────────────────────────────────────────┐
│                      Main                          │
│ ┌───────────────────────────────────────────────┐  │
│ │              Command-Line Parser              │  │
│ │  Parses user input and configures the system  │  │
│ └───────────────────────────────────────────────┘  │
│                                                    │
│ ┌───────────┐      ┌────────────┐       ┌────────┐ │
│ │   Router  │◄────►│ VPN Server │◄─────►│ Client │ │
│ │  Manages  │      │   Secure   │       │ Device │ │
│ │  routing  │      │ connections│       │ Access │ │
│ └───────────┘      └────────────┘       └────────┘ │
│                                                    │
│ Modules:                                           │
│ ┌─────────────┐ ┌───────────────┐ ┌───────────────┐│
│ │ VPNConfig   │ │ FirewallRules │ │ NetworkConfig ││
│ │ VPN settings│ │ Access control│ │ Network setup ││
│ └─────────────┘ └───────────────┘ └───────────────┘│
└────────────────────────────────────────────────────┘
```

---

### Component Descriptions

#### **Main System**
- **Purpose**: Serves as the primary controller, initializing and orchestrating interactions between modules and devices.
- **Key Subcomponent**:
    - **Command-Line Parser**: Handles user inputs, command-line arguments, and configuration files. It initializes the system based on provided parameters and allows flexibility in running simulations with different settings.

#### **Router**
- **Function**: Manages data packet forwarding within the network, ensuring that data reaches the correct device based on the routing table.
- **Interactions**: Communicates with both the VPN server for secure traffic and clients for general data exchange.

#### **VPN Server**
- **Function**: Manages secure connections by encrypting and decrypting data between remote clients and the network.
- **Interactions**: Connects with the router for packet forwarding and with client devices for secure remote access.

#### **Client**
- **Function**: Represents an end-user device that interacts with other network components, such as accessing the VPN server or requesting data from servers.
- **Interactions**: Engages with the router for general access and the VPN server for secure data exchange.

---

### Module Descriptions

Each module represents a specific aspect of the system's configuration or security policies, adding a layer of abstraction and flexibility to the simulation.

#### **VPNConfig**
- **Purpose**: Defines configurations for the VPN server, including settings for encryption, connection policies, and tunneling protocols.
- **Usage**: This module is referenced by the VPN server component to establish secure communication protocols.

#### **FirewallRules**
- **Purpose**: Manages access control policies, such as allowed IPs, port restrictions, and traffic filtering.
- **Usage**: This module is used by the firewall to enforce network security rules, preventing unauthorized access to protected resources.

#### **NetworkConfig**
- **Purpose**: Contains network settings such as IP ranges, subnet masks, and DNS servers. It establishes the foundational network structure and policies.
- **Usage**: Used by both the main system and individual components like the router and VPN server to set up network parameters and ensure consistent configurations.

---

## System Flow Summary

1. **Initialization**: The system is initialized through the command-line parser, which reads configuration files and sets up each component.
2. **Routing & Secure Communication**: The router manages general traffic, while the VPN server ensures secure data exchange.
3. **Client Access**: Clients interact with the network through the router and can securely connect via the VPN server if configured.
4. **Firewall Enforcement**: FirewallRules module enforces access control, blocking or allowing traffic based on preconfigured rules.
