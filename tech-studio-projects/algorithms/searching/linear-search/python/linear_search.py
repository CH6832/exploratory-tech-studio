#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""linear_search.py

A program to demonstrate linear searching.
"""

# Searching an element in a list/array in python
# can be simply done using \'in\' operator
# Example:
# if x in arr:
#   print arr.index(x)
 
# If you want to implement Linear Search in python
 
# Linearly search x in arr[]
# If x is present then return its location
# else return -1
 
def main():
    """main program"""
    arr = [4,8,2,6,9,0,3]
    linear_search(arr, 4)

def linear_search(arr, x):
    """linear search"""
    for i in range(len(arr)):
        if arr[i] == x:
            return i
    return -1

if __name__ == "__main__":
    main()
