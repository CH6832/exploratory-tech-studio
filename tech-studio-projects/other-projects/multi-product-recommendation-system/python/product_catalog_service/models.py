# -*- coding: utf-8 -*-
"""
models.py
---------
This file contains the Pydantic models used for validating and serializing product data.
These models will be used to parse incoming requests and responses.
"""

from pydantic import BaseModel

class Product(BaseModel):
    """
    Pydantic model for a Product entity.
    
    This model is used for product data validation and serialization.
    """
    product_id: str
    name: str
    description: str
    price: float
    stock: int
