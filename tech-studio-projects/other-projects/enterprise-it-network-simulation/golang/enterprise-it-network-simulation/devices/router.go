package devices

import "fmt"

// Router represents a network router in the simulation
type Router struct {
	Name        string
	IP          string
	Interfaces  []string
	ConnectedTo []string
}

// NewRouter creates a new Router instance
func NewRouter(name, ip string) *Router {
	return &Router{
		Name:        name,
		IP:          ip,
		Interfaces:  []string{},
		ConnectedTo: []string{},
	}
}

// AddInterface adds an interface to the router
func (r *Router) AddInterface(interfaceName string) {
	r.Interfaces = append(r.Interfaces, interfaceName)
}

// RoutePacket simulates packet routing
func (r *Router) RoutePacket(destinationIP string) {
	fmt.Printf("Router %s routing packet to %s\n", r.Name, destinationIP)
}
