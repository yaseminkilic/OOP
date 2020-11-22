
class hw4 extends MyGraph<Object> {
	
	static boolean state = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			if( args.length == 0) {
				System.out.print( " Please put a input file at command line !!! " );
				state = false;
			}else if( args.length == 1 ) {
				getFile.splitToThe_end_Words( args[0], "output.txt"  );
			}else {
				getFile.splitToThe_end_Words( args[0], args[1]  );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}