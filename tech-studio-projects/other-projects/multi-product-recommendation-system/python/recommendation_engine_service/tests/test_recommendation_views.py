# -*- coding: utf-8 -*-
"""
test_recommendation_views.py
-----------------------------
This file contains unit tests for the API views that handle fetching recommendations.
"""

from rest_framework.test import APITestCase
from rest_framework import status
from recommendation.models import Recommendation

class RecommendationViewTests(APITestCase):
    """
    Unit tests for the views related to fetching recommendations.
    """

    def test_get_recommendations(self):
        """
        Test that recommendations can be fetched for a given user.
        """
        # Create a sample recommendation
        recommendation = Recommendation.objects.create(
            product_id="12345",
            user_id="user123",
            score=0.95
        )

        # Make a GET request to the recommendations endpoint
        response = self.client.get(f'/recommendations/{recommendation.user_id}/')

        # Verify the response
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.data), 1)
        self.assertEqual(response.data[0]['product_id'], "12345")
        self.assertEqual(response.data[0]['user_id'], "user123")
        self.assertEqual(response.data[0]['score'], 0.95)
