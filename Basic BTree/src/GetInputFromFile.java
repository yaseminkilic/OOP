import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GetInputFromFile extends MyBTree{
	
	MyBTree<Character> myTree = new MyBTree<Character>();
	
	static int abc;
	
	String txt, title;
	static int totalNodeSize;
	static ArrayList<String> list, listTxt, originalList;
	static ArrayList<String> mainTitleNumber; // it holds the main numbers s.t. <1,2,3,4...>
	static ArrayList<String> tmpList; // this is tmp (2.1.2 ün tmp halini tutuyor)
	
	
	public GetInputFromFile () { // default constructor
		list = new ArrayList<String>(); 
		listTxt = new ArrayList<String>();
		originalList = new ArrayList<String>();
		
		mainTitleNumber = new ArrayList<String>();
		tmpList = new ArrayList<String>();
		
		txt=""; title=""; abc = 0;
		totalNodeSize = 0;
	}
	
	// method that split the inputs To The Tab Character 
	public void splitToTheTabCharacter( String fileName, String output){ 
		
		System.out.println(" The Begin Of splitToTheTabCharacter() Method ");
		
		BufferedReader bufferedReader = null;
		String line;
		try {
			bufferedReader = new BufferedReader(new FileReader( fileName ) );
			
			while( (line = bufferedReader.readLine()) != null ){
				if( !line.startsWith(" ") )
					listTxt.add( line ); //txt
				else{
					list.add( line.trim() );  //2.1 Title1
					listTxt.add("<1205011002-YaseminKILIC-HW3>");
				}
        	}
		}catch (Exception e){
			e.printStackTrace();
        }finally {
	            try {
	                if (bufferedReader != null) {
	                    bufferedReader.close();
	                }
	                
	                togetherList();
	                splitToTheSpaceCharacter( output );
	                
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	    }
		System.out.println(" The End Of splitToTheTabCharacter() Method ");
	}
	
	// methods that puts together for list and listTxt
	void togetherList(){
		
		System.out.println(" The Begin Of togetherList() Method ");
		
		String a, b, total;
		boolean state;
		try {
            for( int i=0 ; i<list.size() ; i++ ){
            	
            	state = false;
            	b = "";
            	a = list.get(i).toString();
			
            	for( int j=i+1 ; j<listTxt.size() ; j++ ){
            		if( state == false && listTxt.get(j).toString().equals("<1205011002-YaseminKILIC-HW3>") )
            			continue;
            		else if( state == false && !listTxt.get(j).toString().equals("<1205011002-YaseminKILIC-HW3>") ){ 
            			b += listTxt.get(j).toString() + "\n";
            			listTxt.set(j, "<1205011002-YaseminKILIC-HW3>");
            			state = true;
            		}else if( state == true && !listTxt.get(j).toString().equals("<1205011002-YaseminKILIC-HW3>") ){
            			b += listTxt.get(j).toString() + "\n";
            			listTxt.set(j, "<1205011002-YaseminKILIC-HW3>");
            		}else
            			break;
            	}
            	
            	total = a + "\n" + b;
            	originalList.add( total );
            }
            
		}catch (Exception ex) {
            ex.printStackTrace();
        }
		System.out.println(" The End Of togetherList() Method ");
	}
	
	// method that split the inputs To The NewLine And Space Character.   Format ----> 2.1(" ")Title1
	public void splitToTheSpaceCharacter( String output ){
		
		String b, tmpString = "";
		char temp = 0;
		try{
			
			File f = new File( output );
			if(f.exists())  f.delete();
			
			System.out.println(" The Begin Of splitToTheSpaceCharacter() Method ");
			Collections.sort(originalList);
			
			for( int i=0 ; i<originalList.size() ; i++ ){
				b = originalList.get(i).toString();
				b.trim();
				
				String[] arr2 = b.split("\n",2);
				txt = arr2[1];
				
				String[] arr = arr2[0].split(" ",2);    // index0-->2.1    index1--->Title1
				
				for( int j=0 ; j<arr[0].length() ; j+=2){
					temp = arr[0].charAt(j);
					tmpString = String.valueOf(temp);
					tmpList.add(tmpString);
					myTree.ÝnsertItem(temp, myTree.myRoot);
					abc++;
				}
				tmpList.add("");
				writeOutputToFile(output, myTree.writePathInConsole(myTree.myRoot) , arr[1], txt);
				
				System.out.println("-----1----------");
				System.out.println (tmpList.size() + " >--< length \n" + toString(tmpList, 0) );
				System.out.println("-----2----------");
				
				for( int j=0 ; j<arr[0].length() ; j+=2){
					temp = arr[0].charAt(j);
					myTree.deleteItem(temp, myTree.myRoot);
				}
				myTree.writePathInConsole(myTree.myRoot);
			}
			System.out.println ( originalList.size() + " >--< length \n" + toString(originalList, 0) );
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(" The End Of splitToTheSpaceCharacter() Method ");
	}
	
	public void writeOutputToFile(String fileName, String number, String title, String txt){
		
		System.out.println(" The Begin Of writeOutputToFile() Method ");
		try{
			File dosya = new File ( fileName );
			FileWriter file = new FileWriter( dosya, true );
			BufferedWriter output = new BufferedWriter(file);
			
			try{
				output.write( toStringToOutput(number, title, txt) );
				System.out.println("----> Output.txt is written !!!\n");
			}catch(Exception e){
				System.out.println("----> Output.txt is not written !!!\n");
				e.printStackTrace();
			}finally{
				output.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(" The End Of writeOutputToFile() Method ");
	}
	
	public String toStringToOutput(String x, String title, String txt) {
		
		String tmp = "" , fileString = "";
		System.out.println(" The Begin Of toStringToOutput() Method ");
		
		for( int i=1 ; i<abc ; i++)
			tmp += "   ";
		
		abc = 0;
		if( !txt.equals(" ") )
			fileString = tmp + x + " " + title + "\n" + txt ;
		else
			fileString = tmp + x + " " + title ;
		
		System.out.println(" The End Of toStringToOutput() Method ");
		return fileString;
	}
	
	public String toString(ArrayList<String> arrList, int index) {
		if(index == arrList.size())
			return "";
		else 
			return arrList.get(index).toString() + "\n" + toString(arrList, index+1);
	}
}