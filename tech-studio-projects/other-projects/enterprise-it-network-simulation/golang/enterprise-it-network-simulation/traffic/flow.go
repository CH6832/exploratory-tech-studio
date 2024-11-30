package traffic

import (
	"fmt"
	"time"
)

// Flow represents a data flow in the network between a source and destination
type Flow struct {
	ID            string
	SourceIP      string
	DestinationIP string
	Protocol      string
	Payload       string
	BytesSent     int
	BytesReceived int
	Status        string // "in-progress", "completed", "failed"
	StartTime     time.Time
	EndTime       time.Time
}

// NewFlow initializes a new Flow object
func NewFlow(id, sourceIP, destinationIP, protocol, payload string) *Flow {
	return &Flow{
		ID:            id,
		SourceIP:      sourceIP,
		DestinationIP: destinationIP,
		Protocol:      protocol,
		Payload:       payload,
		BytesSent:     0,
		BytesReceived: 0,
		Status:        "in-progress",
		StartTime:     time.Now(),
	}
}

// Send simulates sending data in the flow
func (f *Flow) SendData(bytes int) {
	f.BytesSent += bytes
	fmt.Printf("Flow %s: Sent %d bytes from %s to %s\n", f.ID, bytes, f.SourceIP, f.DestinationIP)
}

// Receive simulates receiving data in the flow
func (f *Flow) ReceiveData(bytes int) {
	f.BytesReceived += bytes
	fmt.Printf("Flow %s: Received %d bytes from %s to %s\n", f.ID, bytes, f.SourceIP, f.DestinationIP)
}

// Complete marks the flow as completed and records the end time
func (f *Flow) Complete() {
	f.Status = "completed"
	f.EndTime = time.Now()
	fmt.Printf("Flow %s: Data transfer complete. Total Bytes Sent: %d, Bytes Received: %d\n", f.ID, f.BytesSent, f.BytesReceived)
}

// Fail marks the flow as failed
func (f *Flow) Fail() {
	f.Status = "failed"
	f.EndTime = time.Now()
	fmt.Printf("Flow %s: Data transfer failed. Bytes Sent: %d, Bytes Received: %d\n", f.ID, f.BytesSent, f.BytesReceived)
}

// DisplayFlowStatus shows the status and statistics of the flow
func (f *Flow) DisplayFlowStatus() {
	fmt.Printf("Flow ID: %s\n", f.ID)
	fmt.Printf("Source IP: %s\n", f.SourceIP)
	fmt.Printf("Destination IP: %s\n", f.DestinationIP)
	fmt.Printf("Protocol: %s\n", f.Protocol)
	fmt.Printf("Payload: %s\n", f.Payload)
	fmt.Printf("Bytes Sent: %d\n", f.BytesSent)
	fmt.Printf("Bytes Received: %d\n", f.BytesReceived)
	fmt.Printf("Status: %s\n", f.Status)
	fmt.Printf("Start Time: %s\n", f.StartTime.Format(time.RFC3339))
	if !f.EndTime.IsZero() {
		fmt.Printf("End Time: %s\n", f.EndTime.Format(time.RFC3339))
	} else {
		fmt.Println("End Time: N/A")
	}
}

// GetFlowDuration calculates the duration of the flow (if completed)
func (f *Flow) GetFlowDuration() time.Duration {
	if f.Status == "completed" {
		return f.EndTime.Sub(f.StartTime)
	}
	return 0
}
