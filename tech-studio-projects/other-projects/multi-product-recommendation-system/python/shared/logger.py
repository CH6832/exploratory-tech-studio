# ─────────────────────────────────────────────────────────────────────
# logger.py
# Logging utility used by all services.

# How to Use in Each Microservice:
# 
#     In your other microservices, you can simply import these utilities when needed, for example:
# 
# from shared.logger import get_logger
# from shared.caching import set_cache, get_cache, delete_cache
# from shared.utils import is_valid_email
# 
# This ensures consistency across all microservices, reduces code duplication, and improves maintainability.
#
#
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

"""
Logging utility to standardize logging across services.
"""

import logging

def get_logger(name: str) -> logging.Logger:
    """
    Creates and returns a logger with the specified name.
    
    Args:
        name (str): The name of the logger (usually the service name or module).
    
    Returns:
        logging.Logger: Configured logger for the service.
    """
    
    logger = logging.getLogger(name)
    logger.setLevel(logging.DEBUG)
    
    # Create a console handler
    console_handler = logging.StreamHandler()
    console_handler.setLevel(logging.DEBUG)
    
    # Create a formatter and set it for the handler
    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    console_handler.setFormatter(formatter)
    
    # Add the handler to the logger
    logger.addHandler(console_handler)
    
    return logger
