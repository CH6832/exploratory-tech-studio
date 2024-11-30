package main

import (
	"enterprise-it-network-simulation/protocols"
	"enterprise-it-network-simulation/traffic"
	"fmt"
)

func main() {
	// Print a message indicating the start of the program
	fmt.Println("Initializing IT Network Simulation...")

	// Initialize the TCP protocol (you could also use UDP or others)
	fmt.Println("Initializing TCP protocol on port 8080...")
	tcpProtocol := &protocols.TCP{Port: 8080}

	// Create a new TrafficGenerator for the TCP protocol
	fmt.Println("Creating TrafficGenerator with TCP protocol...")
	tg := traffic.NewTrafficGenerator(tcpProtocol)

	// Print message before generating traffic
	fmt.Println("Generating network traffic...")

	// Generate traffic (this will send a packet)
	tg.GenerateTraffic("Hello, Network!")

	// Print message indicating that traffic generation has completed
	fmt.Println("Traffic generation complete.")

	// Optional: Any final messages or cleanup before the program ends
	fmt.Println("Simulation complete. Exiting program.")
}
