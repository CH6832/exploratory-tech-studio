#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""app_integration.py"""

import os
import sys
from typing import Generator, Any
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import pytest
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)
from ..app import app, birthdays_db, UserModel


@pytest.fixture
def client() -> Generator:
    """Create a test client for the Flask application"""
    app.config['TESTING'] = True
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///:memory:'
    birthdays_db_test = SQLAlchemy(app)
    with app.test_client() as client:
        with app.app_context():
            birthdays_db_test.create_all()
        yield client
        birthdays_db_test.drop_all()
    return Generator


def test_index_route(client) -> None:
    """Test the index route"""
    response = client.get('/')
    assert response.status_code == 200
    assert b"Welcome to the Birthday Tracker Application" in response.data

    return None


def test_add_birthday(client) -> None:
    """Test adding a birthday entry"""
    response = client.post('/add', data={'name': 'John', 'year': 1990, 'month': 5, 'day': 10})
    assert response.status_code == 302  # Check if redirected after adding
    with app.app_context():
        user = UserModel.query.filter_by(name='John').first()
        assert user is not None  # Check if the user exists in the database

    return None


def test_delete_birthday(client) -> None:
    """Test deleting a birthday entry"""
    with app.app_context():
        # Add a user to the database
        user = UserModel(name='Alice', year=1985, month=3, day=15)
        birthdays_db.session.add(user)
        birthdays_db.session.commit()

    # Attempt to delete the user
    response = client.post('/delete/Alice')
    assert response.status_code == 302  # Check if redirected after deleting
    with app.app_context():
        user = UserModel.query.filter_by(name='Alice').first()
        assert user is None  # Check if the user no longer exists in the database

    return None
