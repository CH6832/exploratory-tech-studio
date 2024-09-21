#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""binary_search.py

A program to demonstrate recursive binary searching.
"""


def main():
    arr = [2, 3, 4, 10, 40]
    x = 10
    binary_search([0,1,2,3,4,5,6,7,8,9,8,9,9],2,7,4)

    result = binary_search(arr, 0, len(arr) - 1, x)

    if result != -1:
      print("Element", x, "is present at index", result)
    else:
      print("Element", x, "is not present in the array")


def binary_search(arr, low, high, x):
    """Binary search
    """
    # Check base case
    if high >= low:
 
        mid = (high + low) // 2
 
        # If element is present at the middle itself
        if arr[mid] == x:
            return mid
 
        # If element is smaller than mid, then it can only
        # be present in left subarray
        elif arr[mid] > x:
            return binary_search(arr, low, mid - 1, x)
 
        # Else the element can only be present in right subarray
        else:
            return binary_search(arr, mid + 1, high, x)
 
    else:
        # Element is not present in the array
        return -1


if __name__ == "__main__":
    main()
