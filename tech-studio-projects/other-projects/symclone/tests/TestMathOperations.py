#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""MathOperations.py

Unittest for the MathOperations class.
"""

import os
import sys
import unittest
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)
from symclone.MathOperations import MathOperations

class TestMathOperations(unittest.TestCase):
    """
    Test case for the MathOperations class.
    """
    
    def test_derivative(self):
        """Test the derivative method of MathOperations.

        This test checks if the numerical derivative of the function f(x) = x^2 + 2*x + 1
        at x = 3 is approximately equal to 8.
        """
        func = lambda x: x**2 + 2*x + 1
        result = MathOperations.derivative(func, 'x', 3)
        self.assertEqual(result, 8.0001)  # Approximately equal due to numerical differentiation

    def test_integral(self):
        """Test the integral method of MathOperations.

        This test checks if the numerical integral of the function f(x) = x^2 + 2*x + 1
        over the interval [0, 1] is approximately equal to 1.3333333333333333.
        """
        func = lambda x: x**2 + 2*x + 1
        result = MathOperations.integral(func, 0, 1)
        self.assertEqual(result, 1.3333333333333333)  # Approximately equal due to numerical integration

if __name__ == '__main__':
    unittest.main()
