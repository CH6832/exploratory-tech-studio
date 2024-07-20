#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""graph.py"""


class Graph:
    """
    A class representing an undirected graph using an adjacency list representation.

    This class allows users to create a graph object and add edges between vertices.
    It also provides a method to print the adjacency list representation of the graph.

    Example usage:
    my_graph = Graph()
    my_graph.add_edge(1, 2)
    my_graph.add_edge(1, 3)
    my_graph.print_graph()
    1 -> 2 -> 3
    2 -> 1
    3 -> 1
    """


    def __init__(self) -> None:
        """Initializes an empty graph with an empty
        dictionary to store the adjacency lists.
        """        
        self.graph: dict = {}
        print(f"    Init graph: {self.graph}")
        return None


    def add_edge(self, u, v) -> None:
        """Adds an undirected edge between vertices u and v in the graph.

        If the vertex u already exists in the graph, v is appended to its adjacency list.
        If u does not exist, a new entry with u as the key and [v] as the value is created.

        Parameters:
        u -- The first vertex of the edge.
        v -- The second vertex of the edge.
        """
        if u in self.graph:
            self.graph[u].append(v)
            print(f"    Appended to its adjacency list, because u already exists: {self.graph[u]}")
        else:
            self.graph[u] = [v]
            print(f"    New entry with key u and value [v]: {self.graph[u]}")
        return None


    def print_graph(self) -> None:
        """Prints the adjacency list representation of the graph.

        Each line of output represents a vertex followed by its adjacent vertices,
        separated by "->".
        """        
        for vertex in self.graph:
            # print(vertex, "->", " -> ".join(map(str, self.graph[vertex])))
            adjacency_list = vertex, "->", " -> ".join(map(str, self.graph[vertex]))
            print("    "+str(adjacency_list))
        return None
