# -*- coding: utf-8 -*-
"""
config.py
---------
This file contains configuration settings used in the API Gateway service,
including API keys, rate-limiting parameters, and any other configurations
that need to be used across multiple modules.
"""

from pydantic import BaseSettings

class Settings(BaseSettings):
    """
    Configuration settings for the API Gateway.
    This class is used to load configuration from environment variables or .env files.
    """
    API_KEY: str = "your_api_key_here"
    RATE_LIMIT: int = 100  # Max requests per minute
    SERVICE_TIMEOUT: int = 5  # Timeout for external service requests in seconds

    class Config:
        """Pydantic configuration to load environment variables from .env files."""
        env_file = ".env"
        env_file_encoding = "utf-8"


# Load settings from environment
settings = Settings()
