import java.util.List;

public class MazeSolver {

    // D -> Down, L -> Left, R -> Right, U -> Up
    static final String DIRECTION = "DLRU";

    // Arrays to represent change in rows and columns.
    static final int[] dr = {1, 0, 0, -1};
    static final int[] dc = {0, -1, 1, 0};

    static boolean isValid(int row, int col, int n, int[][] maze) {
        return row >= 0 && row < n && col >= 0 && col < n && maze[row][col] == 1;
    }

    static void findPath(int row, int col, int[][] maze, int n, List<String> result, StringBuilder currentPath) {
        if (row == n - 1 && col == n - 1) {
            result.add(currentPath.toString());
            return;
        }

        maze[row][col] = 0;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (isValid(nextRow, nextCol, n, maze)) {
                currentPath.append(DIRECTION.charAt(i));
                findPath(nextRow, nextCol, maze, n, result, currentPath);
                currentPath.deleteCharAt(currentPath.length() - 1);
            }
        }

        maze[row][col] = 1;
    }
}
