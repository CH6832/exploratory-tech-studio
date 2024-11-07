#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
app.py
------
This file initializes the Flask application for the User Profile Service.
It sets up routes, configurations, and integrates with SQLAlchemy for managing user data.
"""

from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from routes.profile_routes import profile_blueprint

# Initialize the Flask app
app = Flask(__name__)

# Configure the app with the database URI (using SQLite for simplicity here)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///user_profile.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SECRET_KEY'] = 'your-secret-key'

# Initialize the database and migration
db = SQLAlchemy(app)
migrate = Migrate(app, db)

# Register blueprint for profile-related routes
app.register_blueprint(profile_blueprint, url_prefix='/profile')

# Home route (optional)
@app.route('/')
def home():
    """
    Home route that serves as the main entry point.
    """
    return "User Profile Service is running!"


if __name__ == '__main__':
    # Run the Flask application
    app.run(debug=True)
