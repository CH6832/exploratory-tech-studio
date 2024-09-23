import os

class Config:
    SECRET_KEY = os.getenv('SECRET_KEY', 'your_secret_key')
    DEBUG = os.getenv('DEBUG', 'True') == 'True'
    DATABASE_URI = os.getenv('DATABASE_URI', 'sqlite:///ecommerce.db')
    # Add more configuration settings as necessary
