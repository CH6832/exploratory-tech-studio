package PathFinding_DFS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class SolveMaze {

	public static void main(String[] args) {
		
		// Draw the maze
		JFrame f = new JFrame("Maze");
		f.setSize(600, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Maze maze = new Maze();
		f.add(maze);
		f.setVisible(true);
		
		Graph g = maze.getGraph();
		DFS defSearchEngine = new DFS();
		
		List<Node> nodeVisited = new ArrayList<>();
		nodeVisited = defSearchEngine.dfs(g , maze.getStartingPoint(), maze.getTargetPoint());
		
		for (Node n : nodeVisited) {
			System.out.println("Node" +n.getElement().toString());
		}
		
		System.out.println("Search is done!");
		
		FinalPath finalPath = new FinalPath(maze,nodeVisited);
		
		JFrame f2 = new JFrame("Path to the goal");
		f2.setSize(600, 500);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.add(finalPath);
		f2.setVisible(true);
		}
}
