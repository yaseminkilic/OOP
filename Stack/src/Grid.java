

public class Grid extends GetInputFromFile {
	
	private int gridRowNumber;
	private int gridColoumNumber;
	static int rowNumber = 10;  // variable holds value of the row, likewise m.
	static int colNumber = 10;  // variable holds value of the column, likewise n.
	
	public int getGridRowNumber() {
		return gridRowNumber;
	}
	public void setGridRowNumber(int gridRowNumber) throws Exception {
		if( gridRowNumber > 0 && gridRowNumber <= 10 )
			this.gridRowNumber = gridRowNumber;
		else
			throw new Exception("Grid number is invalid !!!");
	}
	public int getGridColoumNumber() {
		return gridColoumNumber;
	}
	public void setGridColoumNumber(int gridColoumNumber) throws Exception {
		if( gridColoumNumber > 0 && gridColoumNumber <= 10 )
			this.gridColoumNumber = gridColoumNumber;
		else
			throw new Exception("Grid number is invalid !!!");
	}
	
	public void initialBoard(){ // to initialize the board
		
		for(int i=0 ; i<gridRowNumber ; i++) {
			for(int j=0 ; j<gridColoumNumber ; j++){
				if (  board[i][j] == 1 || board[i][j] == 2 )
					continue;
				board[i][j] = 0;
			}
		}
	}
	
	public void printBoard() // to print board
	{
		
		System.out.println("\nC : Locations Of Cameras\nB : Locations Of Bloks\n* : Locations where the cameras can see\n");
		rowNumber = Integer.parseInt(m);
		colNumber = Integer.parseInt(n);
		System.out.print("  |  ");
		for (int k=0 ; k<gridColoumNumber ; k++)
		{
			if( k<10)
				System.out.print(k+"  |  ");
			if( k==10 )
				System.out.print(k+" | ");
		}
		
		System.out.println("\n   ____________________________________________________________\n");
		for(int i=0 ; i<gridRowNumber ; i++) {
			
			System.out.print(i+ " | ");
			for(int j=0 ; j<gridColoumNumber ; j++) 
			{ 
				if ( board[i][j] == 0 )
					System.out.print("    | ");
				if ( board[i][j] == 3 )
					System.out.print("  * | ");
				else if ( board[i][j] == 2)
					System.out.print("  C | ");
				else if ( board[i][j] == 1)
					System.out.print("  B | ");
			}
			System.out.println("\n");
		}
		System.out.println();
	}
}
