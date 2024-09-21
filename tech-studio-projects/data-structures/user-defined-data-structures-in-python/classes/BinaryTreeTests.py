#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""Unit tests for the binary tree implementation."""

from BinaryTree import BinaryTree
import unittest

class TestBinaryTree(unittest.TestCase):
    
    def setUp(self) -> None:
        """
        Set up the binary tree for testing.
        """
        self.tree: BinaryTree = BinaryTree(1)
        self.tree.root.left = TreeNode(2)
        self.tree.root.right = TreeNode(3)
        self.tree.root.left.left = TreeNode(4)
        self.tree.root.left.right = TreeNode(5)
        self.tree.root.right.left = TreeNode(6)
        self.tree.root.right.right = TreeNode(7)

    def test_preorder_print(self) -> None:
        """
        Test preorder traversal of the binary tree.
        """
        expected_output: str = "1-2-4-5-3-6-7-"
        self.assertEqual(self.tree.print_tree("preorder"), expected_output)

    def test_inorder_print(self) -> None:
        """
        Test inorder traversal of the binary tree.
        """
        expected_output: str = "4-2-5-1-6-3-7-"
        self.assertEqual(self.tree.print_tree("inorder"), expected_output)

    def test_postorder_print(self) -> None:
        """
        Test postorder traversal of the binary tree.
        """
        expected_output: str = "4-5-2-6-7-3-1-"
        self.assertEqual(self.tree.print_tree("postorder"), expected_output)

if __name__ == '__main__':
    unittest.main()
