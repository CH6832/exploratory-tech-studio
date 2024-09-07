import os
import sys
import unittest
import json
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from backend.app import app

class TestRoutes(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client()
        self.app.testing = True

    def test_calculate_option(self):
        response = self.app.post('/calculate', data=json.dumps({
            'stock_price': 100,
            'strike_price': 100,
            'time_to_maturity': 1,
            'volatility': 0.2,
            'interest_rate': 0.05
        }), content_type='application/json')

        self.assertEqual(response.status_code, 200)
        data = json.loads(response.data)
        self.assertIn('call_price', data)
        self.assertIn('put_price', data)

if __name__ == '__main__':
    unittest.main()
