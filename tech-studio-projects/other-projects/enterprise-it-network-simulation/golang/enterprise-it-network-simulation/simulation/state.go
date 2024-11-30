package simulation

import "fmt"

// SimulationState holds the current state of the simulation
type SimulationState struct {
	Devices  []string
	Protocol string
	Running  bool
}

// NewSimulationState initializes the simulation state
func NewSimulationState() *SimulationState {
	return &SimulationState{
		Devices:  []string{},
		Protocol: "TCP",
		Running:  true,
	}
}

// AddDevice adds a device to the simulation
func (s *SimulationState) AddDevice(deviceName string) {
	s.Devices = append(s.Devices, deviceName)
}

// DisplayState shows the current state of the simulation
func (s *SimulationState) DisplayState() {
	fmt.Printf("Simulation State: %v\n", s)
}
