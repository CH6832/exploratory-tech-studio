# -*- coding: utf-8 -*-
"""
product_model.py
----------------
This file contains the MongoDB product model, which represents the structure of product data stored in the database.
"""

from pymongo.collection import Collection
from database.db_config import get_db
from models import Product

# Get a reference to the product collection in the MongoDB database
product_collection: Collection = get_db()["products"]

def create_product(product: Product):
    """
    Insert a new product into the MongoDB database.
    
    Args:
        product (Product): The product object to insert into the database.
    """
    product_dict = product.dict()  # Convert the Pydantic model to a dictionary
    product_collection.insert_one(product_dict)

def get_product_by_id(product_id: str) -> Optional[Product]:
    """
    Retrieve a product by its ID from the MongoDB database.
    
    Args:
        product_id (str): The unique identifier of the product.
    
    Returns:
        Product: A Product Pydantic model if found, None otherwise.
    """
    product_data = product_collection.find_one({"product_id": product_id})
    
    if product_data:
        return Product(**product_data)
    return None
