package main

import (
	"sync"
)

// ThreadPool struct representing a pool of worker goroutines.
type ThreadPool struct {
	tasks chan func() // Channel to store tasks
	wg    sync.WaitGroup
}

// NewThreadPool initializes a ThreadPool with a specified number of workers.
func NewThreadPool(workerCount int) *ThreadPool {
	pool := &ThreadPool{
		tasks: make(chan func()),
	}

	// Start the specified number of workers
	for i := 0; i < workerCount; i++ {
		go pool.worker()
	}

	return pool
}

// worker function for the worker goroutine to execute tasks from the task channel.
func (p *ThreadPool) worker() {
	for task := range p.tasks {
		task() // Execute the task
		p.wg.Done()
	}
}

// Submit adds a new task to the task queue.
func (p *ThreadPool) Submit(task func()) {
	p.wg.Add(1)     // Increment the wait group counter for each new task
	p.tasks <- task // Send task to the task channel
}

// Wait waits for all tasks to finish.
func (p *ThreadPool) Wait() {
	p.wg.Wait() // Wait until all tasks are done
}

// Shutdown gracefully stops the pool, ensuring all workers finish their tasks.
func (p *ThreadPool) Shutdown() {
	close(p.tasks) // Close the tasks channel to stop workers
}
