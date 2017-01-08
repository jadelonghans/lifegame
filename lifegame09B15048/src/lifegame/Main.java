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
		
		BoardModel model = new BoardModel(13,15);
		//model.addListener(new ModelPrinter());
		
		ButtonClickListener ButtonListenerObj = new ButtonClickListener();
			
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
		model.addListener(new UndoStateToggleListener(UndoButton));	//add a Boardlistener to enable/disable UndoButton
		
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
		
		//Disable UndoButton in the beginning
		UndoButton.setEnabled(false);

	}
}
