# ─────────────────────────────────────────────────────────────────────
# utils.py
# Helper functions for common tasks used across services.
# ─────────────────────────────────────────────────────────────────────

# -*- coding: utf-8 -*-

"""
Common utility functions for general tasks such as string validation,
data transformations, and more.
"""

import re

def is_valid_email(email: str) -> bool:
    """
    Validate if the given email address is in the correct format.
    
    Args:
        email (str): The email address to validate.
    
    Returns:
        bool: True if the email is valid, False otherwise.
    """
    email_regex = r"(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)"
    return bool(re.match(email_regex, email))

def is_valid_phone_number(phone_number: str) -> bool:
    """
    Validate if the given phone number is in the correct format.
    
    Args:
        phone_number (str): The phone number to validate.
    
    Returns:
        bool: True if the phone number is valid, False otherwise.
    """
    phone_regex = r"^\+?[1-9]\d{1,14}$"
    return bool(re.match(phone_regex, phone_number))

def generate_unique_id() -> str:
    """
    Generate a unique ID, often used for caching keys, transaction IDs, etc.
    
    Returns:
        str: A unique string ID.
    """
    import uuid
    return str(uuid.uuid4())
