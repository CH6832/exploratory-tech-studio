#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""Unit tests for the stack implementation."""

import unittest
from Stack import Stack

class TestStack(unittest.TestCase):
    """Test cases for the Stack class."""

    def test_push(self):
        """Test pushing elements onto the stack."""
        s = Stack()
        s.push(1)
        s.push(2)
        s.push(3)
        self.assertEqual(s.items, [1, 2, 3])

    def test_pop(self):
        """Test popping elements from the stack."""
        s = Stack()
        s.push(1)
        s.push(2)
        s.push(3)
        self.assertEqual(s.pop(), 3)
        self.assertEqual(s.items, [1, 2])

    def test_is_empty(self):
        """Test checking if the stack is empty."""
        s = Stack()
        self.assertTrue(s.is_empty())
        s.push(1)
        self.assertFalse(s.is_empty())

    def test_peek(self):
        """Test peeking at the top element of the stack."""
        s = Stack()
        s.push(1)
        s.push(2)
        self.assertEqual(s.peek(), 2)
        s.pop()
        self.assertEqual(s.peek(), 1)

    def test_size(self):
        """Test getting the size of the stack."""
        s = Stack()
        s.push(1)
        s.push(2)
        s.push(3)
        self.assertEqual(s.size(), 3)

if __name__ == '__main__':
    unittest.main()