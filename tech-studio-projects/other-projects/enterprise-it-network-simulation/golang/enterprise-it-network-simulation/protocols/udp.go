package protocols

import (
	"fmt"
	"math/rand"
	"time"
)

// UDPPacket represents a simulated UDP packet.
type UDPPacket struct {
	SourceIP        string    `json:"source_ip"`
	DestinationIP   string    `json:"destination_ip"`
	SourcePort      int       `json:"source_port"`
	DestinationPort int       `json:"destination_port"`
	Payload         string    `json:"payload"`
	Timestamp       time.Time `json:"timestamp"`
}

// UDPHandler manages the sending and receiving of UDP packets.
type UDPHandler struct {
	Packets []UDPPacket // Store packets for analysis in the simulation
}

// NewUDPHandler creates and initializes a new UDPHandler instance.
func NewUDPHandler() *UDPHandler {
	return &UDPHandler{
		Packets: []UDPPacket{},
	}
}

// CreatePacket generates a new UDP packet with the given parameters.
func (u *UDPHandler) CreatePacket(srcIP, destIP string, srcPort, destPort int, payload string) UDPPacket {
	packet := UDPPacket{
		SourceIP:        srcIP,
		DestinationIP:   destIP,
		SourcePort:      srcPort,
		DestinationPort: destPort,
		Payload:         payload,
		Timestamp:       time.Now(),
	}
	return packet
}

// SendPacket simulates sending a UDP packet by adding it to the handler's packet list.
func (u *UDPHandler) SendPacket(packet UDPPacket) {
	u.Packets = append(u.Packets, packet)
	fmt.Printf("Sent UDP Packet: %+v\n", packet)
}

// ReceivePacket simulates receiving a UDP packet. In a real simulation, this could process incoming packets.
func (u *UDPHandler) ReceivePacket(packet UDPPacket) {
	u.Packets = append(u.Packets, packet)
	fmt.Printf("Received UDP Packet: %+v\n", packet)
}

// SimulateLoss simulates packet loss in the UDP protocol.
func (u *UDPHandler) SimulateLoss(lossPercentage float64) {
	var retainedPackets []UDPPacket
	for _, packet := range u.Packets {
		if rand.Float64()*100 > lossPercentage {
			retainedPackets = append(retainedPackets, packet)
		} else {
			fmt.Printf("Packet lost: %+v\n", packet)
		}
	}
	u.Packets = retainedPackets
}

// AnalyzeTraffic provides a summary of UDP traffic in the simulation.
func (u *UDPHandler) AnalyzeTraffic() {
	fmt.Printf("Total UDP Packets: %d\n", len(u.Packets))
	sourceMap := make(map[string]int)
	destMap := make(map[string]int)

	for _, packet := range u.Packets {
		sourceMap[packet.SourceIP]++
		destMap[packet.DestinationIP]++
	}

	fmt.Println("Traffic by Source IP:")
	for ip, count := range sourceMap {
		fmt.Printf("%s: %d packets\n", ip, count)
	}

	fmt.Println("Traffic by Destination IP:")
	for ip, count := range destMap {
		fmt.Printf("%s: %d packets\n", ip, count)
	}
}

// ClearPackets clears all stored UDP packets.
func (u *UDPHandler) ClearPackets() {
	u.Packets = []UDPPacket{}
	fmt.Println("All UDP packets cleared.")
}
