package AGraphSearch;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class SolveMaze {

	public static void main(String[] args) {
		
		// Draw the maze
		JFrame f = new JFrame("Maze");
		f.setSize(1000, 1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Maze maze = new Maze();
		f.add(maze);
		f.setVisible(true);
		
		Graph g = maze.getGraph();
		
		List<Node> nodeVisited = new ArrayList<>();
		
		// New 
		JFrame f3 = new JFrame("Heuristic heatmap");
		f3.setSize(1000, 1000);
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawHeuristic d = new DrawHeuristic(maze);
		f3.add(d);
		f3.setVisible(true);
		
		
		AStar aStatSearchEngine = new AStar();
		nodeVisited = aStatSearchEngine.search(g,
		                 maze.getStartingPoint(), maze.getTargetPoint());
		
		//for (Node n : nodeVisited) {
		//	System.out.println("Node" +n.getElement().toString());
		//}
		
		System.out.println("A* Search is done!");
		
		FinalPath finalPath = new FinalPath(maze,nodeVisited);
		
		JFrame f4 = new JFrame("A*");
		f4.setSize(1000, 1000);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f4.add(finalPath);
		f4.setVisible(true);
		}
	
}
