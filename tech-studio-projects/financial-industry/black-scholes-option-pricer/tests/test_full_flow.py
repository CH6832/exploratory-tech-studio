import json
import os
import sys
import unittest
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from backend.app import app
from backend.models import BlackScholesInputs

class TestFullFlow(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client()
        self.app.testing = True

    def test_full_calculation_and_storage(self):
        self.app.post('/calculate', data=json.dumps({
            'stock_price': 100,
            'strike_price': 100,
            'time_to_maturity': 1,
            'volatility': 0.2,
            'interest_rate': 0.05
        }), content_type='application/json')

        result = BlackScholesInputs.query.first()
        self.assertIsNotNone(result)
        self.assertEqual(result.StockPrice, 100)
        self.assertEqual(result.StrikePrice, 100)

if __name__ == '__main__':
    unittest.main()
