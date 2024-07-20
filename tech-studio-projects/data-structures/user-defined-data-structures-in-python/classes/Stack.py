#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""stack.py

A class representing a stack data structure.
"""

from typing import Any, List

class Stack:
    """
    A class representing a stack data structure.
    """

    def __init__(self) -> None:
        """Initializes an empty stack."""
        self.items: List[Any] = []
        
        return None

    def push(self, item: Any) -> None:
        """Pushes an item onto the top of the stack.

        Keyword arguments:
        item (Any) -- The item to be pushed onto the stack.
        """
        self.items.append(item)

    def pop(self) -> Any:
        """Removes and returns the item at the top of the stack."""
        if not self.is_empty():
            return self.items.pop()

    def is_empty(self) -> bool:
        """Checks if the stack is empty."""
        return len(self.items) == 0

    def peek(self) -> Any:
        """Returns the item at the top of the stack without removing it."""
        if not self.is_empty():
            return self.items[-1]

    def size(self) -> int:
        """Returns the number of elements in the stack.

        Keyword arguments:
         - int -- The number of elements in the stack.
        """
        return len(self.items)