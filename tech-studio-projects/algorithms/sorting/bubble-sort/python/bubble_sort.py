#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""bubble_sort.py

Main Function (`main`):
- The `main` function serves as the entry point of the script.
- It initializes an unsorted list of integers (`unsorted_list`), prints it, sorts it using the `bubble_sort` function,
prints the sorted list, and then visualizes the sorting process using the `visualize_bubble_sort` function.

Bubble Sort Function (bubble_sort):
- The `bubble_sort` function takes a list of integers (`input_list`) as input and sorts it using the Bubble Sort algorithm.
- It iterates over the list multiple times, comparing adjacent elements and swapping them if they are in the wrong order.
- After each iteration, the largest element "bubbles up" to its correct position at the end of the list.
- The process continues until the entire list is sorted, and the sorted list is returned.

Visualization Function (visualize_bubble_sort):
- The `visualize_bubble_sort` function visualizes the sorting process of the Bubble Sort algorithm.
- It creates a figure and axes using `plt.subplots()` and plots a bar graph representing the initial state of the input list.
- It then iterates through the Bubble Sort algorithm, updating the plot at each step to reflect the changes in the list as it gets sorted.
- The `plt.pause(0.1)` function call adds a short pause between each iteration to visualize the sorting process gradually.
- Finally, it displays the fully sorted list using `plt.show()`.
"""

import matplotlib.pyplot as plt
from typing import List

def main() -> None:
    """Driving code."""
    unsorted_list = [64, 34, 25, 12, 22, 11, 90]
    sorted_list = bubble_sort(unsorted_list.copy())
    print("Sorted list:", sorted_list)
    visualize_bubble_sort(unsorted_list)

    return None

def bubble_sort(input_list: List[int]) -> List[int]:
    """Sorts a list of integers using the Bubble Sort algorithm.

    Parameters:
    input_list (List[int]) -- The input list to be sorted.

    Returns:
    List[int] -- The sorted list.
    """
    n = len(input_list)
    for i in range(n):
        for j in range(0, n-i-1):
            if input_list[j] > input_list[j+1]:
                input_list[j], input_list[j+1] = input_list[j+1], input_list[j]
    
    return input_list

def visualize_bubble_sort(input_list: List[int]) -> None:
    """Visualizes the Bubble Sort algorithm sorting process.

    Parameters:
    input_list (List[int]) -- The input list to be sorted.
    """
    fig, ax = plt.subplots()
    ax.bar(range(len(input_list)), input_list)
    ax.set_title('Bubble Sort Visualization')
    ax.set_xlabel('Indices')
    ax.set_ylabel('Values')

    for i in range(len(input_list)):
        for j in range(0, len(input_list)-i-1):
            if input_list[j] > input_list[j+1]:
                input_list[j], input_list[j+1] = input_list[j+1], input_list[j]
                ax.clear()
                ax.bar(range(len(input_list)), input_list)
                plt.pause(0.1)
    plt.show()

    return None

if __name__ == "__main__":
    main()