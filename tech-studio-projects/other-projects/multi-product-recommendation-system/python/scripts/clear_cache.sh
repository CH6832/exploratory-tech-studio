#!/bin/bash
# ─────────────────────────────────────────────────────────────────────
# clear_cache.sh
# Script to clear the Redis cache.
# This is useful for clearing the cache during testing or debugging.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

# Print message about clearing cache
echo "Clearing Redis cache..."

# Use redis-cli to flush the entire Redis database
docker-compose exec redis redis-cli FLUSHALL

# Check if the cache was cleared successfully
if [ $? -eq 0 ]; then
    echo "Redis cache cleared successfully!"
else
    echo "Failed to clear Redis cache."
    exit 1
fi
