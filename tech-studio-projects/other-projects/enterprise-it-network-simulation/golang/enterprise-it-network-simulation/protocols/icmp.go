package protocols

import (
	"fmt"
	"math/rand"
	"time"
)

// ICMPType defines the type of ICMP message.
type ICMPType string

const (
	EchoRequest ICMPType = "EchoRequest" // Type 8
	EchoReply   ICMPType = "EchoReply"   // Type 0
)

// ICMPMessage represents a simulated ICMP message.
type ICMPMessage struct {
	Type           ICMPType  `json:"type"`
	SourceIP       string    `json:"source_ip"`
	DestinationIP  string    `json:"destination_ip"`
	SequenceNumber int       `json:"sequence_number"`
	Timestamp      time.Time `json:"timestamp"`
	Payload        string    `json:"payload"`
}

// ICMPHandler manages ICMP message generation and processing.
type ICMPHandler struct {
	Messages []ICMPMessage
}

// NewICMPHandler initializes a new ICMPHandler instance.
func NewICMPHandler() *ICMPHandler {
	return &ICMPHandler{
		Messages: []ICMPMessage{},
	}
}

// SendEchoRequest sends an ICMP Echo Request (ping).
func (i *ICMPHandler) SendEchoRequest(srcIP, destIP, payload string) ICMPMessage {
	message := ICMPMessage{
		Type:           EchoRequest,
		SourceIP:       srcIP,
		DestinationIP:  destIP,
		SequenceNumber: rand.Intn(1000),
		Timestamp:      time.Now(),
		Payload:        payload,
	}
	i.Messages = append(i.Messages, message)
	fmt.Printf("ICMP Echo Request sent: %+v\n", message)
	return message
}

// ReceiveEchoReply generates a simulated ICMP Echo Reply.
func (i *ICMPHandler) ReceiveEchoReply(request ICMPMessage) ICMPMessage {
	reply := ICMPMessage{
		Type:           EchoReply,
		SourceIP:       request.DestinationIP,
		DestinationIP:  request.SourceIP,
		SequenceNumber: request.SequenceNumber,
		Timestamp:      time.Now(),
		Payload:        request.Payload,
	}
	i.Messages = append(i.Messages, reply)
	fmt.Printf("ICMP Echo Reply received: %+v\n", reply)
	return reply
}

// AnalyzeTraffic provides a summary of ICMP messages sent and received.
func (i *ICMPHandler) AnalyzeTraffic() {
	fmt.Printf("Total ICMP Messages: %d\n", len(i.Messages))
	for _, message := range i.Messages {
		fmt.Printf("Message: %+v\n", message)
	}
}

// ClearMessages clears all stored ICMP messages.
func (i *ICMPHandler) ClearMessages() {
	i.Messages = []ICMPMessage{}
	fmt.Println("All ICMP messages cleared.")
}
