# ─────────────────────────────────────────────────────────────────────
# config.py
# Shared configuration file for all services.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

"""
Configuration settings for all services.
"""

import os

class Config:
    """
    Base configuration class for shared configuration settings.
    Subclass this for service-specific configurations.
    """
    
    # Redis settings (for caching across services)
    REDIS_HOST = os.getenv("REDIS_HOST", "localhost")
    REDIS_PORT = os.getenv("REDIS_PORT", 6379)
    REDIS_DB = os.getenv("REDIS_DB", 0)
    
    # General application settings
    DEBUG = os.getenv("DEBUG", False)
    SECRET_KEY = os.getenv("SECRET_KEY", "your-secret-key")
    
    # Other shared settings can be added here
