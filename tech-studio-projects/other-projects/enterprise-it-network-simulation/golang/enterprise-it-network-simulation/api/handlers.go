package api

import (
	"encoding/json"
	"fmt"
	"it-network-simulation/internal/devices"
	"it-network-simulation/internal/topology"
	"it-network-simulation/internal/traffic"
	"net/http"
)

// APIHandler is the central HTTP handler for the simulation system.
type APIHandler struct {
	DeviceManager    *devices.DeviceManager
	TopologyManager  *topology.TopologyManager
	TrafficGenerator *traffic.TrafficGenerator
}

// NewAPIHandler initializes a new APIHandler.
func NewAPIHandler(deviceManager *devices.DeviceManager, topologyManager *topology.TopologyManager, trafficGenerator *traffic.TrafficGenerator) *APIHandler {
	return &APIHandler{
		DeviceManager:    deviceManager,
		TopologyManager:  topologyManager,
		TrafficGenerator: trafficGenerator,
	}
}

// RegisterRoutes registers all API routes to a HTTP mux.
func (h *APIHandler) RegisterRoutes(mux *http.ServeMux) {
	mux.HandleFunc("/devices", h.handleDevices)
	mux.HandleFunc("/topology", h.handleTopology)
	mux.HandleFunc("/traffic", h.handleTraffic)
	mux.HandleFunc("/simulation/results", h.handleSimulationResults)
}

// handleDevices manages requests for devices (GET, POST).
func (h *APIHandler) handleDevices(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case http.MethodGet:
		h.getDevices(w, r)
	case http.MethodPost:
		h.createDevice(w, r)
	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

// getDevices returns the list of devices in the simulation.
func (h *APIHandler) getDevices(w http.ResponseWriter, r *http.Request) {
	devices := h.DeviceManager.GetAllDevices()
	jsonResponse(w, devices, http.StatusOK)
}

// createDevice adds a new device to the simulation.
func (h *APIHandler) createDevice(w http.ResponseWriter, r *http.Request) {
	var device devices.Device
	if err := json.NewDecoder(r.Body).Decode(&device); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}

	if err := h.DeviceManager.AddDevice(&device); err != nil {
		http.Error(w, fmt.Sprintf("Error adding device: %v", err), http.StatusInternalServerError)
		return
	}

	jsonResponse(w, device, http.StatusCreated)
}

// handleTopology manages requests for topology updates (GET, POST).
func (h *APIHandler) handleTopology(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case http.MethodGet:
		h.getTopology(w, r)
	case http.MethodPost:
		h.updateTopology(w, r)
	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

// getTopology returns the current topology configuration.
func (h *APIHandler) getTopology(w http.ResponseWriter, r *http.Request) {
	topology := h.TopologyManager.GetTopology()
	jsonResponse(w, topology, http.StatusOK)
}

// updateTopology updates the topology configuration.
func (h *APIHandler) updateTopology(w http.ResponseWriter, r *http.Request) {
	var config topology.Config
	if err := json.NewDecoder(r.Body).Decode(&config); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}

	if err := h.TopologyManager.UpdateTopology(&config); err != nil {
		http.Error(w, fmt.Sprintf("Error updating topology: %v", err), http.StatusInternalServerError)
		return
	}

	jsonResponse(w, config, http.StatusOK)
}

// handleTraffic generates or analyzes traffic in the simulation.
func (h *APIHandler) handleTraffic(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case http.MethodPost:
		h.generateTraffic(w, r)
	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

// generateTraffic starts traffic generation in the simulation.
func (h *APIHandler) generateTraffic(w http.ResponseWriter, r *http.Request) {
	var request traffic.GenerationRequest
	if err := json.NewDecoder(r.Body).Decode(&request); err != nil {
		http.Error(w, "Invalid request payload", http.StatusBadRequest)
		return
	}

	results, err := h.TrafficGenerator.GenerateTraffic(&request)
	if err != nil {
		http.Error(w, fmt.Sprintf("Error generating traffic: %v", err), http.StatusInternalServerError)
		return
	}

	jsonResponse(w, results, http.StatusOK)
}

// handleSimulationResults fetches simulation results (GET).
func (h *APIHandler) handleSimulationResults(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodGet {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}

	results := h.DeviceManager.GetSimulationResults()
	jsonResponse(w, results, http.StatusOK)
}

// jsonResponse sends a JSON response to the client.
func jsonResponse(w http.ResponseWriter, data interface{}, status int) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(status)
	if err := json.NewEncoder(w).Encode(data); err != nil {
		http.Error(w, "Failed to encode JSON response", http.StatusInternalServerError)
	}
}
