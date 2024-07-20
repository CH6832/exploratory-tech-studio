#ifndef MAZESOLVER_H
#define MAZESOLVER_H

#include <iostream>
#include <vector>
#include <cmath>
#include <string>

using namespace std;

class MazeSolver {
private:
    const std::string DIRECTION = "DLRU";
    const std::vector<int> dr = { 1, 0, 0, -1 };
    const std::vector<int> dc = { 0, -1, 1, 0 };

    /**
    * @brief Checks if a cell is valid in the maze.
    *
    * @param row The row index of the cell.
    * @param col The column index of the cell.
    * @param n The size of the maze.
    * @param maze The maze represented as a 2D vector.
    * @return true if the cell is valid (within bounds and not blocked), false otherwise.
    */
    bool isValid(int row, int col, int n, const std::vector<std::vector<int>>& maze);
    
    /**
     * @brief Finds paths in the maze recursively.
     *
     * @param row The current row index.
     * @param col The current column index.
     * @param maze The maze represented as a 2D vector.
     * @param n The size of the maze.
     * @param result A vector to store the found paths.
     * @param currentPath The current path being explored.
     */
    void findPath(int row, int col, std::vector<std::vector<int>>& maze, int n, std::vector<std::string>& result, std::string& currentPath);

public:
    /**
     * @brief Finds all possible paths in the given maze.
     *
     * @param maze The maze represented as a 2D vector where 1 represents an open cell and 0 represents a blocked cell.
     * @return A vector containing all possible paths from the starting cell to the destination cell.
     */
    std::vector<std::string> findAllPaths(std::vector<std::vector<int>>& maze);
};

#endif // MAZESOLVER_H
