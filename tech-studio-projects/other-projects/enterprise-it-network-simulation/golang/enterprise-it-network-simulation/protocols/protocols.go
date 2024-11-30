package protocols

import "fmt"

// Protocol interface that all protocols (e.g., TCP, UDP) must implement
type Protocol interface {
	SendPacket(data string)
}

// UDP struct implements Protocol interface
type UDP struct {
	Port int
}

// SendPacket method for UDP
func (u *UDP) SendPacket(data string) {
	fmt.Println("Sending UDP packet:", data)
}
