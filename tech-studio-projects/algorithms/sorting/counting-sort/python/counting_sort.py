#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""counting_sort.py

Explanation:

Type Hints: Added type hints to the function signature.
Docstrings: Expanded the docstring to explain parameters and return values.
Array Initialization: Corrected the initialization of the output array to use empty strings instead of zeros, as we are dealing with characters.
Count Array Update: Updated the count array in a loop starting from 1 to 255 to avoid the issue of negative indexing.
Reversed Iteration: Iterated in reverse when building the output array to maintain the stability of the sorting algorithm.
"""

from typing import List

def main() -> None:
    """Driving code."""

    input_arr = ['g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's']
    sorted_arr = counting_sort(input_arr)
    print(sorted_arr)

    return None


def counting_sort(arr: List[str]) -> List[str]:
    """Sorts a list of characters using the Counting Sort algorithm.

    Parameters:
    arr (List[str]) -- The list of characters to be sorted.

    Returns:
    List[str] -- The sorted list of characters.
    """
    # The output character array that will have sorted arr
    output = ['' for _ in range(len(arr))]
    
    # Create a count array to store count of individual characters
    count = [0] * 256
    
    # Store count of each character
    for char in arr:
        count[ord(char)] += 1
    
    # Change count[i] so that count[i] now contains actual position of this character in output array
    for i in range(1, 256):
        count[i] += count[i - 1]
    
    # Build the output character array
    for char in reversed(arr):  # Iterate in reverse to maintain stability
        output[count[ord(char)] - 1] = char
        count[ord(char)] -= 1
    
    return output

if __name__ == "__main__":
    main()
