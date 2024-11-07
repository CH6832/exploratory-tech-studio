# -*- coding: utf-8 -*-
"""
product_routes.py
-----------------
This file contains the API routes for handling product-related requests.
It includes endpoints for creating and retrieving products.
"""

from fastapi import APIRouter, HTTPException
from models import Product
from services.product_service import add_product_to_catalog, get_product_details

# Initialize the FastAPI router
router = APIRouter()

@router.post("/product", response_model=Product)
async def create_product(product: Product):
    """
    API endpoint to create a new product in the catalog.
    
    Args:
        product (Product): The product data to add to the catalog.
    
    Returns:
        Product: The created product.
    
    Raises:
        HTTPException: If the product cannot be created.
    """
    try:
        add_product_to_catalog(product)
        return product
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error creating product: {e}")

@router.get("/product/{product_id}", response_model=Product)
async def get_product(product_id: str):
    """
    API endpoint to retrieve a product by its product ID.
    
    Args:
        product_id (str): The product ID to search for.
    
    Returns:
        Product: The product details.
    
    Raises:
        HTTPException: If the product is not found.
    """
    product = get_product_details(product_id)
    
    if product:
        return product
    else:
        raise HTTPException(status_code=404, detail="Product not found")
