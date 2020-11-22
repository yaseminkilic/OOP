import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class hw2 extends MyStack{ // the main class that extends MyStack
	
	static ArrayList<String> listLocationForCamera; // arrayList that holds location for cameras.
	static int[][][] arr; // array that holds Row, Column and View Number of Cameras together
	static int x, y;
	
	int[] tmpXX; // array that holds the rows that are passed
	int[] tmpYY; // array that holds the columns that are passed
	int camera; // number of cameras in path
	int indexX=0;
	int indexY=0;
	
	hw2() {
		arr = new int[rowNumber][colNumber][colNumber*rowNumber];
		tmpYY = new  int [colNumber];
		tmpXX = new  int [rowNumber];
		listLocationForCamera = new ArrayList<String> ();
	}

	public static void main(String[] args) throws Exception {
		
		try {
			
			hw2 hw = new hw2();
			Grid grid = new Grid();
		
			grid.initialBoard();
			grid.getMovieDataFromFile("input.txt");
			grid.setGridColoumNumber( Integer.parseInt(grid.n) );
			grid.setGridRowNumber( Integer.parseInt(grid.m) );
			grid.initialBoard();
			grid.printBoard();
		
			hw.camera = Integer.parseInt(cNumbers);
		
			grid.toString(grid.list, 0);
			System.out.println( "Row " + grid.m + "*" + grid.n + " Column\nNumberOfCamera : " + grid.cNumbers + "\nNumberOfBloks : " + grid.bNumbers);
		
			MyStack stack = new MyStack();
		
			hw.giveInitialValue();
		
			while( hw.camera != 0) {
				hw.countGridsForCamera();
				hw.locateCameras(arr);
				grid.printBoard();
			}
		
			boolean state = true;
			for( int i=0 ; i<board.length ; i++ )
				for( int j=0 ; j<board[i].length ; j++)
					if( board[i][j] == 0 ){
						state = false; 
						break;
					}
		
			if( state )
				System.out.println( "The number of cameras is enough !!!\nYou did a very successful business :D\n" );
			else
				System.out.println( "There are not enough the number of cameras !!!\nPlease increase the number of camera in the txt file\n" );
			
			hw.writeOutputToFile("output.txt");
			
		} catch ( Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void countGridsForCamera(){
		
		int numberOfGrid = 0;  // variable that holds value of the max view for a x1 and y1.
		index = 0;
		
		for(int i=0 ; i<board.length ; i++){
			
			int tmpI = i;
			for(int j=0 ; j<board[i].length ; j++){
				
				int tmpJ = j;
				if( board[i][j] == 0 ){
					
					push(2);
					numberOfGrid++;
					
					// System.out.println("\n\n*****"+numberOfGrid+"*******"); // DEBUG
					if( i != 0 ){
						tmpI--;
						while( tmpI >= 0 && board[tmpI][tmpJ] != 1 && board[tmpI][tmpJ] != 2 ){ // from top to north
							push(2);
							numberOfGrid++;
							tmpI--;
							// System.out.println(numberOfGrid); // DEBUG
						}
						tmpI = i;
						tmpJ = j;
					}
					// System.out.println("*****"); // DEBUG
					if( i != rowNumber - 1  ){
						tmpI++;
						while( tmpI < rowNumber && board[tmpI][tmpJ] != 1 && board[tmpI][tmpJ] != 2 ){ // from top to south
							push(2);
							numberOfGrid++;
							tmpI++;
							// System.out.println(numberOfGrid); // DEBUG
						}
						tmpI = i;
						tmpJ = j;
					}
					// System.out.println("*****"); // DEBUG
					if( j != 0 ){
						tmpJ--;
						while( tmpJ >= 0 && board[tmpI][tmpJ] != 1 && board[tmpI][tmpJ] != 2 ){ // from top to left
							push(2);
							numberOfGrid++;
							tmpJ--;
							// System.out.println(numberOfGrid); // DEBUG
						}
						tmpI = i;
						tmpJ = j;
					}
					// System.out.println("*****"); // DEBUG
					if( j != colNumber-1 ){
						tmpJ++;
						while( tmpJ < colNumber && board[tmpI][tmpJ] != 1 && board[tmpI][tmpJ] != 2 ){ // from top to right
							push(2);
							numberOfGrid++;
							tmpJ++;
							// System.out.println(numberOfGrid); // DEBUG
						}
						tmpI = i;
						tmpJ = j;
					}
					
					arr[i][j][index] = numberOfGrid;
					
					index++;
					numberOfGrid = 0;
					
					while( isEmpty() )
						pop();

				}
			}  // end for --> j
		} // end for --> i
	}
	
	public void locateCameras( int array[][][]){
		
		
		
		int c = maxNumberOfCameras(array);
		int tmpx = x, tmpy = y;
		int tmpI = x, tmpJ = y;
		
		tmpXX[indexX] = tmpx; tmpXX[indexY] = tmpy;
		
		if( tmpx != 0 ){
			tmpI--;
			while( tmpI >= 0 && board[tmpI][tmpJ] != 1 ){ // from top to north
				board[tmpI][tmpJ] = 3;
				tmpI--;
			}
			tmpI = tmpx;
			tmpJ = tmpy;
		}
		if( tmpy != 0 ){
			tmpJ--;
			while( tmpJ >= 0 && board[tmpI][tmpJ] != 1 ){ // from top to left
				board[tmpI][tmpJ] = 3;
				tmpJ--;
			}
			tmpI = tmpx;
			tmpJ = tmpy;
		}
		if( tmpx != rowNumber ){
			tmpI++;
			while( tmpI < rowNumber && board[tmpI][tmpJ] != 1){ // from top to south
				board[tmpI][tmpJ] = 3;
				tmpI++;
			}
			tmpI = tmpx;
			tmpJ = tmpy;
			
		}
		
		if( tmpy != colNumber ){
			tmpJ++;
			while( tmpJ < colNumber && board[tmpI][tmpJ] != 1 ){ // from top to right
				board[tmpI][tmpJ] = 3;
				tmpJ++;
			}
			tmpI = tmpx;
			tmpJ = tmpy;
		}
		

		array[tmpx][tmpy][c] = 0;
		if( board[tmpx][tmpy] != 1 ){
			board[tmpx][tmpy] = 2;
			camera--;
		}
		
		
	}	
	
	public int maxNumberOfCameras( int array[][][] ){
		
		String tmpA = "", tmpB = "";
		int max = 0;
		int maxIndexOfC = 0;
		for(int a=0 ; a<array.length ; a++ )
			for(int b=0 ; b<array[a].length ; b++){
				for(int c=0 ; c<array[a][b].length ; c++){
					if( array[a][b][c] > max && board[a][b] != 1 && board[a][b] != 2  && board[a][b] != 3 ){
						max = array[a][b][c];
						maxIndexOfC = c;
						x=a; y=b;
						tmpA = String.valueOf(a);
						tmpB = String.valueOf(b);
					}
				}
			}
		
		return maxIndexOfC;
	}
	
	public void giveInitialValue(){
		
		for(int a=0 ; a<arr.length ; a++ )
			for(int b=0 ; b<arr[a].length ; b++)
				for(int c=0 ; c<arr[a][b].length ; c++)
					arr[a][b][c] = 0;
		
		for(int i=0 ; i<tmpXX.length ; i++)
			tmpXX[i] = -1;
		
		for(int j=0 ; j<tmpXX.length ; j++)
			tmpYY[j] = -1;
		
	}
	
	public void writeOutputToFile(String fileName){
		
		try{
			FileWriter file = new FileWriter( fileName );
			Writer output = new BufferedWriter(file);
			
			try{
				
				output.write( "\n" + fileName + " :\n_________________________\n\n" );
				addLocationOfCameraToArrayList();
				output.write( "\n" + toString(listLocationForCamera, 0) );
				System.out.println("----> Output.txt is written !!!\n");
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				output.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void addLocationOfCameraToArrayList(){
		
		for( int i=0 ; i<board.length ; i++ )
			for( int j=0 ; j<board[i].length ; j++)
				if( board[i][j] == 2 ){
					String tmp = ("\n" + i + "   " + j) ;
					listLocationForCamera.add(tmp);
				}
	}
} 
