#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""binary_tree.py

A module for implementing a singly linked list.
"""

import sys
sys.path.append("..")
from sklearn import tree

class TreeNode:
    """TreeNode class"""

    def __init__(self, data: int) -> None:
        """Initialize a TreeNode.

        Keyword arguments:
        data (int) -- The data to be stored in the node.
        """
        self.data = data
        self.left = None
        self.right = None

        return None

class BinaryTree:
    """BinaryTree class"""

    def __init__(self, root: int) -> None:
        """Initialize a BinaryTree with a root node.

        Keyword arguments:
        root (int) -- The data for the root node.
        """
        self.root = TreeNode(root)

        return None

    def print_tree(self, traversal_type: str) -> str:
        """Print the tree using the specified traversal type.

        Keyword arguments:
        traversal_type (str) -- The type of traversal to be performed ("preorder", "inorder", or "postorder").
        """
        if traversal_type == "preorder":
            return self.preorder_print(self.root, "")
        elif traversal_type == "inorder":
            return self.inorder_print(self.root, "")
        elif traversal_type == "postorder":
            return self.postorder_print(self.root, "")

    def preorder_print(self, start: TreeNode, traversal: str) -> str:
        """Perform preorder traversal starting from the given node.

        Keyword arguments:
        start (TreeNode) -- The starting node for traversal.
        traversal (str) -- The current traversal result.
        """
        if start:
            traversal += (str(start.data) + "-")
            traversal = self.preorder_print(start.left, traversal)
            traversal = self.preorder_print(start.right, traversal)
        return traversal

    def inorder_print(self, start: TreeNode, traversal: str) -> str:
        """Perform inorder traversal starting from the given node.

        Keyword arguments:
        start (TreeNode) -- The starting node for traversal.
        traversal (str) -- The current traversal result.
        """
        if start:
            traversal = self.inorder_print(start.left, traversal)
            traversal += (str(start.data) + "-")
            traversal = self.inorder_print(start.right, traversal)
        return traversal

    def postorder_print(self, start: TreeNode, traversal: str) -> str:
        """Perform postorder traversal starting from the given node.

        Keyword arguments:
        start (TreeNode) -- The starting node for traversal.
        traversal (str) -- The current traversal result.
        """
        if start:
            traversal = self.postorder_print(start.left, traversal)
            traversal = self.postorder_print(start.right, traversal)
            traversal += (str(start.data) + "-")
        return traversal
