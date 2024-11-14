// token_bucket.cpp
#include "token_bucket.hpp"
#include <chrono>
#include <mutex>
#include <thread>
#include <unordered_map>
#include <algorithm>

// TokenBucket constructor: Initialize rate, burst size, tokens, and last refill time
TokenBucket::TokenBucket(int rate, int burst_size)
    : rate(rate), burst_size(burst_size), tokens(burst_size), last_refill_time(std::chrono::steady_clock::now()) {}

// Check if a request can proceed
bool TokenBucket::allow() {
    std::lock_guard<std::mutex> lock(mtx); // Lock for thread safety

    // Get the current time and calculate the time passed since last refill
    auto now = std::chrono::steady_clock::now();
    std::chrono::duration<double> time_passed = now - last_refill_time;

    // Refill tokens based on the time passed and rate
    int new_tokens = static_cast<int>(time_passed.count() * rate);
    tokens = std::min(tokens + new_tokens, burst_size);  // Ensure tokens don't exceed burst size
    last_refill_time = now;

    // Check if there are tokens available
    if (tokens > 0) {
        tokens--;  // Decrease token count for this request
        return true;  // Allow the request
    }

    // No tokens available, deny the request (throttle)
    return false;
}
