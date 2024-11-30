package devices

import (
	"errors"
	"fmt"
	"sync"
	"time"
)

// MACEntry represents a single entry in the switch's forwarding table.
type MACEntry struct {
	MACAddress string    `json:"mac_address"`
	Port       int       `json:"port"`
	Timestamp  time.Time `json:"timestamp"`
}

// Switch represents a network switch with MAC address learning and forwarding functionality.
type Switch struct {
	ID              string              `json:"id"`
	ForwardingTable map[string]MACEntry // MAC address to port mapping
	Ports           int                 `json:"ports"`
	mu              sync.RWMutex        // Mutex for thread-safe operations
}

// NewSwitch initializes a new Switch with a given number of ports.
func NewSwitch(id string, ports int) (*Switch, error) {
	if ports <= 0 {
		return nil, errors.New("number of ports must be greater than zero")
	}
	return &Switch{
		ID:              id,
		ForwardingTable: make(map[string]MACEntry),
		Ports:           ports,
	}, nil
}

// LearnMAC learns a MAC address on a specific port and updates the forwarding table.
func (s *Switch) LearnMAC(mac string, port int) error {
	if port <= 0 || port > s.Ports {
		return errors.New("invalid port number")
	}

	s.mu.Lock()
	defer s.mu.Unlock()

	entry := MACEntry{
		MACAddress: mac,
		Port:       port,
		Timestamp:  time.Now(),
	}
	s.ForwardingTable[mac] = entry
	fmt.Printf("Switch %s: Learned MAC %s on port %d\n", s.ID, mac, port)
	return nil
}

// ForwardPacket simulates forwarding a packet based on the destination MAC address.
func (s *Switch) ForwardPacket(srcMAC, destMAC string, inPort int) (int, error) {
	if inPort <= 0 || inPort > s.Ports {
		return 0, errors.New("invalid input port")
	}

	s.mu.RLock()
	defer s.mu.RUnlock()

	// Learn the source MAC
	if err := s.LearnMAC(srcMAC, inPort); err != nil {
		return 0, err
	}

	// Check if the destination MAC is in the forwarding table
	entry, exists := s.ForwardingTable[destMAC]
	if exists {
		fmt.Printf("Switch %s: Forwarding packet from %s to %s via port %d\n", s.ID, srcMAC, destMAC, entry.Port)
		return entry.Port, nil
	}

	// If destination MAC is not known, broadcast to all ports except the input port
	fmt.Printf("Switch %s: Broadcasting packet from %s (unknown destination %s)\n", s.ID, srcMAC, destMAC)
	return 0, nil
}

// RemoveStaleEntries removes stale MAC entries based on a given timeout duration.
func (s *Switch) RemoveStaleEntries(timeout time.Duration) {
	s.mu.Lock()
	defer s.mu.Unlock()

	for mac, entry := range s.ForwardingTable {
		if time.Since(entry.Timestamp) > timeout {
			fmt.Printf("Switch %s: Removed stale MAC entry %s\n", s.ID, mac)
			delete(s.ForwardingTable, mac)
		}
	}
}

// DisplayTable prints the current forwarding table.
func (s *Switch) DisplayTable() {
	s.mu.RLock()
	defer s.mu.RUnlock()

	fmt.Printf("Switch %s Forwarding Table:\n", s.ID)
	for mac, entry := range s.ForwardingTable {
		fmt.Printf("MAC: %s, Port: %d, Last Seen: %s\n", mac, entry.Port, entry.Timestamp)
	}
}
