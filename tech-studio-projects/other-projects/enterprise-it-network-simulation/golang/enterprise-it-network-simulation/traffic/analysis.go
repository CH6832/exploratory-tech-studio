package traffic

import (
	"fmt"
)

// TrafficAnalysis represents the analysis of network traffic for a given simulation
type TrafficAnalysis struct {
	Flows []*Flow
}

// NewTrafficAnalysis initializes a new TrafficAnalysis instance
func NewTrafficAnalysis() *TrafficAnalysis {
	return &TrafficAnalysis{
		Flows: make([]*Flow, 0),
	}
}

// AddFlow adds a flow to the traffic analysis system
func (ta *TrafficAnalysis) AddFlow(flow *Flow) {
	ta.Flows = append(ta.Flows, flow)
}

// CalculateThroughput calculates the throughput of a specific flow (in bytes per second)
func (ta *TrafficAnalysis) CalculateThroughput(flow *Flow) float64 {
	// Ensure the flow is completed to calculate throughput
	if flow.Status != "completed" {
		return 0
	}

	duration := flow.GetFlowDuration().Seconds()
	if duration == 0 {
		return 0
	}

	// Throughput in bytes per second
	throughput := float64(flow.BytesSent) / duration
	return throughput
}

// CalculateLatency calculates the latency of a specific flow (in seconds)
func (ta *TrafficAnalysis) CalculateLatency(flow *Flow) float64 {
	// Ensure the flow is completed to calculate latency
	if flow.Status != "completed" {
		return 0
	}

	// Calculate latency based on start and end time of the flow
	latency := flow.EndTime.Sub(flow.StartTime).Seconds()
	return latency
}

// CalculatePacketLoss calculates the packet loss for the simulation (as a percentage)
func (ta *TrafficAnalysis) CalculatePacketLoss() float64 {
	totalFlows := len(ta.Flows)
	lostPackets := 0

	for _, flow := range ta.Flows {
		if flow.Status == "failed" {
			lostPackets++
		}
	}

	// Calculate the packet loss as a percentage
	if totalFlows == 0 {
		return 0
	}

	packetLoss := float64(lostPackets) / float64(totalFlows) * 100
	return packetLoss
}

// GenerateReport generates a comprehensive traffic analysis report
func (ta *TrafficAnalysis) GenerateReport() {
	var totalThroughput float64
	var totalLatency float64
	var completedFlows int

	for _, flow := range ta.Flows {
		if flow.Status == "completed" {
			// Calculate and accumulate throughput and latency
			totalThroughput += ta.CalculateThroughput(flow)
			totalLatency += ta.CalculateLatency(flow)
			completedFlows++
		}
	}

	// Calculate the average throughput and latency
	averageThroughput := totalThroughput / float64(completedFlows)
	averageLatency := totalLatency / float64(completedFlows)
	packetLoss := ta.CalculatePacketLoss()

	// Display the report
	fmt.Println("Network Traffic Analysis Report:")
	fmt.Printf("Total Completed Flows: %d\n", completedFlows)
	fmt.Printf("Average Throughput: %.2f bytes/sec\n", averageThroughput)
	fmt.Printf("Average Latency: %.2f seconds\n", averageLatency)
	fmt.Printf("Packet Loss: %.2f%%\n", packetLoss)
}

// DisplayFlowAnalysis displays detailed analysis for a single flow
func (ta *TrafficAnalysis) DisplayFlowAnalysis(flow *Flow) {
	throughput := ta.CalculateThroughput(flow)
	latency := ta.CalculateLatency(flow)

	fmt.Printf("Flow ID: %s\n", flow.ID)
	fmt.Printf("Source IP: %s\n", flow.SourceIP)
	fmt.Printf("Destination IP: %s\n", flow.DestinationIP)
	fmt.Printf("Protocol: %s\n", flow.Protocol)
	fmt.Printf("Payload: %s\n", flow.Payload)
	fmt.Printf("Status: %s\n", flow.Status)
	fmt.Printf("Bytes Sent: %d\n", flow.BytesSent)
	fmt.Printf("Bytes Received: %d\n", flow.BytesReceived)
	fmt.Printf("Latency: %.2f seconds\n", latency)
	fmt.Printf("Throughput: %.2f bytes/sec\n", throughput)
}
