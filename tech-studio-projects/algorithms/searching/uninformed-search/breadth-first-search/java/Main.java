
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		// Prepare the graph 
		Graph g = new Graph();
		
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		
		n1.addNeighbour(n2);
		n1.addNeighbour(n3);
		n2.addNeighbour(n4);
		n2.addNeighbour(n5);
		n3.addNeighbour(n6);
		n3.addNeighbour(n7);
		
		List<Node> allNodes = new ArrayList<>();
		allNodes.add(n1);
		allNodes.add(n2);
		allNodes.add(n3);
		allNodes.add(n4);
		allNodes.add(n5);
		allNodes.add(n6);
		allNodes.add(n7);
		
		g.setAllNodes(allNodes);
		
		// BFS the graph
		BFS dfsSearchEngine = new BFS();
		dfsSearchEngine.bfs(g);
		
	}

}
