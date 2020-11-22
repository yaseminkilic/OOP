import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class GetInputFromFile {
	
	static ArrayList<Integer> list; // the arrayList to holds informations that get from input.txt file.
	static int index = 0; 
	
	static int[][] board; // the original array that holds my grid.
	static int[][] copyArrBloks; // the copy array that holds my grid.
	static String cNumbers, bNumbers, m, n; // variables that holds informations in the file.
	
	BufferedReader br = null;
	
	public GetInputFromFile () { // default constructor
		list = new ArrayList<Integer>();
	}
	
	public void getMovieDataFromFile(String fileName) throws Exception {
		
		try {
        	br = new BufferedReader(new FileReader(fileName));
        	String line;
        	
        	while ((line = br.readLine()) != null)   {
		    	String[] arr = line.split(" ");
		    	
		    	list.add( Integer.parseInt(arr[0])); // the value of row
		    	if( arr.length % 2 == 0 ){  // if there are two information in one line <example : 5*4>
		    		list.add( Integer.parseInt(arr[1]) );  // the value of column
		    	}
		    }
        	m = list.get(0).toString();  // the value of row
        	n = list.get(1).toString();  // the value of column
        	cNumbers = list.get(2).toString();  // the number of cameras
        	bNumbers = list.get(3).toString();  // the number of blocks
        	
        	//System.out.println( toString(list, 4, list.size(), 0)); // debug
        	
        	String abcd = toString(list, 4, list.size(), 0 );  // to obtain blocksLocation
        	String[] arr = abcd.split("\n");
        	
        	board = new int[ Integer.parseInt(m) ][ Integer.parseInt(n) ];
        	copyArrBloks = new int[ Integer.parseInt(m) ][ Integer.parseInt(n) ];
        	
        	for( int a=0 ; a<Integer.parseInt(m) ; a++){  // initialize the arrays
    			for( int b=0 ; b<Integer.parseInt(n) ; b++){
    				copyArrBloks[a][b] = board[a][b] = 0;
    			}
    		}
        	
        	for( int i=0 ; i<arr.length ; i++){  // locate the blocks in arrayLists
        		String[] arr2 = arr[i].split(" ");
        		int row = Integer.parseInt( arr2[0] );
        		int col = Integer.parseInt( arr2[1] );
        		copyArrBloks[row][col] = board[row][col] = 1;
        	}
        	
		}catch (Exception e){
        	e.printStackTrace();
        }finally{
			br.close();
		}
	}
	
	// Method to create toString in a new format with different parameters
	public String toString( ArrayList arrList, int index) {
	      if(index == arrList.size())
	          return "";
	      else 
	          return arrList.get(index).toString() + "\n" + toString(arrList, index+1);
	}
	
	// Method to create toString in a new format with different parameters
	public String toString( ArrayList<Integer> arrList, int first, int last, int count) {
		if(first == arrList.size() || first == last)
	    	return "";
	    else 
	    	count++;
	    	return arrList.get(first).toString() + ( (count%2) != 1 ? "\n" : " " ) + toString(arrList, first+1, last, count);
	}
}
