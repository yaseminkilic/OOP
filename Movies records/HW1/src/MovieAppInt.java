
public interface MovieAppInt {
	
	//movie functions
	void addMovie(String movieName, int year, String genre, String directorName) throws Exception;
	void deleteMovie(String movieName) throws Exception;
	void rateMovie(String movieName, String userName, int rate) throws Exception;
	void listMovies(String fileName);
	void addActor(String movieName, String actorName);
	void getMovieDataFromFile(String fileName) throws Exception;
	
	//user functions
	void addUser(String username, int age, String gender) throws Exception;
	void deleteUser(String name) throws Exception;
	
	//rating functions
	void listRatings (String movieName, String fileName);
	
}
