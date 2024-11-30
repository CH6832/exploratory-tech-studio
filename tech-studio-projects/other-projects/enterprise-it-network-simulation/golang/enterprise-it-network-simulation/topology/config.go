package topology

import (
	"encoding/json"
	"errors"
	"fmt"
	"os"
)

// Config holds the configuration settings for the network simulation.
type Config struct {
	DeviceDefaults   DeviceConfig     `json:"device_defaults"`
	LinkDefaults     LinkConfig       `json:"link_defaults"`
	SimulationParams SimulationConfig `json:"simulation_params"`
}

// DeviceConfig holds the default configurations for devices in the network.
type DeviceConfig struct {
	DefaultIP   string `json:"default_ip"`
	DefaultType string `json:"default_type"`
}

// LinkConfig holds the default configurations for links between devices.
type LinkConfig struct {
	DefaultSpeed int `json:"default_speed"` // in Mbps
}

// SimulationConfig holds simulation-wide parameters.
type SimulationConfig struct {
	Timeout int `json:"timeout"` // in seconds
}

// NewConfig creates a new Config instance with default values.
func NewConfig() *Config {
	return &Config{
		DeviceDefaults: DeviceConfig{
			DefaultIP:   "192.168.1.1",
			DefaultType: "Router",
		},
		LinkDefaults: LinkConfig{
			DefaultSpeed: 100, // Default link speed in Mbps
		},
		SimulationParams: SimulationConfig{
			Timeout: 3600, // Default timeout for the simulation in seconds
		},
	}
}

// LoadConfig loads the configuration from a file, with fallback to default values if needed.
func LoadConfig(filePath string) (*Config, error) {
	config := NewConfig() // Start with default config

	file, err := os.Open(filePath)
	if err != nil {
		return nil, fmt.Errorf("failed to open config file: %w", err)
	}
	defer file.Close()

	decoder := json.NewDecoder(file)
	if err := decoder.Decode(&config); err != nil {
		return nil, fmt.Errorf("failed to decode config file: %w", err)
	}

	return config, nil
}

// SaveConfig saves the current configuration to a specified file path.
func (cfg *Config) SaveConfig(filePath string) error {
	file, err := os.Create(filePath)
	if err != nil {
		return fmt.Errorf("failed to create config file: %w", err)
	}
	defer file.Close()

	encoder := json.NewEncoder(file)
	encoder.SetIndent("", "  ") // For better readability in JSON format

	if err := encoder.Encode(cfg); err != nil {
		return fmt.Errorf("failed to encode config: %w", err)
	}

	return nil
}

// GetDeviceDefaults returns the default configuration for devices.
func (cfg *Config) GetDeviceDefaults() DeviceConfig {
	return cfg.DeviceDefaults
}

// GetLinkDefaults returns the default configuration for links.
func (cfg *Config) GetLinkDefaults() LinkConfig {
	return cfg.LinkDefaults
}

// GetSimulationParams returns the simulation parameters.
func (cfg *Config) GetSimulationParams() SimulationConfig {
	return cfg.SimulationParams
}

// ValidateConfig validates the configuration to ensure it contains necessary values.
func (cfg *Config) ValidateConfig() error {
	if cfg.DeviceDefaults.DefaultIP == "" {
		return errors.New("device default IP is missing")
	}
	if cfg.DeviceDefaults.DefaultType == "" {
		return errors.New("device default type is missing")
	}
	if cfg.LinkDefaults.DefaultSpeed <= 0 {
		return errors.New("link default speed must be greater than zero")
	}
	if cfg.SimulationParams.Timeout <= 0 {
		return errors.New("simulation timeout must be greater than zero")
	}
	return nil
}
