import os

class Config:
    """Base configuration."""
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'a_really_secret_key'
    DEBUG = os.environ.get('DEBUG', 'false').lower() == 'true'

class DevelopmentConfig(Config):
    """Development configuration."""
    DEBUG = True
