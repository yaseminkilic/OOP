
public class Rating extends Users{
	 
	private String movieName;
	private int score;
	
	// With three-argument constructor, validate and store moviName, userName and score
	public Rating(String movieName, String userName, int score) {
		setMovieName(movieName);
		setScore(score);
		setUserName(userName);
	}
	
	// With two-argument constructor, validate and store userName and score
	public Rating(String userName, int score) {
		setUserName(userName);
		setScore(score);
	}
	
	// get/set movieName
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	// get/set score
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		if( (0 <= score) && (score <= 100) ){
			this.score = score;
		}else
			this.score = 0;
	}
	
	@Override // indicates that this method overrides a superclass method
	public String toString() {
		return getMovieName() + ":" + getUserName() + " " + getScore();
	}
}
