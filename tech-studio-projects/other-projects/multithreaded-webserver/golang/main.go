package main

import (
	"fmt"
	"time"
)

func main() {
	server := NewServer(8088)
	// Create a thread pool with 4 workers
	pool := NewThreadPool(4)

	// Submit 10 tasks to the pool
	for i := 0; i < 10; i++ {
		taskNumber := i
		pool.Submit(func() {
			fmt.Printf("Executing task %d\n", taskNumber)
			time.Sleep(1 * time.Second) // Simulate work
		})
	}

	// Wait for all tasks to complete
	pool.Wait()

	// Shutdown the pool after all tasks are done
	pool.Shutdown()

	fmt.Println("All tasks completed and pool shut down.")
	// Create a new token bucket: 5 tokens per second with a burst size of 10
	bucket := NewTokenBucket(5, 10)

	// Simulate incoming requests and check if they are allowed
	for i := 0; i < 20; i++ {
		if bucket.Allow() {
			fmt.Printf("Request %d: Allowed\n", i+1)
		} else {
			fmt.Printf("Request %d: Throttled\n", i+1)
		}
		time.Sleep(200 * time.Millisecond) // Simulate time delay between requests
	}
	server.Start()
}
