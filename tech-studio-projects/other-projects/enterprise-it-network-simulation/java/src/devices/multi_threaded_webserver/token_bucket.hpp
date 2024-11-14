#include <chrono>
#include <mutex>
#include <thread>
#include <unordered_map>

class TokenBucket {
public:
    TokenBucket(int rate, int burst_size)
        : rate(rate), burst_size(burst_size), tokens(burst_size), last_refill_time(std::chrono::steady_clock::now()) {}

    // Check if a request can proceed
    bool allow() {
        std::lock_guard<std::mutex> lock(mtx);

        auto now = std::chrono::steady_clock::now();
        std::chrono::duration<double> time_passed = now - last_refill_time;

        // Refill tokens based on time passed
        int new_tokens = static_cast<int>(time_passed.count() * rate);
        tokens = std::min(tokens + new_tokens, burst_size);
        last_refill_time = now;

        // If we have enough tokens, allow the request
        if (tokens > 0) {
            tokens--;
            return true;
        }

        // No tokens available, throttle the request
        return false;
    }

private:
    int rate;  // Token generation rate per second
    int burst_size;  // Maximum number of tokens
    int tokens;  // Current available tokens
    std::chrono::steady_clock::time_point last_refill_time;  // Last time tokens were refilled
    std::mutex mtx;  // To protect shared data
};