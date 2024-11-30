package traffic

import (
	"enterprise-it-network-simulation/protocols"
	"fmt"
)

// TrafficGenerator generates network traffic for different protocols
type TrafficGenerator struct {
	Protocol protocols.Protocol // Protocol should implement the Protocol interface
}

// NewTrafficGenerator creates a new TrafficGenerator instance with a given protocol
func NewTrafficGenerator(protocol protocols.Protocol) *TrafficGenerator {
	return &TrafficGenerator{Protocol: protocol}
}

// GenerateTraffic simulates the generation of traffic for a specific protocol
func (tg *TrafficGenerator) GenerateTraffic(data string) {
	// Call the SendPacket method of the protocol
	switch protocol := tg.Protocol.(type) {
	case *protocols.TCP:
		protocol.SendPacket(data)
	case *protocols.UDP:
		protocol.SendPacket(data)
	default:
		fmt.Println("Unsupported protocol.")
	}
}
