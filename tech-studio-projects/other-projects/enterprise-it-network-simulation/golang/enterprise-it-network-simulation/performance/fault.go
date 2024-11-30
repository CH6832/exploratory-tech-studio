package performance

import (
	"errors"
	"fmt"
	"math/rand"
	"sync"
	"time"
)

// FaultType represents different types of network faults.
type FaultType string

const (
	PacketLoss    FaultType = "PacketLoss"
	Latency       FaultType = "Latency"
	DeviceFailure FaultType = "DeviceFailure"
)

// Fault represents a single fault injection configuration.
type Fault struct {
	ID        string        `json:"id"`
	Type      FaultType     `json:"type"`
	Impact    float64       `json:"impact"`   // Percentage for packet loss, ms for latency
	Duration  time.Duration `json:"duration"` // Duration of the fault
	StartTime time.Time     `json:"start_time"`
	Active    bool          `json:"active"`
}

// FaultManager manages the injection and resolution of network faults.
type FaultManager struct {
	faults map[string]*Fault
	mu     sync.Mutex
}

// NewFaultManager initializes a FaultManager.
func NewFaultManager() *FaultManager {
	return &FaultManager{
		faults: make(map[string]*Fault),
	}
}

// InjectFault injects a new fault into the system.
func (fm *FaultManager) InjectFault(faultType FaultType, impact float64, duration time.Duration) (string, error) {
	if impact <= 0 {
		return "", errors.New("impact must be greater than zero")
	}
	if duration <= 0 {
		return "", errors.New("duration must be greater than zero")
	}

	fm.mu.Lock()
	defer fm.mu.Unlock()

	id := fmt.Sprintf("%d", rand.Int63())
	fault := &Fault{
		ID:        id,
		Type:      faultType,
		Impact:    impact,
		Duration:  duration,
		StartTime: time.Now(),
		Active:    true,
	}
	fm.faults[id] = fault

	// Schedule automatic resolution
	go fm.resolveFaultAfterDuration(id, duration)

	fmt.Printf("Injected fault: %+v\n", fault)
	return id, nil
}

// GetFault retrieves details of a specific fault by ID.
func (fm *FaultManager) GetFault(id string) (*Fault, error) {
	fm.mu.Lock()
	defer fm.mu.Unlock()

	fault, exists := fm.faults[id]
	if !exists {
		return nil, errors.New("fault not found")
	}
	return fault, nil
}

// ListActiveFaults lists all currently active faults.
func (fm *FaultManager) ListActiveFaults() []Fault {
	fm.mu.Lock()
	defer fm.mu.Unlock()

	activeFaults := []Fault{}
	for _, fault := range fm.faults {
		if fault.Active {
			activeFaults = append(activeFaults, *fault)
		}
	}
	return activeFaults
}

// ResolveFault manually resolves a fault by ID.
func (fm *FaultManager) ResolveFault(id string) error {
	fm.mu.Lock()
	defer fm.mu.Unlock()

	fault, exists := fm.faults[id]
	if !exists {
		return errors.New("fault not found")
	}

	if !fault.Active {
		return errors.New("fault is already resolved")
	}

	fault.Active = false
	fmt.Printf("Resolved fault: %+v\n", fault)
	return nil
}

// resolveFaultAfterDuration resolves a fault automatically after its duration ends.
func (fm *FaultManager) resolveFaultAfterDuration(id string, duration time.Duration) {
	time.Sleep(duration)
	fm.ResolveFault(id) // Ignore errors as the fault may already be resolved manually
}

// ClearResolvedFaults clears all resolved faults from the system.
func (fm *FaultManager) ClearResolvedFaults() {
	fm.mu.Lock()
	defer fm.mu.Unlock()

	for id, fault := range fm.faults {
		if !fault.Active {
			delete(fm.faults, id)
		}
	}
	fmt.Println("Cleared resolved faults.")
}

// AnalyzeFaults provides a summary of all faults in the system.
func (fm *FaultManager) AnalyzeFaults() {
	fm.mu.Lock()
	defer fm.mu.Unlock()

	fmt.Printf("Fault Summary: Total=%d, Active=%d\n", len(fm.faults), len(fm.ListActiveFaults()))
	for _, fault := range fm.faults {
		fmt.Printf("Fault ID: %s, Type: %s, Active: %t, Impact: %.2f, Duration: %s\n",
			fault.ID, fault.Type, fault.Active, fault.Impact, fault.Duration)
	}
}
