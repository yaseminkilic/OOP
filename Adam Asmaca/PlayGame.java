// Homework2 
// YaseminKýlýç StudentNumber:1205011002

import java.util.Scanner;
import java.util.Random;


public class PlayGame {
	
	
	public static String userName;
	public static int numWin=0;
	public static int playCount=0;
	public static int maxLevel=1;
	
	
	public static void PlayGame(String newUserName ) 
	{
		userName = newUserName;
		numWin = 0;
		playCount=0;
		maxLevel = 1;
	}
	public static void PlayGame (String newUserName, int playCountP , int maximumLevel, int numWinP ) 
	{
		userName = newUserName;
		numWin = numWinP;
		playCount= playCountP;
		maxLevel = maximumLevel;
	}
	
	
	public static String getUserName() 
	{
		return userName;
	}
	public static void setUserName(String newUserName) 
	{
		userName = newUserName;
	}
	public static int getMaxLevel() 
	{
		return maxLevel;
	}
	public static void setMaxLevel(int maxLevelP) 
	{
		if( maxLevelP<=4)
			maxLevel=maxLevelP;
		else
			maxLevel=4;
	}
	
	public static void Name() 
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the user's name :");
		String user_name = input.nextLine();
		setUserName(user_name);
		
	}
	
	public void MenuShow () 
	{
		System.out.println("\n\nEnter your choice :\n1. Start Game\n2. End Game");
	}
	
	public int MenuInput() 
	{
		Scanner input = new Scanner(System.in);
		MenuShow();
		System.out.println("Enter your choice < 1 or 2 > : ");
		int number = input.nextInt();
		
		if( (number>2) || (number<1) ) {
			System.out.println("Error!!! Wrong Value ...");
			System.out.println("Enter CORRECT choice < 1 or 2 > : ");
			number = input.nextInt();
		}
		return number;
		
	}
	
	public void MenuChoice(int choice) 
	{
		
		switch(choice) {
	         case 1:
	        	 
		    	adamAsmaca();
		    	break;
			
	          default:
	     	
	    	    generateReport();
	    	    break;
	    	
	    }
		
	}
	
	public void generateReport() 
	{
		System.out.println("\n\nName of the player: "+userName+"\n"+"Number of wins: "+numWin+"\n"+"Maximum level: "+maxLevel+"\n"+"Number of times game played: "+playCount);
	}
	
	public void adamAsmaca () 
	{
		Random randomNumber = new Random();
		Scanner input = new Scanner(System.in);
		int random_Number,digit1=0,digit2=0,digit3=0,digit4=0,digit5=0, digit,total_guess;
		
		if(maxLevel==1) 
		{
			random_Number = randomNumber.nextInt(900)+100;
		 	digit=3;
		 	total_guess = 8;
		 	digit1 = ( random_Number / 100) % 10;
		 	digit2 = ( random_Number % 100 / 10) % 10;
		 	digit3 = ( random_Number % 10) % 10;
		 	
		}
		else if(maxLevel==2) 
		{
			random_Number = randomNumber.nextInt(9000)+1000;
	 	    digit=4;
	 	    total_guess = 10;
	 	    digit1 = ( random_Number / 1000 ) % 10;
	 	    digit2 = ( random_Number % 1000 / 100 ) % 10;
	 	    digit3 = ( random_Number % 100 / 10 ) % 10;
	 	    digit4 = ( random_Number % 10 ) % 10;
	 	    
		}
		else 
		{
			random_Number= randomNumber.nextInt(90000)+10000;
			digit=5;
			total_guess = 12;
			digit1 = ( random_Number / 10000 ) % 10;
			digit2 = ( random_Number % 10000 / 1000 ) % 10;
			digit3 = ( random_Number % 1000 / 100) % 10;
			digit4 = ( random_Number % 100 /10 ) % 10;
			digit5 = ( random_Number % 10 ) % 10;
			
		}
		
		
		
		int choice2 = 1;  // 1 for star game. 2 for end game.
        int useOfGuess = 0; // count the guess that you used incorrect.
		int x=-1; // number that you guessed.
		boolean adamAs=true;
		
		String[] a = new String[digit];
		for(int j=0 ; j<digit ; j++) {
			a[j] = "*";
			System.out.print(a[j]);
		}
		
		int[] yerler = new int[digit];
		for(int i=0; i<digit ; i++) 
		{
			yerler[i]= -1;
		}
		if(choice2 == 1)
			System.out.println("\nGuess ???");
		
		while( choice2!=2 ) {
		   
			
		  try
		  {
			   x = input.nextInt();	
			   System.out.println("\nYour guess : "+x);
			
			
			if( (maxLevel==3) || (maxLevel==4) ) //if your guess is correct for maxLevel 3 or 4 , computer take it in memory.
			{
				for(int i=0; i<5 ; i++) 
				{
					if( (digit1 == x) )
					{
						yerler[0]= x;
					}
					if( (digit2 == x) )
					{
						yerler[1]= x;
					}
					if( (digit3 == x) )
					{
						yerler[2]= x;
					}
					if( (digit4 == x) )
					{
						yerler[3]= x;
					}
					if( (digit5 == x) )
					{
						yerler[4]= x;
					}
					
					if (yerler[i] == -1)
						System.out.print("*");
					else
						System.out.print(yerler[i]);
					
					
				} // end for
				
				if( (x==digit1) || (x==digit2) || (x==digit3) || (x==digit4) || (x==digit5) ) {
					System.out.println("\t\t------>>>  This character is true :)");
				}
				else {
					++useOfGuess;
					System.out.println("\nWrong Guess !!!\nThere are "+ (total_guess-useOfGuess) +" guesses...");
				}
				
				if( (yerler[0] != -1) && (yerler[1] != -1) && (yerler[2] != -1) && (yerler[3] != -1) && (yerler[4] != -1) ) 
				{
					System.out.println("\nwith "+useOfGuess+" wrong guesses YOU WIN THE GAME !!! :)");
					System.out.print("Number : "+random_Number);
					numWin++;
					if(maxLevel<4)
						maxLevel++;
					playCount++;
					adamAs = false;
					break;
				}
			} // end if for the maxLevel 3-4
			
			if( maxLevel==2 ) //if your guess is correct for maxLevel 2 , computer take it in memory.
			{

				for(int i=0; i<4 ; i++) 
				{
					if( (digit1 == x) )
					{
						yerler[0]= x;
					}
					if( (digit2 == x) )
					{
						yerler[1]= x;
					}
					if( (digit3 == x) )
					{
						yerler[2]= x;
					}
					if( (digit4 == x) )
					{
						yerler[3]= x;
					}
					
					if (yerler[i] == -1)
						System.out.print("*");
					else
						System.out.print(yerler[i]);
					
				} // end for
				
				if( (x==digit1) || (x==digit2) || (x==digit3) || (x==digit4) ) {
					System.out.println("\t\t------>>>  This character is true :)");
				}
				else {
					++useOfGuess;
					System.out.println("\nWrong Guess !!!\nThere are "+ (total_guess-useOfGuess) +" guesses...");
				}
				
				
				if( (yerler[0] != -1) && (yerler[1] != -1) && (yerler[2] != -1) && (yerler[3] != -1) ) 
				{
					System.out.println("\nwith "+useOfGuess+" wrong guesses YOU WIN THE GAME !!! :)");
					System.out.print("Number : "+random_Number);
					numWin++;
					maxLevel++;
					playCount++;
					adamAs = false;
					break;
				}
				
			} // end if for the maxLevel 2
			
			
			if( maxLevel==1 ) //if your guess is correct for maxLevel 1 , computer take it in memory. 
			{
				
				for(int i=0; i<3 ; i++) {
					if( (digit1 == x) )
					{
						yerler[0]= x;
					}
					if( (digit2 == x) )
					{
						yerler[1]= x;
					}
					if( (digit3 == x) )
					{
						yerler[2]= x;
					}
				
					if (yerler[i] == -1)
						System.out.print("*");
					else
						System.out.print(yerler[i]);
				
				} // end for statement to the maxLevel 1
				
				if( (x==digit1) || (x==digit2) || (x==digit3) ) {
					System.out.println("\t\t------>>>  This character is true :)");
				}
				else {
					++useOfGuess;
					System.out.println("\nWrong Guess !!!\nThere are "+ (total_guess-useOfGuess) +" guesses...");
				}
				
				// if all of the digits are true guesses , you are winners.
				if( (yerler[0] != -1) && (yerler[1] != -1) && (yerler[2] != -1) ) 
				{
					System.out.println("\nwith "+useOfGuess+" wrong guesses YOU WIN THE GAME !!! :)");
					System.out.print("Number : "+random_Number);
					numWin++;
					maxLevel++;
					playCount++;
					adamAs = false;
					break;
				}
				
			} // end if for maxLevel 1 
			
			
			if(useOfGuess==total_guess)
			{
				System.out.println("\nYou finished "+total_guess+" guesses !!! Sorry :(");
				System.out.println("YOU LOST THE GAME !!!");
				System.out.print("Number : "+random_Number);
				System.out.println();
				playCount++;
				break;
			}
			
			  
			if(adamAs != false) {
			  switch(digit) {
			  
				case 3:
					if(useOfGuess==0)
						System.out.println("--------\n|     ( )\n|\n|\n|\n|\n|\n");
					if(useOfGuess==1)
						System.out.println("--------\n|     ( )\n|      |\n|\n|\n|\n|\n|\n");
					if(useOfGuess==2)
						System.out.println("--------\n|     ( )\n|      |\n|     /\n|\n|\n|\n|\n");
					if(useOfGuess==3)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|\n|\n|\n|\n");
					if(useOfGuess==4)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n");
					if(useOfGuess==5)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n|\n");
					if(useOfGuess==6)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|      |\n|\n|\n");
					if(useOfGuess==7)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     /\n|\n");
					if(useOfGuess==8)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     / \\\n|\n");
					break;
					
				case 4:
					if(useOfGuess==0)
						System.out.println("--------\n|        \n|\n|\n|\n|\n|\n");
					if(useOfGuess==1)
						System.out.println("--------\n|     (  \n|\n|\n|\n|\n|\n");
					if(useOfGuess==2)
						System.out.println("--------\n|     ( )\n|\n|\n|\n|\n|\n");
					if(useOfGuess==3)
						System.out.println("--------\n|     ( )\n|      |\n|\n|\n|\n|\n|\n");
					if(useOfGuess==4)
						System.out.println("--------\n|     ( )\n|      |\n|     /\n|\n|\n|\n|\n");
					if(useOfGuess==5)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|\n|\n|\n|\n");
					if(useOfGuess==6)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n");
					if(useOfGuess==7)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n|\n");
					if(useOfGuess==8)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|      |\n|\n|\n");
					if(useOfGuess==9)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     /\n|\n");
					if(useOfGuess==10)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     / \\\n|\n");
					break;
					
				case 5:
					if(useOfGuess==0)
						System.out.println("--------\n|        \n|\n|\n|\n|\n|\n");
					if(useOfGuess==1)
						System.out.println("--------\n|     (  \n|\n|\n|\n|\n|\n");
					if(useOfGuess==2)
						System.out.println("--------\n|     ( )\n|\n|\n|\n|\n|\n");
					if(useOfGuess==3)
						System.out.println("--------\n|     ( )\n|      |\n|\n|\n|\n|\n|\n");
					if(useOfGuess==4)
						System.out.println("--------\n|     ( )\n|      |\n|     /\n|\n|\n|\n|\n");
					if(useOfGuess==5)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|\n|\n|\n|\n");
					if(useOfGuess==6)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n");
					if(useOfGuess==7)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|\n|\n|\n");
					if(useOfGuess==8)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|      |\n|\n|\n");
					if(useOfGuess==9)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     /\n|\n");
					if(useOfGuess==10)
						System.out.println("--------\n|     ( )\n|      |\n|     / \\\n|      |\n|     / \\\n|\n");
					if(useOfGuess==11)
						System.out.println("--------\n|     ( )\n|      |\n|    // \\\n|      |\n|    // \\\n|\n");
					if(useOfGuess==12)
						System.out.println("--------\n|     ( )\n|      |\n|    // \\\n|      |\n|    // \\\n|\n");
					break;
					
			  }// end of switch
				
			} // end if
			
		 } // end try
		 catch (Exception ctrlZ)
		 {
			 System.exit(2);
		 } // end catch 
		 

		} // end while
		
	} // end of adamAsmaca
	
} // end of class