# ─────────────────────────────────────────────────────────────────────
# caching.py
# Shared caching logic to interact with Redis across services.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

"""
Shared caching logic to interact with Redis. 
Handles cache set, get, and deletion operations.
"""

import redis
from shared.config import Config
from typing import Optional

# Initialize Redis connection globally, can be reused across services
redis_client = redis.StrictRedis(
    host=Config.REDIS_HOST,
    port=Config.REDIS_PORT,
    db=Config.REDIS_DB
)

def get_cache(key: str) -> Optional[str]:
    """
    Retrieve an item from the Redis cache.
    
    Args:
        key (str): The cache key to look up in Redis.
    
    Returns:
        Optional[str]: Cached value if it exists, None otherwise.
    """
    value = redis_client.get(key)
    if value:
        return value.decode('utf-8')
    return None

def set_cache(key: str, value: str, ttl: int = 3600) -> bool:
    """
    Set an item in the Redis cache with an optional TTL.
    
    Args:
        key (str): The cache key.
        value (str): The value to store in Redis.
        ttl (int): Time-to-live (TTL) in seconds for the cache entry.
    
    Returns:
        bool: True if the cache was set successfully, False otherwise.
    """
    try:
        redis_client.setex(key, ttl, value)
        return True
    except redis.RedisError as e:
        return False

def delete_cache(key: str) -> bool:
    """
    Delete an item from the Redis cache.
    
    Args:
        key (str): The cache key to delete.
    
    Returns:
        bool: True if the cache entry was deleted, False otherwise.
    """
    try:
        redis_client.delete(key)
        return True
    except redis.RedisError as e:
        return False
