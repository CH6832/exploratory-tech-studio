#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""merge_sort.py

This module implements the merge sort algorithm, which is a
divide-and-conquer sorting algorithm. The algorithm recursively
divides a list into two halves, sorts each half, and then merges
the sorted halves to produce the final sorted list.
"""

def main() -> None:
    """Driving code."""
    sample_list = [38, 27, 43, 3, 9, 82, 10]
    print("Original list:", sample_list)
    merge_sort(sample_list)
    print("Sorted list:", sample_list)

    return None

def merge_sort(input_list: list) -> None:
    """Merge sort algorithm.
    
    Parameters:
    input_list (list) -- The list of elements to be sorted.
    """
    if len(input_list) > 1:
        # Finding the mid of the list
        mid = len(input_list) // 2
        # Dividing the elements into 2 halves
        left_part = input_list[:mid]
        right_part = input_list[mid:]

        # Sorting both halves
        merge_sort(left_part)
        merge_sort(right_part)

        i = j = k = 0

        # Copy data to temp lists left_part[] and right_part[]
        while i < len(left_part) and j < len(right_part):
            if left_part[i] <= right_part[j]:
                input_list[k] = left_part[i]
                i += 1
            else:
                input_list[k] = right_part[j]
                j += 1
            k += 1

        # Checking if any element was left in left_part[]
        while i < len(left_part):
            input_list[k] = left_part[i]
            i += 1
            k += 1

        # Checking if any element was left in right_part[]
        while j < len(right_part):
            input_list[k] = right_part[j]
            j += 1
            k += 1

        return None

if __name__ == "__main__":
    main()
