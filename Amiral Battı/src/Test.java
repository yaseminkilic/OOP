import java.io.IOException;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		Amiral easy = new Easy();
		Amiral normal = new Normal();
		
		int choice = menu.MenuInput();
		menu.MenuChoice(choice);
		//menu.CreateFile();
		//menu.CloseFile();
		try {
			menu.WriteFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n-----------------------------------------------------------------");
		easy.initialBoardAndPrint();
		easy.initShips(easy.ships);
		 do{
			 easy.showBoard(easy.board);
			 easy.shoot(easy.shoot);
			 easy.attempts++;
	            
	            if(easy.hit(easy.shoot,easy.ships)){
	            	easy.shotHit++;
	            }               
	            else;
	            
	            easy.changeboard(easy.shoot,easy.ships,easy.board);
	            

	        }while(easy.shotHit!=3);
		
	}

}
