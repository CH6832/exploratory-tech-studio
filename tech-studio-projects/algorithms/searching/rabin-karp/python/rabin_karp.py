#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""rabin_karp.py



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
    occurrences = rabin_karp_search(text, pattern)
    print("Occurrences of the pattern:", occurrences)
 
    return None


def rabin_karp_search(text: str, pattern: str) -> List[int]:
    """Search for all occurrences of a pattern in a text using the Rabin-Karp algorithm.

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

    # Calculate the hash value of the pattern and the first window of the text
    pattern_hash = hash(pattern)
    text_hash = hash(text[:m])

    occurrences = []

    for i in range(n - m + 1):
        if text_hash == pattern_hash and text[i:i+m] == pattern:
            occurrences.append(i)
        if i < n - m:
            # Update the hash value for the next window
            text_hash = (text_hash - ord(text[i])) + ord(text[i+m])

    return occurrences


if __name__ == '__main__':
    main()

