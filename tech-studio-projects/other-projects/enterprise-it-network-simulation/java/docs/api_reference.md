This document serves as a detailed API reference for developers and users looking to understand or extend the simulation.
API Reference
Main

    Purpose: Entry point for starting, stopping, and managing the simulation.
    Methods:
        void main(String[] args): Parses arguments and initializes components.

Router

    Purpose: Represents a router in the simulation.
    Constructor:
        Router(String routerName, String ipAddress, Map<String, String> routingTable): Initializes a router with a routing table.
    Methods:
        void initialize(): Sets up routing protocols like RIP or OSPF.
        void addRoute(String destination, String nextHop): Adds a route.
        void removeRoute(String destination): Removes a route.

VPNConfig

    Purpose: Holds VPN configuration details.
    Attributes:
        serverIp (String): IP address of the VPN server.
        serverPort (int): Port for VPN access.