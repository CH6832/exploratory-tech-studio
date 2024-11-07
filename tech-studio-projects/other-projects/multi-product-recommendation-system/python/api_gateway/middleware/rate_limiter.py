# -*- coding: utf-8 -*-
"""
rate_limiter.py
---------------
This module contains a middleware that enforces rate limiting for incoming requests.
The rate limit is configurable and limits requests per minute per IP.
"""

from starlette.middleware.base import BaseHTTPMiddleware
from fastapi import Request
from time import time
from collections import defaultdict
from config import settings

# In-memory store for rate-limited IPs
ip_requests = defaultdict(list)

class RateLimitMiddleware(BaseHTTPMiddleware):
    """
    Middleware that applies rate limiting for requests to the API Gateway.
    """
    
    async def dispatch(self, request: Request, call_next):
        """
        Intercepts incoming requests and checks if they exceed the rate limit.
        
        Args:
            request (Request): The incoming HTTP request.
            call_next (callable): The next middleware or request handler.
        
        Returns:
            Response: The response after processing the request.
        """
        ip_address = request.client.host
        current_time = time()
        
        # Remove old requests from the in-memory store (older than 60 seconds)
        ip_requests[ip_address] = [t for t in ip_requests[ip_address] if current_time - t < 60]
        
        # Check if the IP address has exceeded the rate limit
        if len(ip_requests[ip_address]) >= settings.RATE_LIMIT:
            return HTTPException(status_code=429, detail="Rate limit exceeded")
        
        # Log the timestamp of the current request
        ip_requests[ip_address].append(current_time)
        
        # Process the request
        response = await call_next(request)
        return response
