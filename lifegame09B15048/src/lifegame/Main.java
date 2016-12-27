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
		
		BoardModel model = new BoardModel(20,20);
		model.addListener(new ModelPrinter());
		ButtonClickListener ButtonListenerObj = new ButtonClickListener();
		
		//create object of next buttons and others here. then
		//add listeners of Next button and others here
	
			
		model.changeCellState(1, 1);
		
		model.changeCellState(2, 2);
		
		model.changeCellState(0, 3);
		
		model.changeCellState(1, 3);
		
		model.changeCellState(2, 3);
		
		model.changeCellState(4, 4);
		
		model.changeCellState(4, 4);
		System.out.println("d0ne");
		
		//create a new window
		JFrame frame = new JFrame();
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
		NextButton.setActionCommand("Next");
		
		JButton UndoButton = new JButton("Undo");
		buttonpanel.add(UndoButton, BorderLayout.CENTER);
		UndoButton.setActionCommand("Undo");
		
		JButton NewGameButton = new JButton("New Game");
		buttonpanel.add(NewGameButton, BorderLayout.CENTER);
		NewGameButton.setActionCommand("New Game");
		
		ButtonListenerObj.setModel(model);
		ButtonListenerObj.setView(view);
		NextButton.addActionListener(ButtonListenerObj);
		
		
		UndoButton.addActionListener(ButtonListenerObj);
		NewGameButton.addActionListener(ButtonListenerObj);
		
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
