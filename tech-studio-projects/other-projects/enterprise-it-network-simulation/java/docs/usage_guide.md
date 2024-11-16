# Usage Guide

This guide provides comprehensive instructions for setting up, running, and configuring the network simulation. It includes prerequisites, installation steps, and example configurations.

---

## Overview

The Network Simulation Project replicates a virtual network environment, including essential components such as a **Router**, **VPN Server**, **Firewall**, and **Client Devices**. This simulation is useful for testing network configurations, security policies, and response to simulated attacks.

---

## Prerequisites

To run this simulation, ensure you have the following installed:
- **Java 11** or above
- **Maven** for dependency management
- **IDE or Terminal** with `java` and `mvn` commands accessible

---

## Installation

1. **Clone the Repository**:
   Clone the project repository to your local machine.

    ```shell
    git clone https://github.com/yourrepo/network-simulation.git
    cd network-simulation
    ```

2. **Install Dependencies**:
   Use Maven to build the project and download all required dependencies.

    ```shell
    mvn clean install
    ```

---

## Running the Simulation

### Start the Simulation

To initialize and start the simulation, use the following command:

```shell
java -jar NetworkSim.jar -a start
```

### Simulate Network Attacks

To test the system's resilience and observe the behavior under simulated network attacks, use:

```shell
java -jar NetworkSim.jar -a simulateAttack
```

This command triggers a predefined network attack scenario to test firewall, VPN security, and response protocols.

---

## Configuration

The simulation allows for a high level of customization. Configuration files are located in the `config/` directory in JSON format, enabling users to specify network parameters, routing protocols, and security settings.

### Example Configuration: `config/network_config.json`

Below is an example of a network configuration file, `network_config.json`, which defines core network settings. Customize these parameters to fit your desired network setup.

```json
{
    "networkName": "CorporateNet",
    "gatewayIP": "10.0.0.1",
    "subnetMask": 24,
    "dnsServers": ["10.0.0.2", "8.8.8.8"],
    "routingProtocols": ["OSPF", "RIP"],
    "firewallRules": [
        {"rule": "allow", "ipRange": "10.0.0.0/24"},
        {"rule": "deny", "ipRange": "192.168.1.0/24"}
    ]
}
```

### Key Parameters:
- **networkName**: Defines the name of the network.
- **gatewayIP**: The IP address of the gateway router.
- **subnetMask**: Defines the network’s subnet mask (e.g., `24` for a `/24` subnet).
- **dnsServers**: Specifies DNS server IPs for resolving domain names.
- **routingProtocols**: List of routing protocols enabled within the network (e.g., OSPF, RIP).
- **firewallRules**: Custom firewall rules to allow or block specific IP ranges.

---

## Troubleshooting

- **Error: `Port already in use`**: Ensure no other applications are running on the same network ports used by the simulation components (Router, VPN, etc.).
- **Dependency Errors**: Run `mvn clean install` to ensure all dependencies are correctly installed.
- **Configuration Errors**: Verify JSON syntax in configuration files, as syntax errors can prevent successful parsing.
