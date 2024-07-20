package AGraphSearch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawHeuristic extends JComponent { 
	
	private Maze maze;
	private Node[][] heuristicMatrix;
	
	public DrawHeuristic(Maze maze  ) {
		this.maze = maze;
		this.heuristicMatrix = maze.getNodeMatrix();
		 
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		int mazeColNo = maze.getColNo();
		int mazeRowNo = maze.getRowNo();
		
		
		int blockSize = maze.getBlockSize();
		
		for (int i = 0; i < mazeRowNo; i++) {
            for (int j = 0; j < mazeColNo; j++) {
                Color color = null;
                switch (maze.getMaze()[i][j]) {
                    case 1 : // Walls
                    	color = Color.GRAY; 
                    	break;
                }
                g2.setColor(color);
                g2.fillRect(blockSize * j, blockSize * i, 
				blockSize, blockSize);
                
                
                Color c = new Color((int) 
				this.heuristicMatrix[i][j].gethValue()*10 %255,0,0);
				
                g2.setColor(c);
                g2.fillRect(blockSize * j, blockSize * i, 
				blockSize, blockSize);
                
                g2.setColor(Color.WHITE);
                g2.drawString(Math.round(
				this.heuristicMatrix[i][j].gethValue()) + "", 
				blockSize * j + blockSize/2, blockSize * i + blockSize/2);
            }
        }
	}
}
