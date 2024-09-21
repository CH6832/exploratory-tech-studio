#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""quick_sort.py

Quick sort is a popular sorting algorithm that follows the "divide and
conquer" strategy. It works by selecting a "pivot" element from the array
and partitioning the other elements into two sub-arrays according to whether
they are less than or greater than the pivot. The sub-arrays are then recursively
sorted.
"""

def main() -> None:
    """Driving code."""
    sample_list = [38, 27, 43, 3, 9, 82, 10]
    print("Original list:", sample_list)
    sorted_list = quick_sort(sample_list)
    print("Sorted list:", sorted_list)

def quick_sort(unsorted_list: list) -> list:
    """Sorts a list using the quick sort algorithm.

    Args:
        unsorted_list (list): The list to be sorted.

    Returns:
        list: The sorted list.
    """
    elements = len(unsorted_list)
    if elements < 2:
        return unsorted_list

    pivot = unsorted_list[0]
    left = [x for x in unsorted_list[1:] if x <= pivot]
    right = [x for x in unsorted_list[1:] if x > pivot]

    return quick_sort(left) + [pivot] + quick_sort(right)

if __name__ == "__main__":
    main()
