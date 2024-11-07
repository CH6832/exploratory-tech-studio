#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
test_profile_routes.py
----------------------
This file contains unit tests for the API routes in `profile_routes.py`.
It uses pytest and Flask-Testing to ensure correct functionality of the routes.
"""

import pytest
from app import app, db
from models import User

@pytest.fixture
def client():
    """
    Creates a test client to interact with the Flask app during testing.
    """
    with app.test_client() as client:
        yield client

@pytest.fixture
def init_db():
    """
    Initializes the database before each test.
    """
    db.create_all()
    yield db
    db.drop_all()

def test_create_user_profile(client, init_db):
    """
    Test creating a new user profile via the API.
    """
    response = client.post('/profile/', json={
        'name': 'John Doe',
        'email': 'john@example.com',
        'age': 30,
        'address': '123 Main St'
    })
    
    assert response.status_code == 201
    assert response.json['name'] == 'John Doe'

def test_get_user_profile(client, init_db):
    """
    Test fetching an existing user profile by ID.
    """
    user = User(name="Jane Doe", email="jane@example.com", age=25, address="456 Elm St")
    db.session.add(user)
    db.session.commit()
    
    response = client.get(f'/profile/{user.id}')
    
    assert response.status_code == 200
    assert response.json['name'] == 'Jane Doe'

def test_update_user_profile(client, init_db):
    """
    Test updating an existing user profile.
    """
    user = User(name="Alice", email="alice@example.com", age=22, address="789 Oak St")
    db.session.add(user)
    db.session.commit()
    
    response = client.put(f'/profile/{user.id}', json={'age': 23})
    
    assert response.status_code == 200
    assert response.json['age'] == 23

def test_delete_user_profile(client, init_db):
    """
    Test deleting a user profile by ID.
    """
    user = User(name="Bob", email="bob@example.com", age=28, address="321 Pine St")
    db.session.add(user)
    db.session.commit()
    
    response = client.delete(f'/profile/{user.id}')
    
    assert response.status_code == 200
    assert response.json['message'] == 'User profile deleted successfully'
