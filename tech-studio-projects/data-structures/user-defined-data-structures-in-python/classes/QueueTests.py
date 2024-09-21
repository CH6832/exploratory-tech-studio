#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""Unit tests for the queue implementation."""

import unittest
from Queue import Queue

class TestQueue(unittest.TestCase):
    """Test cases for the Queue class."""

    def test_enqueue(self):
        """Test enqueueing elements into the queue."""
        q = Queue()
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)
        self.assertEqual(q.items, [3, 2, 1])

    def test_dequeue(self):
        """Test dequeuing elements from the queue."""
        q = Queue()
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)
        self.assertEqual(q.dequeue(), 1)
        self.assertEqual(q.items, [3, 2])

    def test_is_empty(self):
        """Test checking if the queue is empty."""
        q = Queue()
        self.assertTrue(q.is_empty())
        q.enqueue(1)
        self.assertFalse(q.is_empty())

    def test_peek(self):
        """Test peeking at the front element of the queue."""
        q = Queue()
        q.enqueue(1)
        q.enqueue(2)
        self.assertEqual(q.peek(), 1)
        q.dequeue()
        self.assertEqual(q.peek(), 2)

    def test_size(self):
        """Test getting the size of the queue."""
        q = Queue()
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)
        self.assertEqual(q.size(), 3)

if __name__ == '__main__':
    unittest.main()
