package main

import (
	"math"
	"sync"
	"time"
)

// TokenBucket struct defines the rate limiter with burst and refill logic
type TokenBucket struct {
	rate       int        // Rate of token refill per second
	burstSize  int        // Maximum burst size
	tokens     int        // Current available tokens
	lastRefill time.Time  // Last time the bucket was refilled
	mtx        sync.Mutex // Mutex for thread safety
}

// NewTokenBucket initializes the token bucket with a given rate and burst size
func NewTokenBucket(rate, burstSize int) *TokenBucket {
	return &TokenBucket{
		rate:       rate,
		burstSize:  burstSize,
		tokens:     burstSize,  // Start with a full bucket
		lastRefill: time.Now(), // Initial last refill time is the current time
	}
}

// Allow checks if a request can proceed based on available tokens
func (tb *TokenBucket) Allow() bool {
	tb.mtx.Lock() // Lock for thread safety
	defer tb.mtx.Unlock()

	now := time.Now()                              // Get current time
	timePassed := now.Sub(tb.lastRefill).Seconds() // Calculate time passed in seconds

	// Refill tokens based on time passed and rate
	newTokens := int(timePassed * float64(tb.rate))                                // Refill tokens
	tb.tokens = int(math.Min(float64(tb.tokens+newTokens), float64(tb.burstSize))) // Ensure tokens don't exceed burst size
	tb.lastRefill = now                                                            // Update last refill time to current time

	// Check if tokens are available
	if tb.tokens > 0 {
		tb.tokens-- // Decrease token count for this request
		return true // Allow the request
	}

	// No tokens available, deny the request (throttle)
	return false
}
