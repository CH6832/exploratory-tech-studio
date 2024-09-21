#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""queue.py

A class representing a queue data structure.

This class implements the basic operations of a queue, including
enqueue (adding an item to the end of the queue), dequeue (removing
and returning the item at the front of the queue), peek (returning
the item at the front of the queue without removing it), is_empty
(checking if the queue is empty), and size (returning the number
of elements in the queue).
"""

class Queue:
    """
    A class representing a queue data structure.
    """

    def __init__(self) -> None:
        """Initializes an empty queue."""
        self.items = []

        return None

    def enqueue(self, item: object) -> None:
        """Adds an item to the end of the queue.

        Keyword arguments:
        item -- The item to be added to the queue.
        """
        self.items.insert(0, item)

        return None

    def dequeue(self) -> object:
        """Removes and returns the item at the front of the queue."""
        if not self.is_empty():
            return self.items.pop()

    def is_empty(self) -> bool:
        """Checks if the queue is empty."""
        return len(self.items) == 0

    def peek(self) -> object:
        """Returns the item at the front of the queue without removing it."""
        if not self.is_empty():
            return self.items[-1]

    def size(self) -> int:
        """Returns the number of elements in the queue."""
        return len(self.items)
