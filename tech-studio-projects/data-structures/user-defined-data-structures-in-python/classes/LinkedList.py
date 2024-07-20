#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""linked_list.py

A module for implementing a singly linked list.
"""

class Node:
    """A class representing a single node in a linked list."""
    
    def __init__(self, data: int) -> None:
        """
        Initialize a new node.

        Parameters:
            data (int): The integer data to be stored in the node.
        """
        self.data = data
        self.next = None

        return None

class LinkedList:
    """A class representing a singly linked list."""
    
    def __init__(self) -> None:
        """
        Initialize an empty linked list.
        """
        self.head = None

        return None

    def append(self, data: int) -> None:
        """
        Append a new node with the given data to the end of the list.

        Parameters:
            data (int): The integer data to be appended to the list.
        """
        new_node = Node(data)
        if not self.head:
            self.head = new_node
            return
        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node

        return None

    def print_list(self) -> None:
        """
        Print the data stored in each node of the list.
        """
        current_node = self.head
        while current_node:
            print("    "+str(current_node.data), end=" ")
            current_node = current_node.next
        print()

        return None
