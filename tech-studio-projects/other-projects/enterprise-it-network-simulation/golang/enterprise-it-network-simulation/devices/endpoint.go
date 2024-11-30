package devices

import (
	"enterprise-it-network-simulation/protocols"
	"fmt"
)

// TrafficGenerator generates network traffic
type TrafficGenerator struct {
	Protocol protocols.Protocol `json:"protocol,omitempty"`
}

// NewTrafficGenerator creates a new TrafficGenerator instance
func NewTrafficGenerator(protocol protocols.Protocol) *TrafficGenerator {
	return &TrafficGenerator{Protocol: protocol}
}

// GenerateTraffic simulates the generation of traffic for a specific protocol
func (tg *TrafficGenerator) GenerateTraffic(data string) {
	switch protocol := tg.Protocol.(type) {
	case *protocols.TCP:
		protocol.SendPacket(data)
	default:
		fmt.Println("Unsupported protocol.")
	}
}
