package simulation

import (
	"enterprise-it-network-simulation/devices"
	"enterprise-it-network-simulation/performance"
	"enterprise-it-network-simulation/protocols"
	"enterprise-it-network-simulation/traffic"

	"fmt"
)

// RunSimulation simulates the entire network scenario
func RunSimulation() {
	// Initialize devices
	router := devices.NewRouter("Router1", "192.168.1.1")
	// Initialize protocol (TCP)
	tcp := protocols.NewTCP(80, 8080)

	// Initialize traffic generator
	tg := traffic.NewTrafficGenerator(tcp)

	// Start simulation
	router.AddInterface("eth0")
	tg.GenerateTraffic("Hello World")

	// Performance Metrics
	metrics := performance.NewMetrics(10.5, 1000)
	metrics.Report()

	fmt.Println("Simulation started...")
}
