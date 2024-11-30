package topology

import (
	"errors"
	"fmt"
)

// Device represents a generic network device (router, switch, endpoint, etc.)
type Device struct {
	ID    string
	IP    string
	Type  string // Router, Switch, Endpoint, etc.
	Links []*Link
}

// Link represents a link between two devices in the network
type Link struct {
	Device1   *Device
	Device2   *Device
	LinkSpeed int // Speed of the link in Mbps
}

// Topology represents the overall network topology
type Topology struct {
	Devices map[string]*Device
	Links   []*Link
}

// NewTopology initializes a new empty network topology
func NewTopology() *Topology {
	return &Topology{
		Devices: make(map[string]*Device),
		Links:   make([]*Link, 0),
	}
}

// AddDevice adds a new device to the network topology
func (t *Topology) AddDevice(id, ip, deviceType string) *Device {
	device := &Device{
		ID:    id,
		IP:    ip,
		Type:  deviceType,
		Links: make([]*Link, 0),
	}
	t.Devices[id] = device
	fmt.Printf("Device %s added: %s (%s)\n", id, ip, deviceType)
	return device
}

// AddLink adds a link between two devices in the network
func (t *Topology) AddLink(device1ID, device2ID string, speed int) error {
	device1, exists1 := t.Devices[device1ID]
	device2, exists2 := t.Devices[device2ID]

	if !exists1 || !exists2 {
		return errors.New("one or both devices do not exist")
	}

	link := &Link{
		Device1:   device1,
		Device2:   device2,
		LinkSpeed: speed,
	}

	// Add the link to both devices' link list
	device1.Links = append(device1.Links, link)
	device2.Links = append(device2.Links, link)

	// Add the link to the topology's link list
	t.Links = append(t.Links, link)

	fmt.Printf("Link added between %s and %s with speed %d Mbps\n", device1ID, device2ID, speed)
	return nil
}

// GetDevice retrieves a device by its ID
func (t *Topology) GetDevice(id string) (*Device, bool) {
	device, exists := t.Devices[id]
	return device, exists
}

// DisplayTopology displays the entire network topology, including devices and links
func (t *Topology) DisplayTopology() {
	fmt.Println("Network Topology:")
	for _, device := range t.Devices {
		fmt.Printf("Device ID: %s, IP: %s, Type: %s\n", device.ID, device.IP, device.Type)
		for _, link := range device.Links {
			otherDevice := link.Device1
			if otherDevice.ID == device.ID {
				otherDevice = link.Device2
			}
			fmt.Printf("  Connected to %s (Link Speed: %d Mbps)\n", otherDevice.ID, link.LinkSpeed)
		}
	}
}

// DisplayDeviceLinks displays the links of a particular device
func (t *Topology) DisplayDeviceLinks(deviceID string) {
	device, exists := t.Devices[deviceID]
	if !exists {
		fmt.Printf("Device with ID %s not found.\n", deviceID)
		return
	}
	fmt.Printf("Links for Device %s (%s):\n", device.ID, device.IP)
	for _, link := range device.Links {
		otherDevice := link.Device1
		if otherDevice.ID == device.ID {
			otherDevice = link.Device2
		}
		fmt.Printf("  - Connected to %s (Link Speed: %d Mbps)\n", otherDevice.ID, link.LinkSpeed)
	}
}
