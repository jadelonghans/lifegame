package lifegame;

import javax.swing.JButton;

public class UndoStateToggleListener implements BoardListener{

	JButton UndoButton;
	
	//UndoButton passed when object of this class is created
	public UndoStateToggleListener(JButton button){
		UndoButton = button;
	}
	
	@Override
	public void updated(BoardModel model) {
		// Disable Undo button when undo is not possible
		
		if( !model.isUndoable()){
			System.out.println("Undo disabled");
			UndoButton.setEnabled(false);	
		}
		else UndoButton.setEnabled(true);
	}

}
