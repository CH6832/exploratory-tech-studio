# -*- coding: utf-8 -*-
"""
logging_middleware.py
--------------------
This module defines middleware for logging incoming requests to the API Gateway.
Each incoming request is logged with relevant information such as timestamp,
IP address, and requested URL.
"""

import logging
from starlette.middleware.base import BaseHTTPMiddleware
from fastapi import Request

# Set up logging configuration
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(message)s')

class LoggingMiddleware(BaseHTTPMiddleware):
    """
    Middleware that logs the incoming HTTP requests.
    """
    
    async def dispatch(self, request: Request, call_next):
        """
        Logs details about the incoming request.
        
        Args:
            request (Request): The incoming HTTP request.
            call_next (callable): The next middleware or request handler.
        
        Returns:
            Response: The response after processing the request.
        """
        logging.info(f"Incoming request from {request.client.host} to {request.url.path}")
        
        # Process the request
        response = await call_next(request)
        return response
