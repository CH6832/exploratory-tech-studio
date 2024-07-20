#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""knutt_morris_pratt.py



.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from sys import maxsize
from itertools import permutations
from typing import List


def main() -> None:
    """Driving code and main function"""
    # Example: Search for occurrences of a pattern in a text
    text = "AABAACAADAABAAABAA"
    pattern = "AABA"
    occurrences = kmp_search(text, pattern)
    print("Occurrences of the pattern:", occurrences)
 
    return None


def kmp_search(text: str, pattern: str) -> List[int]:
    """Search for all occurrences of a pattern in a text using the Knuth-Morris-Pratt algorithm.

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

    # Compute the prefix function for the pattern
    prefix = compute_prefix_function(pattern)

    occurrences = []
    j = 0  # Index into the text
    k = 0  # Length of the longest prefix of pattern[:j] that is also a suffix of pattern[:j]

    while j < n:
        if text[j] == pattern[k]:
            j += 1
            k += 1
            if k == m:
                occurrences.append(j - m)
                k = prefix[k - 1]
        else:
            k = prefix[k - 1] if k > 0 else 0
            if k == 0:
                j += 1

    return occurrences


def compute_prefix_function(pattern: str) -> List[int]:
    """Compute the prefix function for a given pattern.

    Args:
        pattern (str): The pattern.

    Returns:
        List[int]: The prefix function for the pattern.
    """
    m = len(pattern)
    prefix = [0] * m
    k = 0

    for q in range(1, m):
        while k > 0 and pattern[k] != pattern[q]:
            k = prefix[k - 1]
        if pattern[k] == pattern[q]:
            k += 1
        prefix[q] = k

    return prefix


if __name__ == '__main__':
    main()

