package lifegame;

public class Main {

	public static void main(String[] args) {
		
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
		
		for(int i=1; i<12; ++i){
			model.next();
		}
		
		System.out.println("Undo operation start.");
		
		while(model.isUndoable())
		{
		
			model.undo();
		}
		
	}
}
