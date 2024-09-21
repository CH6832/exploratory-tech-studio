# Levenshtein Distance Algorithm

## Overview

The Levenshtein Distance, also known as Edit Distance, is a metric used to measure the difference between two sequences. It is defined as the minimum number of single-character edits (insertions, deletions, or substitutions) required to transform one string into another. This algorithm is widely used in various applications such as spell checking, DNA sequence comparison, and natural language processing.

## Theoretical Background

### Definition

Given two strings, `s1` and `s2`, the Levenshtein Distance between `s1` and `s2` is the minimum number of operations needed to convert `s1` into `s2`. The allowed operations are:

1. **Insertion**: Adding a single character to `s1`.
2. **Deletion**: Removing a single character from `s1`.
3. **Substitution**: Replacing one character in `s1` with another character.

### Dynamic Programming Approach

The Levenshtein Distance algorithm uses a dynamic programming approach to solve the problem efficiently. This method involves breaking the problem down into simpler subproblems and solving each subproblem only once, storing the results to avoid redundant calculations.

1. **Matrix Representation**: 
   - Define a matrix `dp` where `dp[i][j]` represents the Levenshtein Distance between the first `i` characters of `s1` and the first `j` characters of `s2`.

2. **Initialization**:
   - `dp[i][0]` represents the cost of converting the first `i` characters of `s1` into an empty string, which is `i`.
   - `dp[0][j]` represents the cost of converting an empty string into the first `j` characters of `s2`, which is `j`.

3. **Matrix Filling**:
   - For each cell `(i, j)`, compute the cost based on the following operations:
     - **Insertion**: `dp[i][j-1] + 1`
     - **Deletion**: `dp[i-1][j] + 1`
     - **Substitution**: `dp[i-1][j-1] + cost`, where `cost` is 0 if the characters at the current positions are the same, otherwise 1.
   - Update `dp[i][j]` with the minimum cost among these operations.

4. **Result**:
   - The value at `dp[m][n]` (where `m` and `n` are the lengths of `s1` and `s2`, respectively) represents the Levenshtein Distance between the two strings.

## Mathematical Background

The Levenshtein Distance can be computed using the following recurrence relation:

\[ dp[i][j] = \min \left\{
  \begin{array}{ll}
  dp[i-1][j] + 1 & \text{(deletion)} \\
  dp[i][j-1] + 1 & \text{(insertion)} \\
  dp[i-1][j-1] + cost & \text{(substitution)}
  \end{array}
\right. \]

Where:
- \( cost = 0 \) if \( s1[i-1] = s2[j-1] \)
- \( cost = 1 \) if \( s1[i-1] \neq s2[j-1] \)

### Time Complexity

The time complexity of the algorithm is \( O(m \times n) \), where \( m \) and \( n \) are the lengths of the two strings. This is because each cell in the matrix is computed once, and there are \( m \times n \) cells.

### Space Complexity

The space complexity is \( O(m \times n) \) due to the matrix used for storing the distances. However, space optimization techniques can reduce this to \( O(\min(m, n)) \) using a rolling array approach.

## Example

Consider the strings `s1 = "kitten"` and `s2 = "sitting"`. The Levenshtein Distance between these two strings is 3, as it requires 3 operations (substitution of 'k' with 's', substitution of 'e' with 'i', and insertion of 'g') to convert "kitten" into "sitting".
