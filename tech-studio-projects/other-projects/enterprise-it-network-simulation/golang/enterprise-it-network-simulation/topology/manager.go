package topology

import (
	"errors"
	"fmt"
	"time"
)

// TopologyManager is responsible for managing the network's topology, including devices, links, and the overall simulation state.
type TopologyManager struct {
	Topology  *Topology
	SimState  string // Represents the state of the simulation (e.g., "stopped", "running", "paused")
	StartTime time.Time
}

// NewTopologyManager creates and initializes a new TopologyManager instance
func NewTopologyManager() *TopologyManager {
	return &TopologyManager{
		Topology: NewTopology(),
		SimState: "stopped", // Initial state of the simulation is stopped
	}
}

// StartSimulation starts the network simulation by setting the state to "running" and recording the start time.
func (tm *TopologyManager) StartSimulation() {
	if tm.SimState == "running" {
		fmt.Println("Simulation is already running.")
		return
	}

	tm.SimState = "running"
	tm.StartTime = time.Now()
	fmt.Println("Simulation started at", tm.StartTime)
}

// StopSimulation stops the network simulation by setting the state to "stopped".
func (tm *TopologyManager) StopSimulation() {
	if tm.SimState == "stopped" {
		fmt.Println("Simulation is already stopped.")
		return
	}

	tm.SimState = "stopped"
	fmt.Println("Simulation stopped.")
}

// PauseSimulation pauses the network simulation by setting the state to "paused".
func (tm *TopologyManager) PauseSimulation() {
	if tm.SimState == "paused" {
		fmt.Println("Simulation is already paused.")
		return
	}

	if tm.SimState == "stopped" {
		fmt.Println("Simulation is not running. Start the simulation first.")
		return
	}

	tm.SimState = "paused"
	fmt.Println("Simulation paused.")
}

// ResumeSimulation resumes the network simulation from a paused state by setting the state back to "running".
func (tm *TopologyManager) ResumeSimulation() {
	if tm.SimState == "running" {
		fmt.Println("Simulation is already running.")
		return
	}

	if tm.SimState == "stopped" {
		fmt.Println("Simulation is stopped. Start the simulation first.")
		return
	}

	tm.SimState = "running"
	fmt.Println("Simulation resumed.")
}

// AddDevice adds a new device to the network topology via the TopologyManager
func (tm *TopologyManager) AddDevice(id, ip, deviceType string) *Device {
	if tm.SimState != "stopped" {
		fmt.Println("Cannot modify devices during an active simulation.")
		return nil
	}
	return tm.Topology.AddDevice(id, ip, deviceType)
}

// RemoveDevice removes a device from the network topology
func (tm *TopologyManager) RemoveDevice(deviceID string) error {
	if tm.SimState != "stopped" {
		fmt.Println("Cannot modify devices during an active simulation.")
		return errors.New("simulation is running")
	}

	device, exists := tm.Topology.GetDevice(deviceID)
	if !exists {
		return errors.New("device not found")
	}

	// Remove all links associated with the device
	for _, link := range device.Links {
		tm.removeLinkFromDevices(link)
	}

	// Remove the device from the topology
	delete(tm.Topology.Devices, deviceID)
	fmt.Printf("Device %s removed.\n", deviceID)
	return nil
}

// AddLink adds a link between two devices in the network via the TopologyManager
func (tm *TopologyManager) AddLink(device1ID, device2ID string, speed int) error {
	if tm.SimState != "stopped" {
		fmt.Println("Cannot modify links during an active simulation.")
		return errors.New("simulation is running")
	}
	return tm.Topology.AddLink(device1ID, device2ID, speed)
}

// RemoveLink removes a link between two devices from the network
func (tm *TopologyManager) RemoveLink(device1ID, device2ID string) error {
	if tm.SimState != "stopped" {
		fmt.Println("Cannot modify links during an active simulation.")
		return errors.New("simulation is running")
	}

	device1, exists1 := tm.Topology.Devices[device1ID]
	device2, exists2 := tm.Topology.Devices[device2ID]

	if !exists1 || !exists2 {
		return errors.New("one or both devices do not exist")
	}

	// Find and remove the link from both devices' link lists
	var linkToRemove *Link
	for _, link := range device1.Links {
		if (link.Device1 == device1 && link.Device2 == device2) ||
			(link.Device1 == device2 && link.Device2 == device1) {
			linkToRemove = link
			break
		}
	}

	if linkToRemove == nil {
		return errors.New("link not found")
	}

	// Remove the link from both devices' link lists
	tm.removeLinkFromDevices(linkToRemove)

	// Remove the link from the topology's link list
	tm.removeLinkFromTopology(linkToRemove)
	fmt.Printf("Link between %s and %s removed.\n", device1ID, device2ID)
	return nil
}

// Helper method to remove a link from both devices' link lists
func (tm *TopologyManager) removeLinkFromDevices(link *Link) {
	for i, devLink := range link.Device1.Links {
		if devLink == link {
			link.Device1.Links = append(link.Device1.Links[:i], link.Device1.Links[i+1:]...)
			break
		}
	}

	for i, devLink := range link.Device2.Links {
		if devLink == link {
			link.Device2.Links = append(link.Device2.Links[:i], link.Device2.Links[i+1:]...)
			break
		}
	}
}

// Helper method to remove a link from the topology's link list
func (tm *TopologyManager) removeLinkFromTopology(link *Link) {
	for i, l := range tm.Topology.Links {
		if l == link {
			tm.Topology.Links = append(tm.Topology.Links[:i], tm.Topology.Links[i+1:]...)
			break
		}
	}
}

// DisplayNetworkStatus displays the current status of the network simulation, including devices and links
func (tm *TopologyManager) DisplayNetworkStatus() {
	fmt.Printf("Simulation State: %s\n", tm.SimState)
	tm.Topology.DisplayTopology()
}
