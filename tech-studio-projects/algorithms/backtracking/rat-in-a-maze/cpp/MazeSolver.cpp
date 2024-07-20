#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include "MazeSolver.h"

using namespace std;

bool MazeSolver::isValid(int row, int col, int n, const vector<vector<int>>& maze) {
    return row >= 0 && row < n && col >= 0 && col < n && maze[row][col] == 1;
}

void MazeSolver::findPath(int row, int col, vector<vector<int>>& maze, int n, vector<string>& result, string& currentPath) {
    if (row == n - 1 && col == n - 1) {
        result.push_back(currentPath);
        return;
    }

    maze[row][col] = 0;

    for (int i = 0; i < 4; i++) {
        int nextRow = row + dr[i];
        int nextCol = col + dc[i];

        if (isValid(nextRow, nextCol, n, maze)) {
            currentPath.push_back(DIRECTION[i]);
            findPath(nextRow, nextCol, maze, n, result, currentPath);
            currentPath.pop_back();
        }
    }
    maze[row][col] = 1;
}

vector<string> MazeSolver::findAllPaths(vector<vector<int>>&maze) {
    int n = maze.size();
    vector<string> result;
    string currentPath;
    findPath(0, 0, maze, n, result, currentPath);
    return result;
}
