/*
Description:
------------
This program explores all possible paths in a maze using backtracking. It starts from the top-left corner
of the maze and recursively explores all valid paths until it reaches the bottom-right corner. The is_valid
function checks whether a given cell is inside the maze and unblocked. The find_path function recursively
explores all valid directions from the current cell and backtracks when necessary. Finally, the main function
initializes the maze and calls the find_path function to find all valid paths.

Compile the program:
--------------------
Step 1: Compile each .cpp file into object files
    g++ -c main.cpp -o main.o
    g++ -c MazeSolver.cpp -o MazeSolver.o
Step 2: Link all object files together to create an executable
    g++ main.o MazeSolver.o -o main
*/

#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include "MazeSolver.h"

using namespace std;

int main() {
    vector<vector<int>> maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {1, 1, 0, 0},
        {0, 1, 1, 1}
    };

    MazeSolver solver; // Create an instance of MazeSolver

    vector<string> result = solver.findAllPaths(maze); // Use the findAllPaths method

    if (result.empty()) {
        cout << -1 << endl;
    }
    else {
        for (const string& path : result) {
            cout << path << " ";
        }
        cout << endl;
    }

    return 0;
}