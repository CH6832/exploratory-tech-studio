import os
import sys
import unittest
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from backend.black_scholes import black_scholes_call, black_scholes_put


class TestBlackScholes(unittest.TestCase):

    def test_black_scholes_call(self):
        call_price = black_scholes_call(100, 100, 1, 0.2, 0.05)
        self.assertAlmostEqual(call_price, 10.4506, places=4)

    def test_black_scholes_put(self):
        put_price = black_scholes_put(100, 100, 1, 0.2, 0.05)
        self.assertAlmostEqual(put_price, 5.5735, places=4)

if __name__ == '__main__':
    unittest.main()
