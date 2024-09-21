#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""rat_in_a_maze.py

This program explores all possible paths in a maze using backtracking. It starts from the top-left corner
of the maze and recursively explores all valid paths until it reaches the bottom-right corner. The is_valid
function checks whether a given cell is inside the maze and unblocked. The find_path function recursively
explores all valid directions from the current cell and backtracks when necessary. Finally, the main function
initializes the maze and calls the find_path function to find all valid paths.

.. _PEP 484:
    https://www.python.org/dev/peps/pep-0484/


"""


from typing import List


# D -> Down
# L -> Left
# R -> Right
# U -> Up
DIRECTION = "DLRU"

# arrays to represent change in rows and columns
dr: list = [1, 0, 0, -1]
dc: list = [0, -1, 1, 0]


def main() -> None:
    "Driver code and program entry point."
    maze: List[list] = [
        [1, 0, 0, 0],
        [1, 1, 0, 1],
        [1, 1, 0, 0],
        [0, 1, 1, 1]
    ]

    # get the length of the maze
    n: int = len(maze)
    
    # list to store all valid paths
    result: list = []
    
    # Store current path
    current_path = ""

    if maze[0][0] != 0 and maze[n - 1][n - 1] != 0:
        # Function call to get all valid paths
        find_path(0, 0, maze, n, result, current_path)

    if not result:
        print(-1)
    else:
        print(" ".join(result))

    return None


def is_valid(row: int, col: int, n: int, maze: List[List[int]]) -> bool:
    """Check if cell(row, col) is inside the maze and unblocked.

    Keyword arguments:
    row (int) -- The row index of the cell.
    col (int) -- The column index of the cell.
    n (int) -- The size of the maze (number of rows/columns).
    maze (List[List[int]]) -- The maze represented as a 2D array.
    """

    return 0 <= row < n and 0 <= col < n and maze[row][col] == 1


def find_path(row, col, maze, n, ans, current_path) -> None:
    """Get all valid paths to escape the maze using backtracking.

    Keyword arguments:
    row (int) -- The current row index.
    col (int) -- The current column index.
    maze (List[List[int]]) -- The maze represented as a 2D array.
    n (int) -- The size of the maze (number of rows/columns).
    ans (List[str]) -- A list to store all valid paths.
    current_path (str) -- The current path being explored.  
    """
    # if bottom right cell is reached, add current path to ans and return
    if row == n - 1 and col == n - 1:
        ans.append(current_path)
        return
    
    # mark current cell as blocked
    maze[row][col] = 0

    for i in range(4):
        
        # find the next row based on the current row (row)
        next_row = row + dr[i]
        
        # find the next column based on the current column (col) and the dc[] array
        next_col = col + dc[i]

        # check if the next cell is valid or not
        if is_valid(next_row, next_col, n, maze):
            current_path += DIRECTION[i]
            
            # recursively call the find_path function for
            # the next cell
            find_path(next_row, next_col, maze, n, ans, current_path)
            
            # remove the last direction when backtracking
            current_path = current_path[:-1]

    # mark the current cell as unblocked
    maze[row][col] = 1

    return None


if __name__ == '__main__':
    main()
