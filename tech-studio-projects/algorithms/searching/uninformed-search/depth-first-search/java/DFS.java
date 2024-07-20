package PathFinding_DFS;

import java.util.*;

public class DFS {
	private Stack<Node> myStack;
	private List<Node> nodeVisited; //
	private int startNodeIndex;//
	private int targetNodeIndex;//
	private boolean targetFound;//
	
	public DFS() {
		this.myStack = new Stack<>();  
		this.nodeVisited = new ArrayList<>(); //
		targetFound = false; //
	}
	
	public List<Node> dfs(Graph g , int startNodeIndex , int targetNodeIndex) { //
		List<Node> nodeList = g.getAllNodes(); //
		this.startNodeIndex = startNodeIndex; //
		this.targetNodeIndex = targetNodeIndex; //
		 
		dfsInStack(nodeList.get(this.startNodeIndex - 1)); //
		 
		return this.nodeVisited; //
	}

	private void dfsInStack(Node root) {
		 this.myStack.push(root);
		 root.setVisited(true); //
		 
		 while (this.myStack.isEmpty() == false) {
			 Node currentNode = this.myStack.pop();
			 
			 if ((int) currentNode.getElement() == this.targetNodeIndex  ) { //
				 this.targetFound = true; //
				 return; //
			 }else //
			 { //
				 this.nodeVisited.add(currentNode); //
			 } //
			 
			// System.out.println("Current node: " + currentNode.getElement().toString() );
			 
			 for (int i = currentNode.getNeighbours().size()-1 ; i >=0 ; i--) {
				 Node n = currentNode.getNeighbours().get(i);
				 
				 if (n.isVisited() == false) {
					 n.setVisited(true);
					 this.myStack.push(n);
				 }
			 }
			 
		 }
	}
}
