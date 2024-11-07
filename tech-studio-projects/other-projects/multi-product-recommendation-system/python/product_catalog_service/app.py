# -*- coding: utf-8 -*-
"""
app.py
------
This file contains the main FastAPI application for the Product Catalog Service.
It initializes the FastAPI application, connects the routes, and configures settings.
"""

from fastapi import FastAPI
from routes import product_routes

# Initialize the FastAPI application
app = FastAPI(
    title="Product Catalog Service",
    description="Service for managing and retrieving product data.",
    version="1.0.0"
)

# Include the product routes from the product_routes module
app.include_router(product_routes.router)
