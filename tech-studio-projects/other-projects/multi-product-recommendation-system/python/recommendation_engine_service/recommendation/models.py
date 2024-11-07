# -*- coding: utf-8 -*-
"""
models.py
----------
This file defines the database model for storing product recommendations in Django.
"""

from django.db import models

class Recommendation(models.Model):
    """
    Recommendation model stores product recommendations for users.
    Each recommendation links to a product and contains user-specific recommendation data.
    """
    product_id = models.CharField(max_length=255, unique=True)
    user_id = models.CharField(max_length=255)
    score = models.FloatField()

    def __str__(self):
        return f"Recommendation for {self.user_id} - Product: {self.product_id}, Score: {self.score}"
