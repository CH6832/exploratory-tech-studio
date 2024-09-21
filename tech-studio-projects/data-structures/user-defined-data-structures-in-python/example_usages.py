#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""Example usages of all user-defined data strucutres."""

from classes.Queue import Queue
from classes.Graph import Graph
from classes.LinkedList import LinkedList
from classes.Stack import Stack
from classes.BinaryTree import BinaryTree, TreeNode

def main() -> None:
    """Main program and drving code."""

    # ----
    print()
    print("Graph example usage: ")
    g = Graph()
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(2, 3)
    g.print_graph()
    print("    "+str(g))

    # ----
    print()
    print("Linked list example usage: ")
    # create a new linked list
    my_list = LinkedList()
    # append data
    my_list.append(1)
    my_list.append(2)
    my_list.append(3)
    # print the list
    my_list.print_list()

    # ----
    print()
    print("Queue example usage: ")
    q = Queue()
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    print(q.dequeue())

    # ----
    print()
    print("Stack example usage: ")
    s = Stack()
    s.push(1)
    s.push(2)
    s.push(3)
    print("    Take off the last added element: "+str(s.pop()))

    # ----
    print()
    print("Tree example usage: ")
    root = TreeNode("A")
    child1 = TreeNode("B")
    child2 = TreeNode("C")
    # root.add_child(child1)
    # root.add_child(child2)
    # child1.add_child(TreeNode("D"))
    # child1.add_child(TreeNode("E"))
    print("    "+str(root))
    root = BinaryTree("A")
    root.print_tree("inorder")

    return None

if __name__ == "__main__":
    main()
