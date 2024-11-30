package protocols

import "fmt"

// TCP represents a TCP protocol instance
type TCP struct {
	SourcePort int
	DestPort   int
	Port       int
}

// NewTCP creates a new TCP protocol instance
func NewTCP(sourcePort, destPort int) *TCP {
	return &TCP{
		SourcePort: sourcePort,
		DestPort:   destPort,
	}
}

// EstablishConnection simulates the TCP connection establishment
func (t *TCP) EstablishConnection() {
	fmt.Printf("TCP connection established between ports %d and %d\n", t.SourcePort, t.DestPort)
}

// SendPacket simulates sending a TCP packet
func (t *TCP) SendPacket(data string) {
	fmt.Printf("TCP Packet sent from port %d to port %d with data: %s\n", t.SourcePort, t.DestPort, data)
}
