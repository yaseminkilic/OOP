import java.util.ArrayList;
import java.util.Stack;

class PDA {

	private static String Z; 				// init stack symbol
	private MyStates current_state;			// current state
	private ArrayList<String> alphabet;
	private static Stack<String> stack;
	private final String F;
	
	PDA(MyStates s, String alp, String z, String f){
		Z = z;
		current_state = s;
		F = f;
		
		stack  = new Stack<String>();
		alphabet = new ArrayList<String>();
		
		for (int i=0; i<alp.length(); i++)
			alphabet.add(alp.substring(i, i+1));
	}

	boolean checkAlphabet(String word) {
		String letter = "";
		for (int i = 0; i < word.length(); i++) {
			letter = String.valueOf(word.charAt(i));
			if (!alphabet.contains(letter) && !letter.equals("e"))
				return false;
		}
		return true;
	}
	
	boolean acceptedOrRejected(String input) {
		MyStates current = current_state;
		boolean accepted = true;
		stack.push(String.valueOf(Z));
		
		if (!checkAlphabet(input)) {
			System.out.println("Invalid input for this alphabet"); 
			return false;
		}
		else{
			String read="", pop="", push="";
			MyTransition transition = null;
			
			for (int i=0; accepted && i<input.length(); i++) {
				read = String.valueOf(input.charAt(i));
				if(stack.size()>0){ pop = stack.peek();}
				else{ pop = "e"; }
				transition = current.getTransition(current_state, read, pop);
				
				if (transition == null) {
					System.out.println("!!! No Valid Transition to " + input); 
					return false;
				}
				else {
					push = transition.getPushWord();
					System.out.print("state:" + current.getName() + "\tread:" + read);
					
					if(!push.isEmpty()){
						if (!push.equals("e")) {
							if(!transition.getPopWord().equals("e")){
								pop = stack.pop();
								System.out.print("\tpop:" + pop);
							}
							else{
								System.out.print("\tpop:e");
							}
							
							for (int j = 0; j < push.length(); j++){
								stack.push(push.substring(push.length()-1-(j), push.length()-j));
							}
							
							System.out.print("\tpush:" + push);
						}
						else if(transition.getPopWord().equals("e")){ 	//exp: (s,c,e-f,e)
							System.out.print("\tpop:e\tpush:e");
						}
						else if(!transition.getPopWord().equals("e")){ 	//exp: (f,a,A-f,e)
							if(stack.size()<1){
								return false;
							}
							else if(stack.size()>=1){
								pop = stack.pop();
								System.out.print("\tpop:" + pop + "\tpush:e");
							}
						}
					}
					
					current = transition.getNextWord();
					System.out.println("\tnewState:" + current.getName() + ",\tfinalState:" + F + "\t=> " + stack);
				}
			}
			
			if(current.isFinal()){		return accepted;	}
			else if(!accepted){			return false;		}
			
			// exp: (p,e,Z-q,e)
			if(accepted){

				read = String.valueOf('e');
				
				if(stack.size()>0){ pop = stack.peek();}
				else{ pop = "e"; }

				transition = current.getTransition(current_state, read, pop);
				
				
				if (transition != null) {
					push = transition.getPushWord();
					System.out.print("state:" + current.getName() + "\tread:" + read);
					
					if(!push.isEmpty()){
						if (!push.equals("e")) {
							if(!transition.getPopWord().equals("e")){
								pop = stack.pop();
								System.out.print("\tpop:" + pop);
							}
							else{
								System.out.print("\tpop:e");
							}
							
							for (int j = 0; j < push.length(); j++){
								stack.push(push.substring(push.length()-1-(j), push.length()-j));
							}
							
							System.out.print("\tpush:" + push);
						}
						else if(transition.getPopWord().equals("e")){ 	//exp: (s,c,e-f,e)
							System.out.print("\tpop:e\tpush:e");
						}
						else if(!transition.getPopWord().equals("e")){ 	//exp: (f,a,A-f,e)
							if(stack.size()<1){
								return false;
							}
							else if(stack.size()>=1){
								pop = stack.pop();
								System.out.print("\tpop:" + pop + "\tpush:e");
							}
						}
					}

					current = transition.getNextWord();
					System.out.println("\tnewState:" + current.getName() + ",\tfinalState:" + F + "\t=> " + stack);

					if(current.isFinal()){		return accepted;	}
					else if(stack.size()>=1){	return false;		}
				}
				else{ return false;	}
			}
		}
		
		return accepted;
	}
}
