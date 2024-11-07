# -*- coding: utf-8 -*-
"""
urls.py
-------
This file defines the URL routes for the recommendation API endpoints.
"""

from django.urls import path
from . import views

urlpatterns = [
    path('recommendations/<str:user_id>/', views.get_recommendations, name='get_recommendations'),
]
