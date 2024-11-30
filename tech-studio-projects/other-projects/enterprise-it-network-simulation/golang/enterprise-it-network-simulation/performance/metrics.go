package performance

import "fmt"

// Metrics tracks network performance
type Metrics struct {
	Latency    float64
	Throughput float64
}

// NewMetrics creates a new Metrics instance
func NewMetrics(latency float64, throughput float64) *Metrics {
	return &Metrics{
		Latency:    latency,
		Throughput: throughput,
	}
}

// Report generates a report of the network metrics
func (m *Metrics) Report() {
	fmt.Printf("Simulation Metrics - Latency: %.2f ms, Throughput: %.2f Mbps\n", m.Latency, m.Throughput)
}
