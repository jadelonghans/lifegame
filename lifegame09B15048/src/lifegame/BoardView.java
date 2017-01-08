package lifegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.Graphics;

import javax.swing.JPanel; 

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	BoardModel model;
	
	private static final long serialVersionUID = -5739833203786797482L;
	
	int X1, Y1;			// top left corner of gameboard (盤面)
	int X2, Y2;			// the bottom right corner of gameboard
	int cellUnit;		// dimension of each cell
	
	//sub class to store cell data as one unit
	private class Cell{
		int row;
		int col;
	}
	
	private Cell prev_cell = new Cell();	//to store the last cell where mouse was pressed, or dragged
	
	public BoardView(BoardModel model){
		this.model = model;
		//registering the listeners 
		this.addMouseListener(this);		//equivalent to just writing addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override								//use recommended
	public void paint(Graphics g){
		//call the paint() method from the superclass 
		//paint the background with default color: gray
		super.paint(g);				
		
		int defaultPadding = 15;			//padding on left, right and top sides of gameboard
		//int bottomPadding = 40;				//padding on the bottom side of gameboard
		
		int boardHeight = this.getHeight() - 1*defaultPadding ; //- bottomPadding --previous was 2*defaultPadding 
		int boardWidth = this.getWidth() - 2* defaultPadding;
		
//		size of rectangle cells to completely fill gameboard
		int cellHeight = boardHeight / model.getRows();
		int cellWidth = boardWidth / model.getCols();
		
//		to draw square cells, use the smaller side of cells as side of square
		cellUnit = java.lang.Math.min(cellHeight, cellWidth);
		
//		define the starting and end points of gameboard, in each case of window size
//		this calculation keeps gameboard centered on the window
		/*starting coordinates: default Padding + half of the gap resulting
		 * due to adopting smaller side of other dimension(height/width) as cellUnit
		 * for the dimension whose side is adopted as cellUnit, X1 or Y1 reduces to simple defaultPadding
		 * 
		 * when cellHeight = cellWidth, X1=Y1= defaultPadding, making the resize of gameboard smooth.		
		 */
		X1 = defaultPadding + (boardWidth - model.getCols() * cellUnit)/ 2;
		Y1 = defaultPadding + (boardHeight - model.getRows() * cellUnit) / 2;//defaultPadding;
		X2 = X1 + model.getCols() * cellUnit;
		Y2 = Y1 + model.getRows() * cellUnit;
		
		System.out.println("X2: " + X2);
		System.out.println("Y2: " + Y2);
		
//		draw horizontal lines
		for(int j= Y1; j<= Y2; j+= cellUnit  ){
			g.drawLine(X1, j, X2, j);
//			System.out.print(" "+ j );
		}
		
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

	/**
	 * TODO 
	 * 1. when mouse clicked, if the cursor coordinates are within a cell boundary, change state of that cell
	 * 2. when mouse dragged, if  last event is not button click or drag within same cell, change cell state.
	 * 
	 * 
	 */
	
	private Cell mouseInsideCell(int cursorX, int cursorY){
		/*
		 * a method to return the cell of gameboard under the coordinates (cursorX,cursorY). 
		 * return null if coordinates are out of boundary of gameboard
		 */ 
		if( (cursorX <= X1 || cursorX > X2) || (cursorY <= Y1 || cursorY > Y2)) 
			return null;
		
		int i,j;
		Cell current_cell = new Cell();

		//NOTE: action on left edge and top edge lines of gameboard are ignored. 
		//MouseActions on right edge and bottom edge of a Cell is considered inside that cell 
		
		for( i= X1; i<X2; i+= cellUnit){
			if(cursorX > i && cursorX <= (i+cellUnit)) 
				break;
		}
		
		for( j= Y1; j<Y2; j+= cellUnit){
			if(cursorY > j && cursorY <= (j+cellUnit)) 
				break;
		}
		
		current_cell.row = i/cellUnit;
		current_cell.col = j/cellUnit;
		
		System.out.println("inside cell " + current_cell.row + current_cell.col  );
		return current_cell;	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.err.println("Pressed: " + e.getX() + " , " + e.getY());
		
		//insert a method call that takes the coordinates and changes a cell state if applicable
		Cell current_cell = mouseInsideCell(e.getX(), e.getY());
		
		if( current_cell != null)
			model.changeCellState(current_cell.row, current_cell.col);
		
		prev_cell = current_cell;	
		this.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// if e.getX() and e.getY() are within a cell, return that cell as Cell object
		Cell current_cell = mouseInsideCell(e.getX(), e.getY());
		
		//prev_cell checked null because it is accessed inside.
		if( current_cell != null && prev_cell !=null) {
			
			if(prev_cell.row == current_cell.row && prev_cell.col == current_cell.col){
				System.out.println("same cell");
			}
			
			else{
				model.changeCellState(current_cell.row, current_cell.col);
				prev_cell = current_cell; 	//placing here causes dragging from outside gameboard invalid
			}
		}
		
		this.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
 
}
