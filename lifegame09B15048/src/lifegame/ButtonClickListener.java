package lifegame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonClickListener implements ActionListener{
	
	//following block of code pulls the object 'model' created in ?
	//it gets Boardmodel object from setModel() as argument from the caller function
	int c;
	int r;
	BoardModel model = new BoardModel(c,r);
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public BoardModel getModel() {
		return model;
	}
	public void setModel(BoardModel model) {
		this.model = model;
	}
	
	BoardView view = new BoardView(model);
	
	public BoardView getView() {
		return view;
	}
	public void setView(BoardView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if(command.equals("Next")){
			model.next();
			view.repaint();
		}
		else if (command.equals("Undo")){
		
			model.undo();
			view.repaint();
			
		}
		else {				//command is "New Game"
			
		}
		
	}
	
}
