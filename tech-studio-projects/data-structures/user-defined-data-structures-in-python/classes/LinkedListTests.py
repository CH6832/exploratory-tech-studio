#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""Unit tests for the linked list implementation."""

import io
import unittest
from unittest.mock import patch
from LinkedList import Node, LinkedList

class TestLinkedList(unittest.TestCase):
    """Test cases for the LinkedList class."""

    def test_node_creation(self):
        """Test creating a Node object."""
        node = Node(5)
        self.assertEqual(node.data, 5)
        self.assertIsNone(node.next)

    def test_linked_list_append(self):
        """Test appending elements to the LinkedList."""
        linked_list = LinkedList()
        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)
        self.assertEqual(linked_list.head.data, 1)
        self.assertEqual(linked_list.head.next.data, 2)
        self.assertEqual(linked_list.head.next.next.data, 3)

    def test_linked_list_print(self):
        """Test printing the elements of the LinkedList."""
        linked_list = LinkedList()
        linked_list.append(1)
        linked_list.append(2)
        linked_list.append(3)
        expected_output = "1 2 3 "
        with patch('sys.stdout', new=io.StringIO()) as fake_stdout:
            linked_list.print_list()
            self.assertEqual(fake_stdout.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()
