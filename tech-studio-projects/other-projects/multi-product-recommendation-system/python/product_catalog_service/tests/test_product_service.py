# -*- coding: utf-8 -*-
"""
test_product_service.py
------------------------
This file contains unit tests for the business logic in the product_service module.
These tests ensure that the logic for adding and retrieving products works correctly.
"""

import pytest
from models import Product
from services.product_service import add_product_to_catalog, get_product_details
from database.product_model import create_product, get_product_by_id

# Mocking the database methods used in product_service.py
def mock_create_product(product: Product):
    """
    Mock function to simulate creating a product in the database.
    This will be used to replace the actual database calls during testing.
    """
    return product

def mock_get_product_by_id(product_id: str):
    """
    Mock function to simulate retrieving a product by its ID.
    This will be used to replace the actual database calls during testing.
    """
    if product_id == "12345":
        return Product(
            product_id="12345",
            name="Laptop",
            description="A high-end gaming laptop",
            price=1500.99,
            stock=30
        )
    return None

# Replacing the real database methods with the mocked ones
services.product_service.create_product = mock_create_product
services.product_service.get_product_by_id = mock_get_product_by_id

def test_add_product_to_catalog():
    """
    Test that the add_product_to_catalog function correctly adds a product.
    """
    product_data = Product(
        product_id="12345",
        name="Laptop",
        description="A high-end gaming laptop",
        price=1500.99,
        stock=30
    )
    
    result = add_product_to_catalog(product_data)
    
    # Test that the product returned by the service matches the input
    assert result.product_id == product_data.product_id
    assert result.name == product_data.name
    assert result.description == product_data.description
    assert result.price == product_data.price
    assert result.stock == product_data.stock

def test_get_product_details_found():
    """
    Test that the get_product_details function retrieves a product by ID correctly.
    """
    product_id = "12345"
    result = get_product_details(product_id)
    
    # Test that the correct product is returned
    assert result is not None
    assert result.product_id == "12345"
    assert result.name == "Laptop"
    assert result.price == 1500.99
    assert result.stock == 30

def test_get_product_details_not_found():
    """
    Test that the get_product_details function returns None if the product is not found.
    """
    product_id = "99999"
    result = get_product_details(product_id)
    
    # Test that None is returned if the product is not found
    assert result is None
