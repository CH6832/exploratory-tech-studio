def levenshtein_distance(s1, s2):
    # Create a matrix to store the distances
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Initialize the matrix
    for i in range(m + 1):
        dp[i][0] = i
    for j in range(n + 1):
        dp[0][j] = j

    # Fill in the rest of the matrix
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            cost = 0 if s1[i - 1] == s2[j - 1] else 1
            dp[i][j] = min(
                dp[i - 1][j] + 1,    # Deletion
                dp[i][j - 1] + 1,    # Insertion
                dp[i - 1][j - 1] + cost  # Substitution
            )

    # The bottom-right cell contains the Levenshtein distance
    return dp[m][n]

# Example usage
s1 = "kitten"
s2 = "sitting"
distance = levenshtein_distance(s1, s2)
print(f"The Levenshtein distance between '{s1}' and '{s2}' is {distance}.")
