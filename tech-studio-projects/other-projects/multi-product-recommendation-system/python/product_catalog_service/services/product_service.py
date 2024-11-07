# -*- coding: utf-8 -*-
"""
product_service.py
------------------
This file contains the business logic for handling products in the catalog.
It includes functionality for creating and retrieving products.
"""

from models import Product
from database.product_model import create_product, get_product_by_id
from typing import Optional

def add_product_to_catalog(product: Product):
    """
    Add a new product to the product catalog.

    Args:
        product (Product): The product to add to the catalog.
    """
    create_product(product)

def get_product_details(product_id: str) -> Optional[Product]:
    """
    Retrieve the details of a product by its product ID.
    
    Args:
        product_id (str): The unique identifier of the product to retrieve.
    
    Returns:
        Product: A Product model object if found, None otherwise.
    """
    return get_product_by_id(product_id)
