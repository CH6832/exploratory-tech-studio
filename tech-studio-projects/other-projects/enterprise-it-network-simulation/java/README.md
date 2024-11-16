# Enterprise IT Network Simulation

## Overview
The Network Simulation Project is a modular Java-based application designed to simulate a virtual network environment. It includes core network components such as routers, firewalls, VPNs, and clients. The simulation supports various networking scenarios, from normal operation to attack simulations (e.g., DDoS, MITM, and ARP Spoofing) and can be configured and extended easily for testing and educational purposes.

### Key Features
- **Core Network Components**: Simulates routers, firewalls, VPN servers, and clients.
- **Routing Protocols**: Supports RIP and OSPF routing simulations.
- **Network Attack Simulations**: Includes DDoS, MITM, Smurf Attack, ARP Spoofing, and more.
- **Configurable**: Settings are customizable via JSON configuration files.
- **Logging and Feedback**: Detailed logging and command-line feedback for real-time status updates.

## Prerequisites
- **Java 11** or higher
- **Maven** for dependency management
- An IDE (e.g., IntelliJ IDEA) or a terminal with `java` and `mvn` commands available

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourrepo/network-simulation.git
   cd network-simulation
   ```

2. **Install dependencies**:

   ```bash
   mvn clean install
   ```

## Configuration
The simulation configuration files are located in `src/main/resources/config/`:

- **network_config.json**: Defines network-specific settings (e.g., IP addresses, subnet mask).
- **firewall_rules.json**: Specifies firewall rules and security settings.
- **vpn_config.json**: Contains VPN-related configurations.

### Example Configuration (network_config.json)
```json
{
  "networkName": "CorporateNet",
  "gatewayIP": "10.0.0.1",
  "subnetMask": 24
}
```

Edit these files as needed before running the simulation.

## Usage

The simulation is operated via command-line options. The main actions include starting, stopping, checking status, and simulating attacks.

### Basic Command Structure
```bash
java -jar NetworkSim.jar -[option]
```

#### Available Options:
- **-s, --start**: Starts the network simulation
- **-t, --stop**: Stops the network simulation
- **-status**: Displays the current status of the simulation
- **-a, --simulateAttack**: Launches a series of network attack simulations

### Example Commands

1. **Starting the Simulation**
   ```bash
   java -jar NetworkSim.jar -s
   ```
   Starts the simulation, initializing all components including routers, VPN, and firewall, and applies specified configurations.

2. **Simulating Network Attacks**
   ```bash
   java -jar NetworkSim.jar -a
   ```
   Launches a sequence of predefined network attacks, such as DDoS, MITM, and Smurf attacks, to test the resilience of the simulated network environment.

3. **Checking Simulation Status**
   ```bash
   java -jar NetworkSim.jar -status
   ```
   Displays the current status of the network components, including active connections, firewall status, and VPN state.

4. **Stopping the Simulation**
   ```bash
   java -jar NetworkSim.jar -t
   ```
   Gracefully stops all active network components and ends the simulation session.

## Logs and Troubleshooting
- **Logs**: Check the `logs/` directory for detailed logs on simulation activity, errors, and attack simulations.
- **Troubleshooting Tips**:
    - Ensure the configuration files are correctly formatted and paths are accurate.
    - For command-line options, ensure there are no typos and each command is prefixed with `-`.

## Example Output

Here’s an example output for starting the simulation:

```
Parsing command-line arguments...
Arguments parsed successfully.
Loading configuration files...
Configuration files loaded successfully.
Initializing simulation components...
Components initialized: Router, Firewall, and VPN Server.
Initializing routing protocols...
Routing protocols initialized: RIP and OSPF.
Starting the simulation...
Simulation started successfully.
```

## Documentation
Refer to the `docs/` folder for more in-depth documentation on the network topology, system architecture, and usage guide.

- **network_topology.md**: Describes the layout of routers, VPNs, and firewalls within the network.
- **system_architecture.md**: Details the modular architecture of the system and each component’s role.
- **usage_guide.md**: Provides detailed examples, configuration options, and troubleshooting advice.

## License
This project is licensed under the MIT License.
