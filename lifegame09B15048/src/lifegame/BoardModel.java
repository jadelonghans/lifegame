package lifegame;

import java.util.ArrayList;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;
	final int MAX_HISTORY_ENTRIES = 32;	//maximum undo possible
	
	private	ArrayList<BoardListener> listeners;
	private ArrayList<boolean [][]> history;
	
	public BoardModel(int c, int r){
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		
		listeners = new ArrayList<BoardListener>();
		history = new ArrayList<boolean [][]>();
	}
	
	public int getCols(){
		return cols;
	}
	
	public int getRows(){
		return rows;
	}
	
	public void printForDebug(){
		//print the state of gameboard
		for(int r=0; r<rows; r++){
			for(int c=0; c<cols; c++){
				if(cells[r][c] == false)
					System.out.print(".");
				else System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*//for New Game button
	public void clearAll(){
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<cols; j++){
				cells[i][j] = false;
			}
		}
		history.clear();
		//history = new ArrayList<boolean [][]>();
		this.fireUpdate();
	}*/
	
	// change the state of a given cell, x=col,y=row
	public void changeCellState(int x,int y){
		
		boolean[][] cellForUndo = new boolean [rows][cols];
		//copy the current cells[][] to cellForUndo
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<cols; j++){
				cellForUndo[i][j] = cells[i][j];
			}
		}
		
		//if the history has maximum no. of entries already, delete the oldest entry
		if(history.size() == MAX_HISTORY_ENTRIES )
			history.remove(0); //history.size()-1);

		//add the current generation to the history list as first element
		history.add(cellForUndo);
		
		//to toggle cell state between true and false
		cells[y][x] = (cells[y][x]==true)? false : true;
		
		this.fireUpdate();
	}
	
	public boolean isAlive(int x, int y){
		return cells[x][y];
	}
	
	public void addListener(BoardListener listener){
		listeners.add(listener);
	}
	
	public void fireUpdate(){
		for(BoardListener listener: listeners){
			listener.updated(this);
		}
	}
	
	//to calculate the next generation from current generation
	public void next(){
		boolean[][] nextgen = new boolean [rows][cols];
		
		//check in rows[][] and write in new array 'nextgen[][]'
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				nextgen[i][j] = liveOrDie(i,j);
			}
		}
		
		//if the history has 32 entries already, delete the oldest entry
		if(history.size() == MAX_HISTORY_ENTRIES )
			history.remove(0); //history.size()-1);
		
		//add the current generation to the history list as first element
		history.add(cells);
		
		//replace the original array with the array of next generation
		cells = nextgen;	 
		this.fireUpdate();
	}
	
	//called by next()
	private boolean liveOrDie(int x, int y){
		
		int count = 0;
		
		if(x!=0){	//cell on top
			if(cells[x-1][y]== true) count++;
		}
		if(y!=0){	//cell on left
			if(cells[x][y-1]== true) count++;
		}
		if(y!=0 && x!=0){	//cell on top-left
			if(cells[x-1][y-1]== true) count++;
		}
		if(x!=(rows-1)){	//cell on bottom
			if(cells[x+1][y]== true) count++;
		}
		if(y!=(cols-1)){	//cell on right
			if(cells[x][y+1]== true) count++;
		}
		if(y!=0 && x!=(rows-1)){	//cell on bottom left
			if(cells[x+1][y-1]== true) count++;
		}
		if(y!=(cols-1) && x!=(rows-1)){	//cell on bottom right
			if(cells[x+1][y+1]== true) count++;
		}
		if(y!=(cols-1) && x!=0){	//cell on top right
			if(cells[x-1][y+1]== true) count++;
		}
	
		//living cells need 2 or 3 alive cells to stay alive
		if(cells[x][y] == true){
			if(count == 2 || count == 3)
				return true;
		}
		
		//dead cells need 3 alive cells to go alive
		//(cells[x][y] == false){
		else{			
			if (count == 3)
				return true;
		}
		
		/*living cells die when no. of cells alive >3 or <2
		dead cells remain dead when no. of cells alive !=3
		both case handled by return 0;
		*/
		return false;	
	}
	
	//this method assumes the object isUndoable
	public void undo(){
			
		//pop the last history entry, while removing from history ArrayList
		cells = history.get(history.size()-1);
		history.remove(cells);
		
		//update the cells on the screen
		//System.out.println("contents of history: " + history);
		this.fireUpdate();
	}
	
	//to check if the object can be Undo'ed.
	public boolean isUndoable(){
		return ( !history.isEmpty());
	}
}
