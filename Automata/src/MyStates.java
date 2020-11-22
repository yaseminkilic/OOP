import java.util.ArrayList;

class MyStates {
	
	private String name;							// State name
	private ArrayList<MyTransition> transitions;	// transitions of this state
	private boolean finished = false;				// is final state?
	
	MyStates(String s) {
		name = s;
		transitions = new ArrayList<MyTransition>();
	}
	
	void addTransitionForState(MyStates next, String read, String pop, String push) {
		transitions.add(new MyTransition(next, read, pop, push));
	}
	
	String getName() {return name;}
	void setName(String name) {this.name = name;}
	
	ArrayList<MyTransition> getTransitions() {return transitions;}
	void setTransitions(ArrayList<MyTransition> t) {transitions = t;}
	
	boolean isFinal() {return this.finished;}
	void setFinal (boolean s) {this.finished = s;}
	
	MyTransition getTransition(MyStates state, String read, String pop) {
		for (MyTransition transition : transitions)
			if ((transition.getPopWord().equals(pop) || transition.getPopWord().equals("e")) && transition.getReadWord().equals(read))
				return transition;
		return null;
	}
}
