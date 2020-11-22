import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class GetInputFromFile{
	
	PDA pda;
	BufferedReader bufferedReader;
	static String Q[]; 	// finite set of states
	static String E; 	// input alphabet
	static String T[]; 	// stack alphabet
	static String Z;	// initial stack symbol
	static String S;	// initial state
	static String F[]; 	// finite set of states
	static String word;	// input
	
	static ArrayList<String[]> D;  // transition relations
	
	GetInputFromFile() {
		D = new ArrayList<String[]>();
		E = "";	S = "";	Z = "";
	}
	
	void readInput(String fileName){
		
		String line;
		
		try {
			
			bufferedReader = new BufferedReader(new FileReader( fileName ) );
			while((line = bufferedReader.readLine()) != null){
				if(line.startsWith("D")) break;
				String[] input = line.trim().split(" ");
				for(String data : input){
					String[] item = data.split("=");
					String[] arr = item[1].split("\\{|\\}|\\,");
					if(data.startsWith("Q")){
						Q = new String[arr.length];
						Q = arr.clone();
					}
					else if(data.startsWith("E")){
						for(int i=0; i<arr.length; i++){
							E = E + arr[i];
						}
					}
					else if(data.startsWith("T")){
						T = new String[arr.length];
						T = arr.clone();
					}
					else if(data.startsWith("Z")){
						Z = item[1];
					}
					else if(data.startsWith("S")){
						S = item[1];
					}
					else if(data.startsWith("F")){
						F = new String[arr.length];
						F = arr.clone();
					}
				}
        	}
			
			// Add D input from input
			String[] d = line.split("\\=|\\{");
			String[] arr2 = null;
			String[] arr1 = d[2].split("\\,|\\}");
			D.add(arr1);
			while((line = bufferedReader.readLine()) != null){
				arr2 = line.trim().split(" ");
				arr1 = line.split("\\,");
				if(arr2.length>1){
					word = arr2[1];
					arr1 = arr2[0].split("\\}|\\,");
				}
				D.add(arr1);
			}
			
			// Set states and final states
			MyStates[] q = new MyStates[Q.length];
			for(int i=0; i<Q.length; i++){
				q[i] = new MyStates(Q[i]);
				for(int j=0; j<F.length; j++){
					if(Q[i].equals(F[j])){
						q[i].setFinal(true);
					}
				}
			}
			
			// Add transitions for states
			for(int j=0; j<D.size(); j++){
				String[] rule = D.get(j);
				String[] arr = rule[2].split("\\-");
				for(int i=0; i<Q.length; i++){
					for(int t=0; t<Q.length; t++){
						if(Q[i].equals(rule[0]) && Q[t].equals(arr[1])){
							q[i].addTransitionForState(q[t], rule[1], arr[0], rule[3]);
						}
					}
				}
			}
			
			// Create an object of automata with start state, start stack symbol and alphabet
			for(int i=0; i<q.length; i++){
				if(q[i].getName().equals(S)){
					pda = new PDA(q[i], E, Z, toString(F));
				}
			}
			
			// Test state of word, accepted? or rejected?
			boolean accepted = pda.acceptedOrRejected(word);
			String acceptedState = (accepted ? "ACCEPTED" : "REJECTED");
			System.out.println("\n" + word + " is " + acceptedState + " for this language");
			
		}catch (Exception e){
			e.printStackTrace();
        }finally {
	            try {
	                if (bufferedReader != null)
	                    bufferedReader.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	    }
	}
	
	String toString(ArrayList<String[]> list, int index) {
		if(index == list.size())
			return "";
		else
			toString(list.get(index));
			return  "\n" + toString(list, index+1);
	}
	
	String toString(String[] arr) {
		String str = "";
		for(int i=0; i<arr.length; i++)
			str = "/" + arr[i];
		return str.substring(1);
	}
}