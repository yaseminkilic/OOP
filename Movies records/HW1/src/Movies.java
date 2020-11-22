public class Movies {
	
	private String name;
    private String genre;
    private String director;
    private String actors;
    private int year;
    
    // two-argument constructor
    public Movies(String name, String actors) {
		this(name, 0, "Unknown", "Unknown", actors);
	}
    
    // three-argument constructor
    public Movies(String name, int year, String genre, String director) {
		this(name, year, genre, director, "Unknown");
	}
    
    // With five-argument constructor, validate and store actors, director, genre, name and year
    public Movies(String name, int year, String genre, String director, String actors) {
		setActors(actors); 
		setDirector(director);
		setGenre(genre);
		setName(name);
		setYear(year);
	}
    
    // get/set genre
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	// get/set director
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	// get/set actors
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	
	// get/set year
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	// get/set name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override // indicates that this method overrides a superclass method
	public String toString() {
		return  name + "\n" + genre + "\n" + year + "\n" + director  + "\n" + actors + "\n ";
	}
	
}