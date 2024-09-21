#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""bucket_sort.py

Bucket sort is a sorting algorithm that works by distributing the elements of an array
into several groups, called buckets. Each bucket is then sorted individually, either
using a different sorting algorithm or recursively applying the bucket sort. Finally,
the sorted buckets are concatenated to form the final sorted array. This algorithm is
particularly useful when the input is uniformly distributed over a range.


Steps of Bucket Sort:
    Initialization:
        Determine the number of buckets you will use. Typically, this is chosen to be the same as the number of elements in the array for simplicity.
        Create these empty buckets (which can be represented as lists).

    Distribution:
        Iterate over the original array and distribute each element into its corresponding bucket.
        The bucket for each element is determined based on a function of the element's value, ensuring that elements are spread out among the buckets in a way that reflects their order.

    Sorting Individual Buckets:
        Once all elements are distributed into buckets, sort each bucket individually.
        This sorting can be done using any suitable sorting algorithm (like insertion sort, which is efficient for small datasets typically found in each bucket).

    Concatenation:
        Finally, concatenate all the sorted buckets to produce the final sorted array.


Detailed Example:

Consider an array of n floating-point numbers uniformly distributed in the range [0, 1).

Step-by-step:

    Initialization:
        Suppose our array is [0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68].
        Create n empty buckets: buckets = [[], [], [], [], [], [], [], [], [], []].

    Distribution:
        For each element in the array, calculate its bucket index. This is typically index = int(num * n) where num is the element and n is the number of buckets.
        Place the element into the appropriate bucket:
            0.78 goes into bucket 7 (since int(0.78 * 10) = 7).
            0.17 goes into bucket 1.
            And so on, until all elements are distributed.

    Sorting Individual Buckets:
        Each bucket is sorted individually. After sorting, buckets might look like this:
            Bucket 0: [0.12]
            Bucket 1: [0.17, 0.21, 0.23]
            Bucket 2: [0.26]
            Bucket 3: [0.39]
            Bucket 4: []
            Bucket 5: []
            Bucket 6: [0.68]
            Bucket 7: [0.72, 0.78]
            Bucket 8: []
            Bucket 9: [0.94]

    Concatenation:
        Combine all sorted buckets to form the final sorted array:
            [0.12, 0.17, 0.21, 0.23, 0.26, 0.39, 0.68, 0.72, 0.78, 0.94].
"""

def main() -> None:
    """Driving code."""
    arr = [0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68]
    sorted_arr = bucket_sort(arr)
    print(sorted_arr)

    return None

def bucket_sort(arr):
    """Bucket sort algorithm."""
    
    num_buckets = len(arr)
    buckets = [[] for _ in range(num_buckets)]

    print("Initial array:", arr)
    
    # place each element in the appropriate bucket based on its value
    for num in arr:
        index = int(num * num_buckets)
        buckets[index].append(num)
        print(f"Element {num} -> Bucket {index}")

    print("\nBuckets after distribution:")
    for i, bucket in enumerate(buckets):
        print(f"Bucket {i}: {bucket}")

    # sort each bucket individually
    for bucket in buckets:
        bucket.sort()

    print("\nBuckets after sorting:")
    for i, bucket in enumerate(buckets):
        print(f"Bucket {i}: {bucket}")

    # concatenate all buckets into the original array
    sorted_array = []
    for bucket in buckets:
        sorted_array.extend(bucket)

    return sorted_array

if __name__ == "__main__":
    main()
