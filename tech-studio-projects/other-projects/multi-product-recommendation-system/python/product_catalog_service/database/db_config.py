# -*- coding: utf-8 -*-
"""
db_config.py
-------------
This file contains the MongoDB connection setup and configuration. 
It establishes the connection to the MongoDB database.
"""

from pymongo import MongoClient
from typing import Optional

# MongoDB connection URL
MONGODB_URL = "mongodb://localhost:27017"  # Example local MongoDB URL

# Initialize MongoDB client
client = MongoClient(MONGODB_URL)

# Connect to the 'product_catalog' database
db = client.product_catalog

def get_db() -> Optional:
    """
    Returns the database connection object.
    
    Returns:
        db: MongoDB database connection object
    """
    return db
