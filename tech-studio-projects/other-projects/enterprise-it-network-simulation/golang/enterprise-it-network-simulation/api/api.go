package api

import (
	"enterprise-it-network-simulation/devices"
	"enterprise-it-network-simulation/topology"
	"enterprise-it-network-simulation/traffic"
	"fmt"
	"log"
	"net/http"
	"os"
)

// APIConfig contains configuration for the API server.
type APIConfig struct {
	Port string
}

// NewAPIConfig initializes a default API configuration.
func NewAPIConfig() *APIConfig {
	// Defaults to port 8080 if not provided
	port := os.Getenv("API_PORT")
	if port == "" {
		port = "8080"
	}
	return &APIConfig{
		Port: port,
	}
}

// StartServer starts the API server and handles requests.
func StartServer(config *APIConfig, deviceManager *devices.DeviceManager, topologyManager *topology.TopologyManager, trafficGenerator *traffic.TrafficGenerator) {
	// Initialize the APIHandler
	apiHandler := NewAPIHandler(deviceManager, topologyManager, trafficGenerator)

	// Create HTTP multiplexer and register routes
	mux := http.NewServeMux()
	apiHandler.RegisterRoutes(mux)

	// Start the HTTP server
	serverAddr := fmt.Sprintf(":%s", config.Port)
	log.Printf("Starting API server on %s...\n", serverAddr)
	if err := http.ListenAndServe(serverAddr, mux); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}
