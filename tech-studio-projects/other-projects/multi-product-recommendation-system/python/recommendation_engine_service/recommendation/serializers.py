# -*- coding: utf-8 -*-
"""
serializers.py
---------------
This file defines the serializers for the recommendation model.
Serializers are used to convert model instances into JSON format and validate incoming data.
"""

from rest_framework import serializers
from .models import Recommendation

class RecommendationSerializer(serializers.ModelSerializer):
    """
    Serializer for the Recommendation model to validate and serialize data.
    """

    class Meta:
        model = Recommendation
        fields = ['product_id', 'user_id', 'score']
