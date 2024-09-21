#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""insertion_sort.py

This module provides an implementation of the insertion sort algorithm.
"""

def main() -> None:
    """Driving code for the insertion sort algorithm."""
    sample_list = [12, 11, 13, 5, 6]
    print("Original list:", sample_list)
    insertion_sort(sample_list)
    print("Sorted list:", sample_list)

    return None

def insertion_sort(list_to_sort: list) -> None:
    """Insertion sort algorithm.
    
    Parameters:
    list_to_sort (list) -- The list of elements to be sorted.
    """
    print(f"Unsorted: {list_to_sort}")
    # Iterate over the array to be sorted
    for i in range(1, len(list_to_sort)):
        x = list_to_sort[i]  # Get each element
        j = i - 1  # Get one position before x
        # Shift elements until reaching index 0 or getting an element smaller than x
        while j >= 0 and x < list_to_sort[j]:
            list_to_sort[j + 1] = list_to_sort[j]
            j -= 1
        # Place x in its correct position
        list_to_sort[j + 1] = x
    print(f"Sorted: {list_to_sort}")

    return None

if __name__ == "__main__":
    main()
