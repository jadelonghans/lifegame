package lifegame;

import java.awt.Graphics;
import javax.swing.JPanel; 

public class BoardView extends JPanel{
	
	/**
	 * 
	 */
	BoardModel model;
	
	private static final long serialVersionUID = -5739833203786797482L;
	
	public BoardView(BoardModel model){
		this.model = model;
	}
	
	@Override								//use recommended
	public void paint(Graphics g){
		//call the paint() method from the superclass 
		//paint the background with default color: gray
		super.paint(g);				
		
		int X1, Y1;
		int X2, Y2;
		
		int defaultPadding = 15;			//padding on left, right and top sides of gameboard
		int bottomPadding = 40;				//padding on the bottom side of gameboard
		
		int boardHeight = this.getHeight() - bottomPadding -defaultPadding ;
		int boardWidth = this.getWidth() - 2* defaultPadding;
		
//		size of rectangle cells to completely fill gameboard
		int cellHeight = boardHeight / model.getRows();
		int cellWidth = boardWidth / model.getCols();
		
//		to draw square cells, use the smaller side of cells as side of square
		int cellUnit = java.lang.Math.min(cellHeight, cellWidth);
		
//		define the starting and end points of gameboard, in each case of window size
//		this calculation keeps gameboard centered on the window
		if(boardWidth > boardHeight ){
			X1 = (boardWidth - model.getCols() * cellUnit + 2*defaultPadding)/ 2;
			Y1 = defaultPadding;
			X2 = X1 + model.getCols() * cellUnit;
			Y2 = defaultPadding + model.getRows() * cellUnit;
		}
		else{
			X1 = defaultPadding;
			Y1 = (boardHeight - model.getRows() * cellUnit ) / 2;
			X2 = defaultPadding + model.getCols() * cellUnit;
			Y2 = Y1 + model.getRows() * cellUnit;
		}
		
//		draw horizontal lines
		for(int j= Y1; j<= Y2; j+= cellUnit  ){
			g.drawLine(X1, j, X2, j);
//			System.out.print(" "+ j );
		}

		System.out.println();
//		draw vertical lines
		for(int i= X1; i<= X2; i+= cellUnit  ){
			g.drawLine(i, Y1, i, Y2);
//			System.out.print(" "+ i );
		}
		
		
//		to paint the living cells black
		for(int i = 0; i<model.getRows(); i++){
			for(int j = 0; j<model.getCols(); j++){
				if(model.isAlive(i, j)) 
					g.fillRect( X1 + (j)*cellUnit , Y1 + (i)*cellUnit, cellUnit, cellUnit);
			}
		}
		
	}
	
	
	// function to convert cell number into window coordinates
	
	//private 
}
