
import java.io.*;
import java.util.*;



public class Menu {
	
	enum Status { EASY, HARD, NORMAL };
	public Status mode;
	public int numberOfPlaying;
	public int numberOfWinner;
	public int level;
	private String userName;
	private File f = new File ("AmiralBattiInfo.txt");

	
	public Menu() {
		userName = getPlayersName();
		mode = Status.EASY;
		numberOfPlaying = 0;
		numberOfWinner = 0;
		level = 0;
	}
	public Menu( String userName,Status mode) {
		this.mode = mode;
		numberOfPlaying ++;
		this.userName = userName;
		
	}
	
	public String nameOfPlayer() 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the playerName : ");
		String user_name = input.nextLine();
		
		if ( ( user_name == null)||( user_name.equals(" ") ) ) {
			System.out.println("You have to input something.");
		}
		
		return user_name;
	}
	
	public void MenuShow () 
	{
		System.out.print("\n -- BATTLESHÝPS GAME --\n\n");
		System.out.println("##  GAME MENU  ##\n1. Login\n2. Create new user");
	}
	
	public int MenuInput() 
	{
		Scanner input1 = new Scanner(System.in);
		MenuShow();
		System.out.println("Enter your choice < 1 or 2 > : ");
		int number = input1.nextInt();
		
		if( (number==2) || (number==1) );	
		else {
			System.out.println("Error!!! Wrong Value.You must enter CORRECT choice...");
			MenuInput();
		}
		
		return number;	
	}
	
	public void MenuChoice(int choice) 
	{
		
		
		switch(choice) 
		{
		case 1:
			searchPlayerName();
			break;
			
		case 2:
			String name = nameOfPlayer();
			setPlayersName(name);
			break;
		}
	}
	
	public void searchPlayerName() 
	{
		String name;
		String logname;
		try {
			Scanner sc = new Scanner(new FileInputStream(f));
			name = nameOfPlayer();
			setPlayersName(name);
			
			do {
				logname = sc.next();
				sc.nextLine();
				
			} while (!name.equals(logname));
			
			if( name.equals(logname) ) {
				System.out.println("I found " +name+ " in file < " + toString() + " >");
			}
			
			
		} catch (NoSuchElementException n) {
			System.out.println("jjjj");
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void WriteFile() throws IOException {
		
		 String str = toString();
		 
	        File file = new File("AmiralBattiInfo.txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	 
	        FileWriter fileWriter = new FileWriter(file, true);
	        BufferedWriter bWriter = new BufferedWriter(fileWriter);
	        bWriter.write(str+"\n");
	        bWriter.close();
	        
	        FileReader fileReader = new FileReader(file);
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		/* 
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			}
		*/
		 
		
		 
		br.close();
	
	}
	
	public String toString() //<username> <mode>(<level>)
	{
		//state mode = difficultlyLevel; 
		return (" " + userName + " " + mode + "(" + level + ")");
	}
	
	public  String getPlayersName() 
	{
		return userName;
	}

	public void setPlayersName(String playersName) 
	{
		userName = playersName;
	}
	
}
