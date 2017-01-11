package lifegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import javax.swing.JPanel; 

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{

	private BoardModel model;
	
	private static final long serialVersionUID = -5739833203786797482L;
	
	private int X1, Y1;			// top left corner of gameboard (盤面)
	private int X2, Y2;			// the bottom right corner of gameboard
	private int cellUnit;		// dimension of each cell
	
	//a class to store a cell's data as one unit
	private class Cell{
		int col;
		int row;
	}
	//to store the last cell where mouse was pressed, or dragged
	private Cell prev_cell = new Cell();	
	
	public BoardView(BoardModel model){
		this.model = model;
		//registering the listeners 
		this.addMouseListener(this);		//same as "addMouseListener(this);"
		this.addMouseMotionListener(this);
	}
	
	@Override								
	public void paint(Graphics g){
		//call the paint() method from the superclass 
		//paint the background with default color: gray
		super.paint(g);				
		
		int defaultPadding = 15;			//padding on left, right and top sides of gameboard
		
		//calculate the actual size of space to draw gameboard on
		int boardHeight = this.getHeight() - 1*defaultPadding ; 
		int boardWidth = this.getWidth() - 2* defaultPadding;
		
//		size of rectangle cells to completely fill gameboard
		int cellHeight = boardHeight / model.getRows();
		int cellWidth = boardWidth / model.getCols();
		
//		to draw square cells, use the smaller value as side for square cells
		cellUnit = java.lang.Math.min(cellHeight, cellWidth);
		
//		calculation of top-left and bottom-right coordinates of gameboard
//		calculation to draw gameboard centered on the window
		
		X1 = defaultPadding + (boardWidth - model.getCols() * cellUnit)/ 2;
		Y1 = defaultPadding + (boardHeight - model.getRows() * cellUnit) / 2;
		X2 = X1 + model.getCols() * cellUnit;
		Y2 = Y1 + model.getRows() * cellUnit;
		
//		draw vertical lines
		for(int i= X1; i<= X2; i+= cellUnit  ){
			g.drawLine(i, Y1, i, Y2);
		}
		
//		draw horizontal lines
		for(int j= Y1; j<= Y2; j+= cellUnit  ){
			g.drawLine(X1, j, X2, j);
		}
		
//		to paint the living cells black
		for(int i = 0; i<model.getCols(); i++){
			for(int j = 0; j<model.getRows(); j++){
				if(model.isAlive(i, j)) 
					g.fillRect( X1 + (i)*cellUnit , Y1 + (j)*cellUnit, cellUnit, cellUnit);
			}
		}
	}

	/** Mouse action summary:
	 * 1. when mouse clicked, if the cursor coordinates are within a cell boundary, change state of that cell
	 * 2. when mouse dragged, if last event is not button click or drag within same cell, change cell state.
	 */
	
	private Cell mouseInsideCell(int cursorX, int cursorY){
		/* a method to return the cell of gameboard under the given coordinates (cursorX,cursorY). 
		 * returns null if coordinates are out of boundary of gameboard
		 */ 
		if( (cursorX < X1 || cursorX >= X2) || (cursorY < Y1 || cursorY >= Y2)) 
			return null;
		
		Cell current_cell = new Cell();

		//action on right edge and bottom edge lines of gameboard are ignored. 
		//MouseActions on left edge and top edge of a Cell are considered actions inside that cell 
		
		current_cell.col = (cursorX - X1)/cellUnit;
		current_cell.row = (cursorY - Y1)/cellUnit;		
		
		return current_cell;	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		//to return the cell on which mouse button was pressed
		Cell current_cell = mouseInsideCell(e.getX(), e.getY());
		
		//change cell state if mouse press was inside a cell
		if( current_cell != null)
			model.changeCellState(current_cell.col, current_cell.row);
		
		//update the last cell pressed
		prev_cell = current_cell;	
		this.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

		//to return the cell on which mouse dragging occurred
		Cell current_cell = mouseInsideCell(e.getX(), e.getY());
		
		//if mouse drag occurs in a new cell, change that cell state
		//prev_cell checked for null because it is accessed inside.
		if( current_cell != null && prev_cell !=null) {
			
			if(prev_cell.row != current_cell.row || prev_cell.col != current_cell.col){
				model.changeCellState(current_cell.col, current_cell.row);
				//update the last cell pressed
				prev_cell = current_cell; 	//updating here to make drag from outside gameboard invalid
			}
		}
		this.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
}
