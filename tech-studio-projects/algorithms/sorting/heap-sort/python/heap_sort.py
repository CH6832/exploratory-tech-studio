#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""heap_sort.py

Certainly! Let's break down the `heap_sort` function and the driving code step by step to understand how the heap sort algorithm works.

### Overview of Heap Sort

Heap sort is a comparison-based sorting algorithm that uses a binary heap data structure. It involves two main steps:
1. **Building a max heap**: Rearranging the array to satisfy the max heap property, where each parent node is greater than or equal to its child nodes.
2. **Extracting elements from the heap**: Repeatedly removing the largest element from the heap and rebuilding the heap with the remaining elements.

### Detailed Explanation

#### Main Function
```python
def main() -> None:
    arr = [12, 11, 13, 5, 6, 7]
    print("Original array:", arr)
    heap_sort(arr)
    print("Sorted array:", arr)

if __name__ == "__main__":
    main()
```
- The `main` function initializes an array `arr` with unsorted integers.
- It prints the original array.
- It calls the `heap_sort` function to sort the array.
- Finally, it prints the sorted array.

#### Heap Sort Function
```python
def heap_sort(arr):
    n = len(arr)
```
- `heap_sort` takes an array `arr` and sorts it in place.
- `n` is the length of the array.

#### Heapify Function
```python
    def heapify(arr, n, i):
        largest = i  # Initialize largest as root
        l = 2 * i + 1  # left = 2*i + 1
        r = 2 * i + 2  # right = 2*i + 2

        # See if left child of root exists and is greater than root
        if l < n and arr[i] < arr[l]:
            largest = l

        # See if right child of root exists and is greater than root
        if r < n and arr[largest] < arr[r]:
            largest = r

        # Change root, if needed
        if largest != i:
            arr[i], arr[largest] = arr[largest], arr[i]  # swap

            # Heapify the root.
            heapify(arr, n, largest)
```
- `heapify` ensures the subtree rooted at index `i` satisfies the max heap property.
- `largest` is initialized to `i` (the root).
- `l` and `r` are the indices of the left and right children of `i`.
- The function checks if the left child exists and is greater than the root.
- It then checks if the right child exists and is greater than the largest of the root and left child.
- If the largest is not the root, it swaps the root with the largest child and recursively calls `heapify` on the affected subtree.

#### Building the Max Heap
```python
    # Build a maxheap.
    # Since last parent will be at ((n//2)-1) we can start at that location.
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)
```
- This loop builds a max heap from the input array.
- It starts from the last parent node (at index `n // 2 - 1`) and calls `heapify` for each node up to the root.
- By the end of this loop, the largest element will be at the root of the heap (index 0).

#### Extracting Elements from the Heap
```python
    # One by one extract elements
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # swap
        heapify(arr, i, 0)
```
- This loop extracts the elements from the heap one by one.
- It swaps the root (the largest element) with the last element of the heap.
- It then calls `heapify` on the reduced heap (excluding the last element).
- By repeatedly moving the largest element to the end of the array and rebuilding the heap, the array becomes sorted.

### Summary
1. **Build a max heap** from the array.
2. **Repeatedly extract** the largest element from the heap, place it at the end of the array, and rebuild the heap with the remaining elements.

The result is a sorted array in ascending order.
"""

from typing import List


def main() -> None:
    """Driving code"""
    arr = [12, 11, 13, 5, 6, 7]
    print("Original array:", arr)
    heap_sort(arr)
    print("Sorted array:", arr)

    return None

def heap_sort(arr):
    """Heap sort algorithm."""
    n = len(arr)
    
    def heapify(arr, n, i):
        largest = i  # Initialize largest as root
        l = 2 * i + 1  # left = 2*i + 1
        r = 2 * i + 2  # right = 2*i + 2

        # See if left child of root exists and is
        # greater than root
        if l < n and arr[i] < arr[l]:
            largest = l

        # See if right child of root exists and is
        # greater than root
        if r < n and arr[largest] < arr[r]:
            largest = r

        # Change root, if needed
        if largest != i:
            arr[i], arr[largest] = arr[largest], arr[i]  # swap

            # Heapify the root.
            heapify(arr, n, largest)

    # Build a maxheap.
    # Since last parent will be at ((n//2)-1) we can start at that location.
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # One by one extract elements
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # swap
        heapify(arr, i, 0)

if __name__ == "__main__":
    main()
