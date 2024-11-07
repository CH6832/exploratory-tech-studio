#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
profile_routes.py
-----------------
This file contains the routes (API endpoints) for handling user profile data.
The routes include fetching, updating, and deleting user profiles.
"""

from flask import Blueprint, request, jsonify
from app import db
from models import User

# Create a blueprint for profile routes
profile_blueprint = Blueprint('profile', __name__)

@profile_blueprint.route('/<int:user_id>', methods=['GET'])
def get_user_profile(user_id):
    """
    Fetch the profile of a user by their ID.
    
    Args:
        user_id (int): The user ID to fetch the profile for.
        
    Returns:
        JSON response with the user profile data.
    """
    user = User.query.get(user_id)
    if user:
        return jsonify({
            'id': user.id,
            'name': user.name,
            'email': user.email,
            'age': user.age,
            'address': user.address
        }), 200
    return jsonify({'message': 'User not found'}), 404

@profile_blueprint.route('/', methods=['POST'])
def create_user_profile():
    """
    Create a new user profile.
    
    Returns:
        JSON response with the created user profile.
    """
    data = request.get_json()
    new_user = User(
        name=data['name'],
        email=data['email'],
        age=data.get('age', None),
        address=data.get('address', None)
    )
    db.session.add(new_user)
    db.session.commit()
    
    return jsonify({
        'id': new_user.id,
        'name': new_user.name,
        'email': new_user.email,
        'age': new_user.age,
        'address': new_user.address
    }), 201

@profile_blueprint.route('/<int:user_id>', methods=['PUT'])
def update_user_profile(user_id):
    """
    Update an existing user profile by ID.
    
    Args:
        user_id (int): The user ID to update.
        
    Returns:
        JSON response with the updated user profile.
    """
    data = request.get_json()
    user = User.query.get(user_id)
    
    if user:
        user.name = data.get('name', user.name)
        user.email = data.get('email', user.email)
        user.age = data.get('age', user.age)
        user.address = data.get('address', user.address)
        
        db.session.commit()
        
        return jsonify({
            'id': user.id,
            'name': user.name,
            'email': user.email,
            'age': user.age,
            'address': user.address
        }), 200
    
    return jsonify({'message': 'User not found'}), 404

@profile_blueprint.route('/<int:user_id>', methods=['DELETE'])
def delete_user_profile(user_id):
    """
    Delete a user profile by ID.
    
    Args:
        user_id (int): The user ID to delete.
        
    Returns:
        JSON response indicating the result of the deletion.
    """
    user = User.query.get(user_id)
    if user:
        db.session.delete(user)
        db.session.commit()
        return jsonify({'message': 'User profile deleted successfully'}), 200
    
    return jsonify({'message': 'User not found'}), 404
