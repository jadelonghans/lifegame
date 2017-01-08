package lifegame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class Main implements Runnable{

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Main());	
	}
	
	public void run(){
		
		BoardModel model = new BoardModel(10,10);
		model.addListener(new ModelPrinter());
		ButtonClickListener ButtonListenerObj = new ButtonClickListener();
		
		//create object of next buttons and others here. then
		//add listeners of Next button and others here
	
/*			
		model.changeCellState(1, 1);
		
		model.changeCellState(2, 2);
		
		model.changeCellState(0, 3);
		
		model.changeCellState(1, 3);
		
		model.changeCellState(2, 3);
		
		model.changeCellState(4, 4);
		
		model.changeCellState(4, 4);
		System.out.println("d0ne");
*/		
		//create a new window
		JFrame frame = new JFrame("Lifegame");
		frame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
		
		//create a base panel for the window
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,400));
		frame.setMinimumSize(new Dimension(300,300));
		
		//set the rules to put GUI on top of 'base'
		base.setLayout(new BorderLayout()); 	
		BoardView view = new BoardView(model);
		//to set 'view' in the center of 'base'
		base.add(view, BorderLayout.CENTER);
		
		JPanel buttonpanel = new JPanel();
		base.add(buttonpanel, BorderLayout.SOUTH);
		buttonpanel.setLayout(new FlowLayout());
		
		JButton NextButton = new JButton("Next");
		buttonpanel.add(NextButton, BorderLayout.CENTER);
		NextButton.setActionCommand("next");
		
		JButton UndoButton = new JButton("Undo");
		buttonpanel.add(UndoButton, BorderLayout.CENTER);
		UndoButton.setActionCommand("undo");
		
		JButton NewGameButton = new JButton("New Game");
		buttonpanel.add(NewGameButton, BorderLayout.CENTER);
		NewGameButton.setActionCommand("newgame");
		
		//passing 'model' and 'view' objects to ButtonListenerObj
		ButtonListenerObj.setModel(model);
		ButtonListenerObj.setView(view);
		
		//connecting the buttons with their actions in ButtonClickListener 
		NextButton.addActionListener(ButtonListenerObj);
		UndoButton.addActionListener(ButtonListenerObj);
		NewGameButton.addActionListener(ButtonListenerObj);
		
		frame.pack();
		frame.setVisible(true);
		
		//to activate/deactivate Undo Button
		/**
		 * needs revision: to check whether the game has just started (count only mouse inputs)
		 * additionally, causes ArrayIndexOutOfBoundsException when Undo is not disabled when all Undo is done
		 */
		
		//unlike repaint, this is executed only once
		//to solve this problem, move this part somewhere else where update happens every time button is pressed
		System.out.println("Hello");
		if( !model.isUndoable()){
			System.out.println("Undo disabled");
			UndoButton.setEnabled(false);	
		}
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
