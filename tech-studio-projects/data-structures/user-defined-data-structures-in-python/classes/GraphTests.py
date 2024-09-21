#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""graph_test.py

Unit tests for the graph implementation.
"""

import io
import unittest
from unittest.mock import patch
from Graph import Graph

class TestGraph(unittest.TestCase):
    """Test cases for the Graph class."""

    def test_add_edge(self) -> None:
        """Test the add_edge method of the Graph class."""
        graph = Graph()
        graph.add_edge(1, 2)
        graph.add_edge(1, 3)
        graph.add_edge(2, 3)
        expected_graph = {1: [2, 3], 2: [3]}
        self.assertEqual(graph.graph, expected_graph)
        return None

    def test_print_graph(self) -> None:
        """Test the print_graph method of the Graph class."""
        graph = Graph()
        graph.add_edge(1, 2)
        graph.add_edge(1, 3)
        graph.add_edge(2, 3)
        expected_output = "1 -> 2 -> 3\n2 -> 3\n"
        with patch('sys.stdout', new=io.StringIO()) as fake_stdout:
            graph.print_graph()
            self.assertEqual(fake_stdout.getvalue(), expected_output)
        return None

if __name__ == '__main__':
    unittest.main()
