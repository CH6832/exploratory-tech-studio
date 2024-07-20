package PathFinding_DFS;

/*
** file: ABox.java
** purpose: A component that paints a red box.
*/
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
 
public class Maze extends JComponent {
	
	private int [][] maze = 
		{     {1,1,1,1,1,1,1,1,1,1,1,1,1},
	          {1,-1,1,0,1,0,1,0,0,0,0,0,1},
	          {1,0,1,0,0,0,1,0,1,1,1,0,1},
	          {1,0,0,0,1,1,1,0,0,0,0,0,1},
	          {1,1,1,0,0,0,0,0,1,1,1,0,1},
	          {1,0,1,1,1,1,1,0,1,0,0,0,1},
	          {1,0,1,0,1,0,0,0,1,1,1,0,1},
	          {1,0,1,0,1,1,1,0,1,0,1,0,1},
	          {1,0,0,0,0,0,0,0,0,0,1,9,1},
	          {1,1,1,1,1,1,1,1,1,1,1,1,1}
	        };
	
	private int[][] indexMatrix;
	private Node[][] nodesMatrix;
	private Graph g;
	
	private int colNo;
	private int rowNo;
	
	private int blockSize;
	
	public Maze() {
		this.blockSize = 40;
		this.rowNo = maze.length;
		this.colNo = maze[0].length;
		this.g = new Graph();
		nodesMatrix = new Node[this.rowNo][this.colNo];
		
		int idx = 1;
		for (int i = 0; i < this.rowNo; i++) {
            for (int j = 0; j < this.colNo; j++) {
            	//this.indexMatrix[i][j] = idx;
            	this.nodesMatrix[i][j] = new Node (idx);
            	idx++;
            }
        }
		
		createGraph();

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
 
		
		// draw the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                Color color;
                switch (maze[i][j]) {
                    case 1 : 
                    	color = Color.GRAY; 
                    	break;
                    case -1: // Start
                    	color = Color.RED;
                    	break;
                    case 9 : 
                    	color = Color.BLUE; 
                    	break;
                    default : 
                    	color = Color.WHITE;
                }
                g2.setColor(color);
                g2.fillRect(this.blockSize * j, this.blockSize * i, this.blockSize, this.blockSize);
                g2.setColor(Color.GREEN);
                g2.drawString(this.nodesMatrix[i][j].getElement()+ " ", this.blockSize * j + this.blockSize/2, this.blockSize * i + this.blockSize/2);
                g2.setColor(Color.BLACK);
                g2.drawRect(this.blockSize * j, this.blockSize * i, this.blockSize, this.blockSize);
            }
        } 
	}
	
	private void createGraph() {
		List<Node> allNodes = new ArrayList<>();
		
		for (int i = 0; i < this.rowNo; i++) {
            for (int j = 0; j < this.colNo; j++) {
            	if (this.maze[i][j] == 1) {
            		nodesMatrix[i][j].setVisited(true);
            	}
            	findAddNeighbours(i,j);
            	allNodes.add(nodesMatrix[i][j]);
            }
        }
		g.setAllNodes(allNodes);
	}
 

	private void findAddNeighbours(int  row, int col) {
		int colNum = col ;
	    int rowNum = row ;
        if(withinGrid (colNum+1, rowNum , this.rowNo, this.colNo)) {
        	nodesMatrix[row][col].addNeighbour(this.nodesMatrix[rowNum][colNum+1]); 
        }
        if(withinGrid (colNum, rowNum+1 , this.rowNo, this.colNo)) {
        	nodesMatrix[row][col].addNeighbour(this.nodesMatrix[rowNum+1][colNum]); 
        }
        if(withinGrid (colNum-1, rowNum , this.rowNo, this.colNo)) {
        	nodesMatrix[row][col].addNeighbour(this.nodesMatrix[rowNum][colNum-1]); 
        }
        if(withinGrid (colNum, rowNum-1 , this.rowNo, this.colNo)) {
        	nodesMatrix[row][col].addNeighbour(this.nodesMatrix[rowNum-1][colNum]); 
        }
	}
	                

	private boolean withinGrid(int colNum, int rowNum, int maxRow, int maxCol) {

	    if((colNum < 0) || (rowNum <0) ) {
	        return false;     
	    }
	    if((colNum >= maxCol) || (rowNum >= maxRow)) {
	        return false;     
	    }
	    return true;
	}

	public Graph getGraph() {
		return this.g;
	}
	
	public int getColNo() {
		return colNo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int[][] getMaze() {
		return maze;
	}
	
	public int getStartingPoint() {
		int idx = 1;
		for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
            	idx++;
            	if (maze[i][j] == -1) {
            		return idx-1;
            	}
            }
		}
		return 1;
	}
	
	public int getTargetPoint() {
		int idx = 1;
		for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
            	idx++;
            	if (maze[i][j] == 9) {
            		return idx-1;
            	}
            }
		}
		return 1;
	}
	
	
}



// Diagonal Movements 
//private void findAddNeighbours(int  row, int col) {
//	
//	//List<Integer> neighboursList = new ArrayList<>();
//    for (int colNum = col - 1 ; colNum <= (col + 1) ; colNum +=1  ) {
//        for (int rowNum = row - 1 ; rowNum <= (row + 1) ; rowNum +=1  ) {
//
//             //if not the center cell 
//            if(! ((colNum == col) && (rowNum == row))) {
//
//                //make sure it is within  grid
//                if(withinGrid (colNum, rowNum , this.rowNo, this.colNo)) {
//                	
//                	nodesMatrix[row][col].addNeighbour(this.nodesMatrix[rowNum][colNum]);
//                    //System.out.println("Neighbor of "+ col+ " "+ row + " - " + colNum +" " + rowNum );
//                }
//            }
//        }
//    }
//    
//
//}
