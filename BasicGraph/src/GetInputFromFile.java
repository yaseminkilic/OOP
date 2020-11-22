import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


class GetInputFromFile<T> {
	
	BufferedReader bufferedReader;
	MyGraph<T> graph = new MyGraph<>();
	
	static String[] tmpNode;
	static int[] edgeCost;
	static int[][] costMatrix;
	static ArrayList<String> firstListToGetInput, secondListToGetInput, thirdListToGetInput, tmpThirdListToGetInput, edgeDirection;
	
	GetInputFromFile() { // default constructor
		
		graph = new MyGraph<>();
		
		firstListToGetInput = new ArrayList<String>();  // Ankara \n Istanbul \n Ýzmir \n Manisa etc.
		secondListToGetInput = new ArrayList<String>(); // Ankara Istanbul 300 etc.
		thirdListToGetInput = new ArrayList<String>(); //Ankara Ýzmir Manisa etc.
		tmpThirdListToGetInput = new ArrayList<String>(); //Ankara \n Ýzmir \n Manisa \n  etc.
		edgeDirection = new ArrayList<String>();
	}
	
	String getCity(int index){  return firstListToGetInput.get(index);  }
	int numberOfCity(){   return firstListToGetInput.size();  }
	
	@SuppressWarnings("unchecked")
	void splitToThe_end_Words( String fileName, String output ){
		
		System.out.println(" The Begin Of splitToThe_end_Words() Method ");
		String line;
		try {
			bufferedReader = new BufferedReader(new FileReader( fileName ) );
			
			boolean state1 = true, state2 = true;
			
			while( (line = bufferedReader.readLine()) != null ){
				
				if( state1==true && !line.equalsIgnoreCase("end") && state2==true ){
					graph.addCity((T) line);
					firstListToGetInput.add(line);
				}
				else if( state1==true && line.equalsIgnoreCase("end") && state2==true ){
					state1 = false;
					continue;
				}
				else if( state1==false && !line.equalsIgnoreCase("end") && state2==true )
					secondListToGetInput.add(line);
				else if( state1==false && line.equalsIgnoreCase("end") && state2==true ){
					state2 = false;
					continue;
				}
				else{
					thirdListToGetInput.add( line );
					String[] arr = line.split(" ");
					for( int i=0 ; i<arr.length ; i++ ){
						tmpThirdListToGetInput.add( arr[i] );
					}
				}
				
        	}
			
			System.out.println("\n*************************************");
			System.out.println("\n" + toString(firstListToGetInput, 0) + "\n\n" 
			                   + toString(secondListToGetInput, 0) + "\n\n" + toString(thirdListToGetInput, 0)
			                   + toString(tmpThirdListToGetInput, 0) );
			System.out.println("*************************************\n");
			
		}catch (Exception e){
			e.printStackTrace();
        }finally {
	            try {
	                if (bufferedReader != null)
	                    bufferedReader.close();
	                
	                tmpNode = new String[ numberOfCity() ];
	                splitSecondList_callAddEdgeMethod( output );
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	    }
		System.out.println(" The End Of splitToThe_end_Words() Method ");
	}
	
	@SuppressWarnings("unchecked")
	void splitSecondList_callAddEdgeMethod( String outputName ){
		
		System.out.println(" The Begin Of splitSecondList_callAddEdgeMethod() Method \n");
		String line = "";
		edgeCost = new int[ secondListToGetInput.size() ];
		
		try {
			for( int i=0 ; i<secondListToGetInput.size() ; i++ ){
				line = secondListToGetInput.get(i).toString();
				line.trim();
			
				String[] arr = line.split(" ",3);
				edgeDirection.add( arr[0] );
				edgeDirection.add( arr[1] ); 
				edgeDirection.add(" ");
				edgeCost[i] = Integer.parseInt( arr[2] ) ;
			
				graph.addEdge( (T) arr[0], (T) arr[1], Integer.parseInt(arr[2]) );
				splitThirdListToGetInput( outputName );
			}
			showTheAdjMatrix();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(" The End Of splitSecondList_callAddEdgeMethod() Method ");
	}
	
	@SuppressWarnings("unchecked")
	void splitThirdListToGetInput( String outputName ){
		//System.out.println("\n The Begin Of splitThirdListToGetInput() Method ");
		try {
			String line = thirdListToGetInput.get(0).toString();
			T[] arr = (T[]) line.split(" ");
			graph.shortestPath( outputName, arr[0], arr[arr.length-1]);
		}catch (Exception e){
			e.printStackTrace();
		}
		//System.out.println("\n The End Of splitThirdListToGetInput() Method ");
	}
	
	void showTheAdjMatrix(){
		
		System.out.println("\n The Begin Of showTheAdjMatrix() Method ");
		
		try {
			addValuesForCostMatrix();
		
			System.out.print("\n________");
			for(int i=0 ; i<tmpNode.length ; i++) {
				System.out.printf("____%d.City_____",i);
			}
			System.out.println("\n");
		
			for(int i=0 ; i<tmpNode.length ; i++) {
			
				System.out.print(i+ ".City | ");
				for(int j=0 ; j<tmpNode.length ; j++) { 
					if ( costMatrix[i][j] == 0 )
						System.out.print("      0      | ");
					else if ( costMatrix[i][j] == -1 )
						System.out.print("             | ");
					else{
						System.out.printf( "      %-6d",costMatrix[i][j]); 
						System.out.print( " | " );
					}
				}
				System.out.println("\n");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println(" The End Of showTheAdjMatrix() Method ");
	}
	
	int addValuesForCostMatrix(){
		
		System.out.println(" The Begin Of addValuesForCostMatrix() Method ");
		
		String tmp = "", tmp2 = "", line;
		int rtrn = 0;
		
		try {
			for( int i=0 ; i<numberOfCity() ; i++ ){
				tmpNode[i] = getCity(i);
			}
			
			costMatrix = new int[ numberOfCity() ][  numberOfCity() ];
			for( int x=0 ; x< numberOfCity() ; x++ )
				for( int y=0 ; y< numberOfCity() ; y++ ){
					costMatrix[x][y] = -1;
					if( firstListToGetInput.get(x).toString().equals( tmpThirdListToGetInput.get(0).toString() ) ){
						costMatrix[x][x] = 0;
					}
				}
				
			for( int i=0 ; i<numberOfCity() ; i++ ){
				tmp = tmpNode[i].toString();
				for( int j=0 ;j<edgeDirection.size() ; j++ ){
					if( tmp.equalsIgnoreCase( edgeDirection.get(j).toString() ) ) {
						if( !( edgeDirection.get(j+1).toString() ).equals(" ") ){
							tmp2 = edgeDirection.get(j+1).toString();
							for( int k=0 ; k<numberOfCity() ; k++ ){
								if( tmp2.equals( firstListToGetInput.get(k) )){
									for( int l=0 ; l<secondListToGetInput.size() ; l++ ){
										line = secondListToGetInput.get(l).toString();
										line.trim();
										String[] arr = line.split(" ",3);
										double d = Double.parseDouble( arr[2] );
										if( ( arr[0].equals(tmp) || arr[0].equals(tmp2) ) && ( arr[1].equals(tmp2) || arr[1].equals(tmp) ) ){
											costMatrix[i][k] = (int) d;
											rtrn = costMatrix[i][k];
										}
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(" The End Of addValuesForCostMatrix() Method ");
		return rtrn;
	}
	
    String toString(List<String> list, int index) {
		if(index == list.size())
			return "";
		else 
			return list.get(index).toString() + "\n" + toString(list, index+1);
	}
}