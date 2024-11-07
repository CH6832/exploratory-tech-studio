#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
models.py
---------
This file defines the User model for storing user profile data in the database.
It uses SQLAlchemy to create the necessary database schema.
"""

from app import db

class User(db.Model):
    """
    User model represents a user profile in the system.
    It stores the user's ID, name, email, and other personal data.
    """
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    age = db.Column(db.Integer, nullable=True)
    address = db.Column(db.String(250), nullable=True)

    def __repr__(self):
        """
        String representation of the User object.
        """
        return f'<User {self.name}>'
