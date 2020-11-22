
class MyTransition {

	private final MyStates nextWord;
	private final String readWord, popWord,pushWord;

	MyTransition(final MyStates n, final String r, final String pp, final String ph) {
		nextWord = n;
		readWord = r;
		popWord = pp;
		pushWord = ph;
	}

	MyStates getNextWord() {return nextWord;}
	String getReadWord() {return readWord;}
	String getPopWord() {return popWord;}
	String getPushWord() {return pushWord;}
	
}