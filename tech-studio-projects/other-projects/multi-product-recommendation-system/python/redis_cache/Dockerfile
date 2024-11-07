# Use the official Redis image from Docker Hub
FROM redis:latest

# Set environment variables (optional)
# Define the maximum memory limit for Redis
ENV REDIS_MAXMEMORY 256mb

# Expose the default Redis port (6379)
EXPOSE 6379

# Command to run Redis server
CMD ["redis-server", "--maxmemory", "256mb", "--maxmemory-policy", "allkeys-lru"]
