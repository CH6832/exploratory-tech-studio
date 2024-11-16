# Network Topology Documentation

This document describes the network topology used in the simulation, which includes key components such as routers, VPN servers, firewalls, and client devices. This topology represents a simplified corporate network model with essential security features.

---

## Network Topology Overview

The following ASCII diagram illustrates the structure and relationships of the main network components. Each device and node in this topology serves a specific role, providing connectivity, security, and access control within the network.

```plaintext
                        ┌─────────────────┐
                        │     Router      │
                        │     (Main)      │
                        │ IP: 10.0.0.1    │
                        └───────┬─────────┘
                                │
                ┌───────────────┼───────────────┐
                │                               │
         ┌──────────────┐               ┌───────────────┐
         │   VPN Server │               │    Firewall   │
         │ IP: 10.0.0.2 │               │ IP: 10.0.0.3  │
         └───────┬──────┘               └───────┬───────┘
                 │                              │
         ┌───────┼───────────┐          ┌───────┼───────────┐
         │                   │          │                   │
 ┌──────────────┐    ┌─────────────┐  ┌──────────────┐  ┌───────────────┐
 │   Notebook   │    │   Client    │  │ Web Server   │  │ Backup Server  │
 │ IP: 10.0.0.4 │    │ IP: 10.0.0.5 │  │ IP: 10.0.0.6 │  │ IP: 10.0.0.7  │
 └──────────────┘    └─────────────┘  └──────────────┘  └───────────────┘
```

### Component Descriptions

#### **Router (Main)**
- **IP Address:** `10.0.0.1`
- **Role:** Acts as the central routing device, handling packet forwarding and routing information between network segments. Routes data packets between the VPN server, firewall, and client devices.

#### **VPN Server**
- **IP Address:** `10.0.0.2`
- **Role:** Provides a secure gateway for remote users to access the network. Encrypts and decrypts data passing through the VPN tunnel to maintain data confidentiality.

#### **Firewall**
- **IP Address:** `10.0.0.3`
- **Role:** Filters incoming and outgoing traffic based on predefined security rules. Protects the network from unauthorized access and monitors traffic for potential threats.

#### **Notebook (Client Device)**
- **IP Address:** `10.0.0.4`
- **Role:** Represents a client device connected to the network. Can initiate data transmissions and access network resources via the VPN or directly through the router.

#### **Client**
- **IP Address:** `10.0.0.5`
- **Role:** A general client device that communicates with other network devices. Can serve as a workstation or test device within the simulation.

#### **Web Server**
- **IP Address:** `10.0.0.6`
- **Role:** Hosts web applications accessible to network users. May be accessible through specific firewall rules or VPN settings to restrict external access.

#### **Backup Server**
- **IP Address:** `10.0.0.7`
- **Role:** Serves as a storage and backup server, providing data redundancy and backup solutions for network resources. Communicates with other devices primarily for data storage purposes.

---

## Network Flow Summary

1. **Data Transmission**: Data is transmitted through the router, which directs packets based on destination IPs.
2. **VPN Traffic**: Encrypted data flows through the VPN server for remote access and secure communication.
3. **Firewall Rules**: Traffic between clients and servers is regulated by firewall rules to ensure data security.
4. **Client Access**: Clients such as the notebook can access internal servers (Web Server, Backup Server) based on network configuration.
