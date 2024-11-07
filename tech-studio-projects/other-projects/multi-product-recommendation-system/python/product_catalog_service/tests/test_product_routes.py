# -*- coding: utf-8 -*-
"""
test_product_routes.py
-----------------------
This file contains unit tests for the product API routes defined in the product_routes module.
These tests check if the routes for creating and retrieving products work correctly.
"""

from fastapi.testclient import TestClient
from app import app

client = TestClient(app)

def test_create_product():
    """
    Test creating a new product through the /product endpoint.
    """
    product_data = {
        "product_id": "12345",
        "name": "Laptop",
        "description": "A high-end gaming laptop",
        "price": 1500.99,
        "stock": 30
    }
    
    response = client.post("/product", json=product_data)
    
    assert response.status_code == 200
    assert response.json()["product_id"] == "12345"
    assert response.json()["name"] == "Laptop"

def test_get_product():
    """
    Test retrieving a product by its product ID through the /product/{product_id} endpoint.
    """
    response = client.get("/product/12345")
    assert response.status_code == 200
    assert response.json()["product_id"] == "12345"
    assert response.json()["name"] == "Laptop"

def test_get_product_not_found():
    """
    Test retrieving a non-existent product (should return 404).
    """
    response = client.get("/product/99999")
    assert response.status_code == 404
