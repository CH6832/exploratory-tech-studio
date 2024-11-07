#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
test_profile_service.py
------------------------
This file contains unit tests for the business logic of the User Profile Service.
It tests the logic for creating, updating, and deleting user profiles.
"""

import pytest
from app import db
from models import User
from sqlalchemy.exc import IntegrityError

@pytest.fixture
def init_db():
    """
    Initializes the database before each test and drops it after.
    """
    db.create_all()
    yield db
    db.drop_all()

def test_create_user(init_db):
    """
    Test creating a user in the database through the business logic.
    Ensures that a new user is correctly created and committed.
    """
    user_data = {
        'name': 'John Doe',
        'email': 'john@example.com',
        'age': 30,
        'address': '123 Main St'
    }

    # Create a user instance using the business logic
    user = User(**user_data)
    db.session.add(user)
    db.session.commit()

    # Fetch the user from the database and verify the creation
    created_user = User.query.filter_by(email='john@example.com').first()
    assert created_user is not None
    assert created_user.name == 'John Doe'
    assert created_user.email == 'john@example.com'
    assert created_user.age == 30
    assert created_user.address == '123 Main St'

def test_create_user_with_duplicate_email(init_db):
    """
    Test creating a user with a duplicate email.
    This should raise an IntegrityError due to the unique constraint on the email field.
    """
    user_data = {
        'name': 'Jane Doe',
        'email': 'jane@example.com',
        'age': 28,
        'address': '456 Elm St'
    }

    # First user creation
    user1 = User(**user_data)
    db.session.add(user1)
    db.session.commit()

    # Attempt to create another user with the same email (should fail)
    user_data['name'] = 'Jack Doe'
    user2 = User(**user_data)
    
    db.session.add(user2)

    with pytest.raises(IntegrityError):
        db.session.commit()

def test_update_user(init_db):
    """
    Test updating an existing user profile.
    Verifies that the user data is correctly updated in the database.
    """
    # Create a user instance
    user = User(
        name="Alice",
        email="alice@example.com",
        age=22,
        address="789 Oak St"
    )
    db.session.add(user)
    db.session.commit()

    # Fetch the user and update the age
    user_to_update = User.query.filter_by(email="alice@example.com").first()
    user_to_update.age = 23
    db.session.commit()

    # Verify the update
    updated_user = User.query.filter_by(email="alice@example.com").first()
    assert updated_user.age == 23

def test_delete_user(init_db):
    """
    Test deleting a user profile from the database.
    Ensures the user is removed after the deletion process.
    """
    # Create a user instance
    user = User(
        name="Bob",
        email="bob@example.com",
        age=28,
        address="321 Pine St"
    )
    db.session.add(user)
    db.session.commit()

    # Fetch the user and delete
    user_to_delete = User.query.filter_by(email="bob@example.com").first()
    db.session.delete(user_to_delete)
    db.session.commit()

    # Verify the user was deleted
    deleted_user = User.query.filter_by(email="bob@example.com").first()
    assert deleted_user is None

def test_get_user_by_email(init_db):
    """
    Test fetching a user profile by email.
    Ensures that the correct user data is returned for a given email.
    """
    # Create a user
    user = User(
        name="Charlie",
        email="charlie@example.com",
        age=35,
        address="654 Maple St"
    )
    db.session.add(user)
    db.session.commit()

    # Fetch user by email
    user_to_fetch = User.query.filter_by(email="charlie@example.com").first()

    # Verify the correct user is returned
    assert user_to_fetch is not None
    assert user_to_fetch.name == "Charlie"
    assert user_to_fetch.age == 35
    assert user_to_fetch.address == "654 Maple St"
