import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

        int n = maze.length;
        List<String> result = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();

        if (maze[0][0] != 0 && maze[n - 1][n - 1] != 0) {
            MazeSolver.findPath(0, 0, maze, n, result, currentPath);
        }

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(String.join(" ", result));
        }
    }
}
