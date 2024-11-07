# -*- coding: utf-8 -*-
"""
test_recommendations.py
------------------------
This file contains unit tests for the recommendation generation logic in the `tasks.py` file.
"""

from recommendation.tasks import recalculate_recommendations
from recommendation.models import Recommendation
from django.test import TestCase

class RecommendationTaskTests(TestCase):
    """
    Unit tests for the Celery background tasks related to recommendations.
    """

    def test_recalculate_recommendations(self):
        """
        Test that the recalculate_recommendations task correctly adds a recommendation.
        """
        user_id = "user123"
        recalculate_recommendations(user_id)

        # Check if the recommendation has been created
        recommendation = Recommendation.objects.filter(user_id=user_id).first()
        self.assertIsNotNone(recommendation)
        self.assertEqual(recommendation.user_id, user_id)
        self.assertEqual(recommendation.product_id, "12345")
        self.assertEqual(recommendation.score, 0.95)
