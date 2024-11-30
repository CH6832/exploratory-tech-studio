package protocols

import (
	"errors"
	"fmt"
	"math/rand"
	"sync"
	"time"
)

// DNSRecord represents a simulated DNS record.
type DNSRecord struct {
	Domain     string    `json:"domain"`
	IP         string    `json:"ip"`
	RecordType string    `json:"record_type"` // A, AAAA, PTR, etc.
	TTL        int       `json:"ttl"`
	Created    time.Time `json:"created"`
}

// DNSCache stores DNS records for fast lookups.
type DNSCache struct {
	records map[string]DNSRecord
	mu      sync.RWMutex
}

// DNSHandler manages DNS queries and caching.
type DNSHandler struct {
	Cache *DNSCache
}

// NewDNSCache initializes an empty DNSCache.
func NewDNSCache() *DNSCache {
	return &DNSCache{
		records: make(map[string]DNSRecord),
	}
}

// NewDNSHandler initializes a DNSHandler with a cache.
func NewDNSHandler() *DNSHandler {
	return &DNSHandler{
		Cache: NewDNSCache(),
	}
}

// AddRecord adds a DNS record to the cache.
func (d *DNSCache) AddRecord(domain, ip, recordType string, ttl int) {
	d.mu.Lock()
	defer d.mu.Unlock()

	record := DNSRecord{
		Domain:     domain,
		IP:         ip,
		RecordType: recordType,
		TTL:        ttl,
		Created:    time.Now(),
	}
	d.records[domain] = record
	fmt.Printf("DNS record added: %+v\n", record)
}

// GetRecord retrieves a DNS record from the cache.
func (d *DNSCache) GetRecord(domain string) (DNSRecord, error) {
	d.mu.RLock()
	defer d.mu.RUnlock()

	record, exists := d.records[domain]
	if !exists {
		return DNSRecord{}, errors.New("record not found")
	}

	// Check if the record is still valid based on TTL
	if time.Since(record.Created).Seconds() > float64(record.TTL) {
		delete(d.records, domain)
		return DNSRecord{}, errors.New("record expired")
	}
	return record, nil
}

// ResolveDomain resolves a domain name to an IP address using the cache or simulating a query.
func (d *DNSHandler) ResolveDomain(domain string) (string, error) {
	// Check cache first
	record, err := d.Cache.GetRecord(domain)
	if err == nil {
		fmt.Printf("DNS cache hit: %+v\n", record)
		return record.IP, nil
	}

	// Simulate DNS query (example simulation)
	ip := fmt.Sprintf("192.168.%d.%d", rand.Intn(255), rand.Intn(255))
	d.Cache.AddRecord(domain, ip, "A", 60) // TTL: 60 seconds
	fmt.Printf("DNS query resolved: %s -> %s\n", domain, ip)
	return ip, nil
}

// ResolveIP performs a reverse DNS lookup to find a domain for an IP address.
func (d *DNSHandler) ResolveIP(ip string) (string, error) {
	// Simulate reverse lookup in cache
	for _, record := range d.Cache.records {
		if record.IP == ip {
			fmt.Printf("Reverse DNS cache hit: %+v\n", record)
			return record.Domain, nil
		}
	}

	// Simulate reverse DNS query (example simulation)
	domain := fmt.Sprintf("host-%s.local", ip)
	d.Cache.AddRecord(domain, ip, "PTR", 60) // TTL: 60 seconds
	fmt.Printf("Reverse DNS query resolved: %s -> %s\n", ip, domain)
	return domain, nil
}

// AnalyzeCache provides a summary of DNS cache contents.
func (d *DNSHandler) AnalyzeCache() {
	d.Cache.mu.RLock()
	defer d.Cache.mu.RUnlock()

	fmt.Printf("DNS Cache Summary: %d records\n", len(d.Cache.records))
	for domain, record := range d.Cache.records {
		fmt.Printf("Domain: %s, Record: %+v\n", domain, record)
	}
}

// ClearCache clears all records from the DNS cache.
func (d *DNSHandler) ClearCache() {
	d.Cache.mu.Lock()
	defer d.Cache.mu.Unlock()

	d.Cache.records = make(map[string]DNSRecord)
	fmt.Println("DNS cache cleared.")
}
