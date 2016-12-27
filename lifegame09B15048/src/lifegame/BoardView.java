package lifegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import java.awt.Graphics;
import javax.swing.JPanel; 

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	BoardModel model;
	
	private static final long serialVersionUID = -5739833203786797482L;
	
	public BoardView(BoardModel model){
		this.model = model;
		//registering the listeners 
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override								//use recommended
	public void paint(Graphics g){
		//call the paint() method from the superclass 
		//paint the background with default color: gray
		super.paint(g);				
		
		int X1, Y1;
		int X2, Y2;
		
		int defaultPadding = 15;			//padding on left, right and top sides of gameboard
		//int bottomPadding = 40;				//padding on the bottom side of gameboard
		
		int boardHeight = this.getHeight() - 1*defaultPadding ; //- bottomPadding --previous was 2*defaultPadding 
		int boardWidth = this.getWidth() - 2* defaultPadding;
		
//		size of rectangle cells to completely fill gameboard
		int cellHeight = boardHeight / model.getRows();
		int cellWidth = boardWidth / model.getCols();
		
//		to draw square cells, use the smaller side of cells as side of square
		int cellUnit = java.lang.Math.min(cellHeight, cellWidth);
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
 
}
