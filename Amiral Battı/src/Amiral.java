import java.util.Random;


public abstract class Amiral {
	
	public int [][] board ;
	public String [][] tahmin ;
	int[][] ships;
	int[] shoot = new int[2];
	int attempts=0, shotHit=0;
	int gridNumber;
	
	public abstract void initialBoardAndPrint ();
	public abstract void initShips(int[][] ships);
	public abstract void showBoard(int[][] board);
	public abstract void shoot(int[] shoot);
	public abstract boolean hit(int[] shoot, int[][] ships);
	public abstract void changeboard(int[] shoot, int[][] ships, int[][] board);
	
}
