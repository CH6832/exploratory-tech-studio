# -*- coding: utf-8 -*-
"""
views.py
--------
This file contains the logic for generating product recommendations based on user behavior or preferences.
"""

from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Recommendation
from .serializers import RecommendationSerializer

@api_view(['GET'])
def get_recommendations(request, user_id):
    """
    Fetch product recommendations for a given user.
    
    Args:
        user_id (str): The user ID for whom to fetch the recommendations.
    
    Returns:
        Response: JSON response with the list of product recommendations.
    """
    recommendations = Recommendation.objects.filter(user_id=user_id)
    serializer = RecommendationSerializer(recommendations, many=True)
    return Response(serializer.data)
