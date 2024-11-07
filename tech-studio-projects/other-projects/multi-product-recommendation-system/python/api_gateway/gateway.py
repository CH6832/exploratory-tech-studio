# -*- coding: utf-8 -*-
"""
gateway.py
-----------
This file contains the main FastAPI application for the API Gateway.
The API Gateway handles all incoming requests, routing them to the appropriate 
microservice (Product Catalog or Recommendation Engine) and enforces rate-limiting.
"""

from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from fastapi.middleware.trustedhost import TrustedHostMiddleware
import logging
from routes import product_catalog, recommendation_engine
from middleware import rate_limiter, logging_middleware

# Create the FastAPI application
app = FastAPI(
    title="API Gateway for Multi-Product Recommendation System",
    description="This service routes requests to Product Catalog and Recommendation Engine services",
    version="1.0.0",
)

# Adding middleware to the app
app.add_middleware(TrustedHostMiddleware, allowed_hosts=["localhost", "127.0.0.1", "api.yourdomain.com"])
app.add_middleware(rate_limiter.RateLimitMiddleware)
app.add_middleware(logging_middleware.LoggingMiddleware)

# Register the routes for different services
app.include_router(product_catalog.router, prefix="/product", tags=["Product Catalog"])
app.include_router(recommendation_engine.router, prefix="/recommendation", tags=["Recommendation Engine"])

# Add a simple health check route
@app.get("/health")
async def health_check():
    """
    Health check endpoint to verify if the API Gateway is up and running.

    Returns:
        JSON response with a status of "OK"
    """
    return {"status": "OK"}
