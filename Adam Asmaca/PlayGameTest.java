// Homework2
// Yasemin Kýlýç StudentNumber:1205011002

import java.util.Scanner;


public class PlayGameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		PlayGame Player = new PlayGame();
		int choice1=1;
		choice1 = Player.MenuInput();
        
		switch(choice1) {
		
			case 1:
				PlayGame.Name();
				Player.adamAsmaca();
				break;
				
			default:
				Player.generateReport();
				break;
		}   
		
		while( choice1 != 2 )
		{	
			try
			{
				choice1 = Player.MenuInput();
				Player.MenuChoice(choice1);
			} // end try
			
			catch (Exception ctrlZ)
			{
				System.exit(2);
			} // end catch
			
		}
    }
}