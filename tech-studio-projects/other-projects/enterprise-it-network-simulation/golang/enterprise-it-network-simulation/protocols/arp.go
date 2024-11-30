package protocols

import (
	"errors"
	"fmt"
	"sync"
	"time"
)

// ARPEntry represents a single ARP cache entry.
type ARPEntry struct {
	IPAddress  string    `json:"ip_address"`
	MACAddress string    `json:"mac_address"`
	Timestamp  time.Time `json:"timestamp"`
}

// ARPCache stores a mapping of IP addresses to MAC addresses.
type ARPCache struct {
	entries map[string]ARPEntry
	mu      sync.RWMutex
}

// ARPHandler manages ARP requests, replies, and cache operations.
type ARPHandler struct {
	Cache *ARPCache
}

// NewARPCache initializes an empty ARPCache.
func NewARPCache() *ARPCache {
	return &ARPCache{
		entries: make(map[string]ARPEntry),
	}
}

// NewARPHandler initializes an ARPHandler with a cache.
func NewARPHandler() *ARPHandler {
	return &ARPHandler{
		Cache: NewARPCache(),
	}
}

// AddEntry adds an ARP entry to the cache.
func (a *ARPCache) AddEntry(ip, mac string) {
	a.mu.Lock()
	defer a.mu.Unlock()

	entry := ARPEntry{
		IPAddress:  ip,
		MACAddress: mac,
		Timestamp:  time.Now(),
	}
	a.entries[ip] = entry
	fmt.Printf("ARP entry added: %+v\n", entry)
}

// GetEntry retrieves a MAC address for a given IP from the ARP cache.
func (a *ARPCache) GetEntry(ip string) (ARPEntry, error) {
	a.mu.RLock()
	defer a.mu.RUnlock()

	entry, exists := a.entries[ip]
	if !exists {
		return ARPEntry{}, errors.New("ARP entry not found")
	}
	return entry, nil
}

// ResolveIP resolves an IP address to a MAC address, simulating an ARP request if necessary.
func (a *ARPHandler) ResolveIP(ip string) (string, error) {
	// Check cache first
	entry, err := a.Cache.GetEntry(ip)
	if err == nil {
		fmt.Printf("ARP cache hit: %+v\n", entry)
		return entry.MACAddress, nil
	}

	// Simulate ARP request (example simulation)
	mac := fmt.Sprintf("00:1A:2B:%02X:%02X:%02X", time.Now().Second(), time.Now().Minute(), time.Now().Hour())
	a.Cache.AddEntry(ip, mac)
	fmt.Printf("ARP request resolved: %s -> %s\n", ip, mac)
	return mac, nil
}

// AnalyzeCache provides a summary of ARP cache contents.
func (a *ARPHandler) AnalyzeCache() {
	a.Cache.mu.RLock()
	defer a.Cache.mu.RUnlock()

	fmt.Printf("ARP Cache Summary: %d entries\n", len(a.Cache.entries))
	for ip, entry := range a.Cache.entries {
		fmt.Printf("IP: %s, MAC: %s, Timestamp: %s\n", ip, entry.MACAddress, entry.Timestamp)
	}
}

// ClearCache clears all entries from the ARP cache.
func (a *ARPHandler) ClearCache() {
	a.Cache.mu.Lock()
	defer a.Cache.mu.Unlock()

	a.Cache.entries = make(map[string]ARPEntry)
	fmt.Println("ARP cache cleared.")
}
