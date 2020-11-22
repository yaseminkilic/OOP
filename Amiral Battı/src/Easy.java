import java.util.Random;
import java.util.Scanner;


public class Easy extends Amiral{
	
	
	public Easy() {
		super();
		gridNumber = 10;
		//shipNumber = 3;
		int shipLength;
		ships = new int[gridNumber][gridNumber];    //gemilerin yerleþtirildiði matris
		board = new int [gridNumber][gridNumber];   
	}
	
	public void initialBoardAndPrint()
	{
		
		System.out.print("\t  ");
		
		for (int k=0 ; k<gridNumber ; k++)
		{
			if( k<10)
				System.out.print(k+"  ");
			if( k>=10 )
				System.out.print(k+" ");
		}
		
		System.out.println("\n");
		
		for(int i=0 ; i<gridNumber ; i++) {
			
			System.out.print("    " +i+ "\t ");
			
			for(int j=0 ; j<gridNumber ; j++) 
			{ 
				board[i][j] = -1;
				if ( board[i][j] == -1)
					System.out.print(" . ");
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
	/*
	public void placeShips( int board[][]){
	    	        Random rand = new Random();
	    	        int x = rand.nextInt(board.length);
	    	        int y = rand.nextInt(board.length);
	    	        boolean orientation = rand.nextBoolean();
	    	        for(int i = 0; i < getLength(); i++){
	    	            if(x >= 0 && x < board.length && y >= 0 && y < board.length){
	    	                if(board[x][y] == 0){
	    	                    if(orientation == true){ //horizontal
	    	                        board[x+1][y] =getNumber();
	    	                    } else{ //vertical
	    	                        board[x][y+i] =getNumber();
	    	                    }
	    	                }
	    	            }
	    	        }
	 }
	 */
	public void initShips(int[][] ships){   // will fill the position of 3 ships
        Random random = new Random();
        
        for(int ship=0 ; ship < 3 ; ship++){  // method draws randomly two numbers between 0 and 9.
            ships[ship][0]=random.nextInt(this.gridNumber);
            ships[ship][1]=random.nextInt(this.gridNumber);
            
            //let's check if that shot was already tried 
            //if it was, just finish the do...while when a new pair was randomly selected
            for(int last=0 ; last < ship ; last++){
                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do{
                        ships[ship][0]=random.nextInt(10);
                        ships[ship][1]=random.nextInt(10);
                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }
            
        }
    }


	public void showBoard(int[][] board){    // to checks each position of the 'board[10][10]
		
        System.out.print("\t  ");
		
		for (int k=0 ; k<gridNumber ; k++)
		{
			if( k<10)
				System.out.print(k+"  ");
			if( k>=10 )
				System.out.print(k+" ");
		}
		
		System.out.println("\n");
		
		for(int i=0 ; i<gridNumber ; i++) {
			
			System.out.print("    " +i+ "\t ");
			
			for(int j=0 ; j<gridNumber ; j++) 
			{ 
				if ( board[i][j] == -1)
					System.out.print(" . ");
				else  {
					if( board[i][j] == 0 ){
						System.out.print(" - ");
					}else if( board[i][j] == 1 ){
						System.out.print(" X ");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
        
        
        /*
        for(int row=0 ; row < 10 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 5 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+" . ");
                }else if(board[row][column]==0){
                    System.out.print("\t"+" - ");
                }else if(board[row][column]==1){
                    System.out.print("\t"+" X ");
                }
                
            }
            System.out.println();
        }*/

    }

	public void shoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Row: ");
        shoot[0] = input.nextInt();
        
        System.out.print("Column: ");
        shoot[1] = input.nextInt();
        
    }
	

    public boolean hit(int[] shoot, int[][] ships){
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.println("You hit a ship located in ("+shoot[0]+", "+shoot[1]+")\n");
                return true;
            }
        }
        return false;
    }
    
    public void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
	
}
