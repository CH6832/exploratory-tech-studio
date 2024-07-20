#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""boyer_moore.py

The Boyer-Moore algorithm is a powerful string searching algorithm known for its efficiency in practice. Here's how the algorithm works:

    Preprocessing: Before searching, we preprocess the pattern to create a "last occurrence" table, which stores the rightmost occurrence of each character in the pattern.

    Searching: We start searching the text from the end of the pattern towards the beginning. At each step, we compare characters from right to left:
        If there is a match, we continue comparing characters until the entire pattern matches the corresponding substring in the text.
        If there is a mismatch, we use the information from the last occurrence table to determine how far we can shift the pattern.

    Termination: We continue this process until we reach the end of the text or find all occurrences of the pattern.

In the example usage, we demonstrate how to search for occurrences of a pattern "AABA" in a text "AABAACAADAABAAABAA" using the Boyer-Moore algorithm. We call the boyer_moore_search function to obtain a list of indices where the pattern starts in the text. Adjustments to the text and pattern can be made based on the specific problem being solved.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from sys import maxsize
from itertools import permutations
from typing import List

V = 4


def main() -> None:
    """Driving code and main function"""
    # Example: Search for occurrences of a pattern in a text
    text = "AABAACAADAABAAABAA"
    pattern = "AABA"
    occurrences = boyer_moore_search(text, pattern)
    print("Occurrences of the pattern:", occurrences)
 
    return None


def boyer_moore_search(text: str, pattern: str) -> List[int]:
    """Search for all occurrences of a pattern in a text using the Boyer-Moore algorithm.

    Args:
        text (str): The text to search in.
        pattern (str): The pattern to search for.

    Returns:
        List[int]: A list of indices where the pattern starts in the text.
    """
    if not text or not pattern:
        return []

    n, m = len(text), len(pattern)
    if m > n:
        return []

    last_occurrence = {char: i for i, char in enumerate(pattern)}

    occurrences = []
    i = m - 1  # Start at the end of the pattern
    j = m - 1  # Match characters from right to left

    while i < n:
        if text[i] == pattern[j]:
            if j == 0:
                occurrences.append(i)
                i += m * 2 - 1  # Skip the rest of the block where the pattern occurs
            else:
                i -= 1
                j -= 1
        else:
            if text[i] in last_occurrence:
                i += m - min(j, 1 + last_occurrence[text[i]])
            else:
                i += m
            j = m - 1

    return occurrences


if __name__ == '__main__':
    main()

