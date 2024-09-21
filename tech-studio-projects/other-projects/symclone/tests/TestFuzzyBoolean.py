#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""TestFuzzyBoolean.py

Unittest for the FuzzyBoolean class.
"""

import os
import sys
import unittest
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)
from symclone.FuzzyBoolean import FuzzyBoolean

class TestFuzzyBoolean(unittest.TestCase):
    """
    Test case for the FuzzyBoolean class.
    """

    def test_and(self):
        """Test the AND operation for FuzzyBoolean.

        This test checks if the AND operation between FuzzyBoolean(0.6) and FuzzyBoolean(0.4)
        correctly results in FuzzyBoolean(0.4).
        """
        a = FuzzyBoolean(0.6)
        b = FuzzyBoolean(0.4)
        result = a & b
        self.assertEqual(result.value, 0.4)

    def test_or(self):
        """Test the OR operation for FuzzyBoolean.

        This test checks if the OR operation between FuzzyBoolean(0.6) and FuzzyBoolean(0.4)
        correctly results in FuzzyBoolean(0.6).
        """
        a = FuzzyBoolean(0.6)
        b = FuzzyBoolean(0.4)
        result = a | b
        self.assertEqual(result.value, 0.6)

    def test_not(self):
        """Test the NOT operation for FuzzyBoolean.

        This test checks if the NOT operation on FuzzyBoolean(0.6) correctly results in FuzzyBoolean(0.4).
        """
        a = FuzzyBoolean(0.6)
        result = ~a
        self.assertEqual(result.value, 0.4)

if __name__ == '__main__':
    unittest.main()
