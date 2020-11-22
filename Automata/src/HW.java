/*
 * Yasemin KILIÇ
 * 1205011002
 */

public class HW {

	static GetInputFromFile input = new GetInputFromFile();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			if( args.length == 0) {
				input.readInput("3_1.txt");  // Input File
			}
			else if(args.length == 1){
				input.readInput(args[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}