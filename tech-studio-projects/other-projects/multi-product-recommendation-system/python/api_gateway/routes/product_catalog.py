# -*- coding: utf-8 -*-
"""
product_catalog.py
------------------
This module defines the routes for interacting with the Product Catalog Service.
These routes will forward requests to the corresponding Product Catalog microservice.
"""

from fastapi import APIRouter, HTTPException
import requests
from config import settings

router = APIRouter()

@router.get("/{product_id}")
async def get_product(product_id: str):
    """
    Route to retrieve product details by product ID.
    This route forwards the request to the Product Catalog microservice.
    
    Args:
        product_id (str): The ID of the product to retrieve.
    
    Returns:
        dict: The product details retrieved from the Product Catalog service.
    
    Raises:
        HTTPException: If the product does not exist in the catalog.
    """
    try:
        # Forwarding the request to the Product Catalog service
        product_service_url = f"http://product-catalog-service/product/{product_id}"
        response = requests.get(product_service_url, timeout=settings.SERVICE_TIMEOUT)
        
        if response.status_code != 200:
            raise HTTPException(status_code=response.status_code, detail="Product not found")
        
        return response.json()
    
    except requests.exceptions.Timeout:
        raise HTTPException(status_code=504, detail="Service timeout")
    except requests.exceptions.RequestException as e:
        raise HTTPException(status_code=500, detail=f"Request failed: {e}")
