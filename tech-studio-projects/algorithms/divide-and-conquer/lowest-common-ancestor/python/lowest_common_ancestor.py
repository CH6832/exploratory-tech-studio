#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""lowest_common_ancestor.py

The Lowest Common Ancestor (LCA) algorithm is used to find the lowest common ancestor of two nodes in a tree or directed acyclic graph (DAG). The lowest common ancestor of two nodes u and v in a tree is the lowest (i.e., deepest) node that has both u and v as descendants.

Here's a general overview of the LCA algorithm:

    Tree Representation: The tree is typically represented as an adjacency list, adjacency matrix, or as a collection of nodes with parent pointers.

    Depth Calculation: First, calculate the depth (or level) of each node in the tree. This can be done using a depth-first search (DFS) or breadth-first search (BFS) traversal.

    Parent Pointers: Next, create parent pointers for each node, allowing you to navigate from a node to its parent.

    Finding the LCA: Given two nodes u and v, compare their depths. If u is deeper than v, move u up the tree until it is at the same level as v. Similarly, if v is deeper, move v up the tree until it is at the same level as u. Then, simultaneously move both u and v up the tree until they meet at the lowest common ancestor.

Here's a high-level overview of the steps involved in finding the LCA of two nodes u and v:

    Calculate the depths of nodes u and v.
    Move the deeper node up the tree until it is at the same level as the other node.
    Move both nodes up the tree simultaneously until they meet at the lowest common ancestor.

The time complexity of the LCA algorithm depends on the method used to traverse the tree and calculate depths. In a binary tree, the time complexity is typically O(log N), where N is the number of nodes in the tree. However, in a general tree or DAG, the time complexity may vary depending on the specific implementation.

Overall, the LCA algorithm is a fundamental operation in tree and graph algorithms, commonly used in applications such as finding the nearest common ancestor in genealogical trees, solving various graph-related problems, and implementing efficient data structures like disjoint-set forests.

.. _PEP 484:
    https://www.python.org/dev/peps/pep-0484/
"""


class Node:
    """
    A class representing an individual node in a binary tree
    """
    def __init__(self, key):
        self.left = None
        self.right = None
        self.val = key


def find_lca(root, n1, n2):
    # To store paths to n1 and n2 from the root
    path1 = []
    path2 = []
 
    # Find paths from root to n1 and root to n2.
    # If either n1 or n2 is not present, return -1
    if (not find_path(root, path1, n1) or not find_path(root, path2, n2)):
        return -1
 
    # Compare the paths to get the first different value
    i = 0
    while(i < len(path1) and i < len(path2)):
        if path1[i] != path2[i]:
            break
        i += 1
    return path1[i-1]


# Finds the path from the root node to the given root of the tree.
# Stores the path in a list path[], returns true if the path
# exists, otherwise false
def find_path(root, path, k):
    # Base Case
    if root is None:
        return False

    # Store the current node in the path
    path.append(root.val)
 
    # Check if the current node is the target node
    if root.val == k:
        return True

    # Check if the target node is found in the left or right subtree
    if ((root.left is not None and find_path(root.left, path, k)) or
            (root.right is not None and find_path(root.right, path, k))):
        return True

    # If the target node is not found in the subtree rooted with root, remove the current node from the path and return False
    path.pop()
    return False


if __name__ == '__main__':
    # Example usage
    root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left.left = Node(4)
    root.left.right = Node(5)
    root.right.left = Node(6)
    root.right.right = Node(7)

    # Example call to find the lowest common ancestor of nodes 4 and 6
    lca = find_lca(root, 4, 6)
    print("Lowest Common Ancestor of 4 and 6:", lca)
