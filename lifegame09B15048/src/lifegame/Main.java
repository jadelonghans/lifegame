package lifegame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;


public class Main implements Runnable{

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Main());	
	}
	
	public void run(){
		
		BoardModel model = new BoardModel(10,10);
		model.addListener(new ModelPrinter());
		
		//model.printForDebug();
			
		model.changeCellState(1, 1);
		//model.printForDebug();
		
		model.changeCellState(2, 2);
		//model.printForDebug();
		
		model.changeCellState(0, 3);
		//model.printForDebug();
		
		model.changeCellState(1, 3);
		//model.printForDebug();
		
		model.changeCellState(2, 3);
		//model.printForDebug();
		
		model.changeCellState(4, 4);
		//model.printForDebug();
		
		model.changeCellState(4, 4);
		System.out.println("d0ne");
		
		//create a new window
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
		
		//create a base panel for the window
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,300));
		frame.setMinimumSize(new Dimension(300,200));
		
		frame.pack();
		frame.setVisible(true);
/*		
		for(int i=1; i<12; ++i){
			model.next();
		}
		
		System.out.println("Undo operation start.");
		
		while(model.isUndoable())
		{
		
			model.undo();
		}
*/
	}
}
