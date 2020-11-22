import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class HW1 implements MovieAppInt {
	
	Rating rating;
	Movies movies;
	Users users;
	static HW1 hw1 = new HW1();
	
	static int index = 0; // variable to assign index of array in addActors method
	static int averageRating; // variable to calculate the averageRating for movies
	
	@SuppressWarnings("rawtypes")
	static ArrayList listMovies; // ArrayList to hold Movies information
	static ArrayList<Users> listUsers; // ArrayList to hold Users information
	static ArrayList<Rating> listRating; // ArrayList to hold Rating information
	ArrayList<String> movieNameAndActorsFromFile; // ArrayList to hold some information that is in input.txt
	
	BufferedReader br = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HW1() { // default constructor
		listMovies = new ArrayList();
		listUsers = new ArrayList();
		listRating = new ArrayList();
		movieNameAndActorsFromFile = new ArrayList<String>();
	}
	
	// Method to add a new Movie in ArrayList <listMovies>. It might give a exception <Movie Already Exists>
	@SuppressWarnings({ "static-access", "unchecked" })
	public void addMovie(String movieName, int year, String genre, String directorName) throws Exception {
		
		if( this.listMovies.contains(movieName) ){
	        	System.out.printf("-----> You Can't Add This Movie < %s > In MovieList \n", movieName);
	        	throw new Exception("< Movie Already Exists >\n");
	    }else {
			listMovies.add( new Movies(movieName, year, genre, directorName) );
			System.out.printf("-----> You added \" %s \" In MovieList \n", movieName);
		}
		
	}
	
	// Method to delete a Movie in ArrayList <listMovies>. It might give a exception <Movie Does Not Exist>
	@SuppressWarnings("static-access")
	public void deleteMovie(String movieName) throws Exception {
		
		if( this.listMovies.isEmpty() ) {
			throw new Exception("< MovieList is already empty >\n");
	    }
		
		if( !this.listMovies.toString().contains(movieName) ) {
			System.out.printf("-----> You Can't Delete This Movie < %s > From MovieList \n", movieName);
			throw new Exception("< Movie  Does Not Exist >\n");
	    }
		
		for(int i=0;i<this.listMovies.size();i++){
			if( this.listMovies.get(i).toString().contains(movieName) ) {
				listMovies.remove(i);
				System.out.printf("-----> You deleted \" %s \" In MovieList \n", movieName);
				break;
			}
	    }
	}
	
	// Method to add Actors in ArrayList <listMovies>
	@SuppressWarnings({ "unchecked", "static-access" })
	public void addActor(String movieName, String actorName) {
		
		String temp, abcd;
		
		try{
			String[] line = new String[6*listMovies.size()];
			
			for(int i=0 ; i<listMovies.size() ;i++){
				temp = listMovies.get(i).toString();
				line = temp.split("\n");
				
				if( line[0].contains(movieName) ){
					String a = line[index+2]; 
					int b = Integer.parseInt(a);
					
					abcd = line[index] + "\n" + b +"\n"+ line[index+1] +"\n"+ line[index+3] +"\n"+ averageRating +"\n"+ actorName + "\n";
					listMovies.set(i, abcd );
					averageRating = 0;
					break;
				}
			}
			
			if( this.listMovies.contains(movieName) ){
				index += 5;
			}
			
		}catch( ArrayIndexOutOfBoundsException arr ){
        	System.out.println("\nThere is a mistake in addActor method : " + arr);
		}catch( Exception e ){
			System.out.printf("\nThere are exception in addActor method : " + e);
		}
	}
	
	// Method to take a file name which contains movie information. It might give a exception <File Not Exists>
	// The format of input.txt =>  Wolverine;Action;2013;James Mangold;Hugh Jackman, Tao Okamoto, Brian Tee, Rila Fukushima
	public void getMovieDataFromFile(String fileName) throws Exception {
		
		try {
        	br = new BufferedReader(new FileReader(fileName));
        	String line, a, actorName;
        	int b;
        	
        	for(line = br.readLine() ; line != null ; ) {
        		String[] lineArr1 = line.split("[;,]");
        		
        		a = lineArr1[2]; 
        		b = Integer.parseInt(a);
        		
        		averageRating = averageRating(lineArr1[index]);
        		actorName = toStringActorName(lineArr1);
        		
        		movieNameAndActorsFromFile.add(lineArr1[index]);
        		movieNameAndActorsFromFile.add(actorName);
        		
        		addMovie( lineArr1[index] , b, lineArr1[index+1] , lineArr1[index+3]);
        		line = br.readLine();
        	}
        }catch (Exception e){
        	throw new Exception("< File Not Exists >");
        }
	}
	
	// A Extra Method to write in following format <Hugh Jackman, Tao Okamoto, Brian Tee, Rila Fukushima>
	public String toStringActorName(String[] b){
		
		String abcde = "";
		try{
			abcde = b[4];
			for(int i=5 ; i<b.length ;i++){
				abcde += String.format(" ,%s",b[i]);	
			}
		}catch(ArrayIndexOutOfBoundsException arr){
        	System.out.println("There is a mistake in toStringActorName method: "+ arr);
		}
		return abcde;
	}
	
	// Method to add a new User in ArrayList <listUsers>. It might give a exception <User Already Exists>
	@SuppressWarnings("static-access")
	public void addUser(String username, int age, String gender) throws Exception {
		
		if( this.listUsers.contains(username) ){
			System.out.printf("-----> You Can't Add This User < %s > In UserList\n", username);
			throw new Exception("< User Already Exists In UserList !!! >\n");
	    }else {
			listUsers.add( new Users(username, age, gender) );
			System.out.printf("-----> You added \" %s \" In UserList\n", username);
		}
	}
	
	// Method to delete an User in ArrayList <listUsers>. It might give a exception <User Does Not Exist>
	@SuppressWarnings("static-access")
	public void deleteUser(String name) throws Exception {
		
		if( this.listUsers.isEmpty() ) {
			throw new Exception("< UserList is already empty >\n");
	    }
		
		if( !this.listUsers.toString().contains(name) ) {
			System.out.printf("-----> You Can't Delete This User < %s >\n", name);
			throw new Exception("< User Does Not Exist >\n");
	    }
		
		for(int i=0;i<this.listUsers.size();i++){
			if( this.listUsers.get(i).toString().contains(name) ) {
				listUsers.remove(i);
				System.out.printf("-----> You deleted \" %s \" In UserList\n", name);
				break;
			} 
	    }
	}
	
	// Method to create toString in a new format with different parameters
	@SuppressWarnings("rawtypes")
	public String toString( ArrayList arrList, int index) {
	      if(index == arrList.size())
	          return "";
	      else 
	          return arrList.get(index).toString() + "\n" + toString(arrList, index+1);
	}
	
	// Method to list Movies in Output.txt
	public void listMovies(String fileName) {
		
		try{
			FileWriter file = new FileWriter(fileName);
			Writer output = new BufferedWriter(file);
			
			output.write( "\n" + fileName + "\n----------------------------\n" );
			
			output.write( "\n" + toString(listMovies, 0) );
			System.out.println("----> Output.txt is written !!!\n");
			output.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Method to rate a Movie that is in listMovies. It might give a exception <Movie Does Not Exist>
	@SuppressWarnings("static-access")
	public void rateMovie(String movieName, String userName, int rate) throws Exception {
		
		if( this.listMovies.isEmpty() ) {
			throw new Exception("< MovieList is already empty !!! Hence You Can't Rate This Movie >\n");
	    }
		else if( !this.listMovies.toString().contains(movieName) ) {
			System.out.printf("-----> You Can't Rate This Movie < %s >\n", movieName);
			throw new Exception("< Movie Does Not Exist >\n");
	    }
		else{
			for(int i=0;i<listMovies.size();i++){
				if( listMovies.get(i).toString().contains(movieName) ) {
					System.out.printf("-----> You rated %d point \" %s \" In MovieList \n", rate, movieName);
					rating = new Rating( movieName, userName, rate );
					listRating.add(rating);
					averageRating = averageRating(movieName);
					break;
				}
	        } 
	    }
	}
	
	// A Extra Method to calculate averageRating of a movie
	public int averageRating(String movieName){
		
		int total = 0, counter = 0, rate;
		
		try{
			for( int i=0 ; i<listRating.size() ; i++ ){
				if( listRating.get(i).toString().contains(movieName)){
					++counter;
					rate = listRating.get(i).getScore();
					total +=  rate;
					System.out.printf("Debug total %d \n",total);
					System.out.printf("Debug counter %d \n",counter);
					if( counter == 0 ) averageRating = 0;
					if( counter != 0 ) averageRating = (total / counter);
				}	
			}
		} catch( Exception e ){
			e.printStackTrace();
		}
		
		System.out.printf("Debug average %d \n", averageRating);
		return averageRating;
	}
	
	// Method to write the output to the file taken as the argument in the following format <Veli 6.1>
	public void listRatings(String movieName, String fileName) { 
		
		int rate;
		String userName ="";
		try{
			FileWriter file = new FileWriter(fileName);
			Writer output2 = new BufferedWriter(file);
			
			output2.write( "\n\n" + fileName + "\n----------------------------\n\n" );
			for( int i=0 ; i<listRating.size() ; i++ ){
				if( listRating.get(i).toString().contains(movieName)){
					rate = listRating.get(i).getScore();
					userName = listRating.get(i).getUserName();
					output2.write(userName +" "+ rate + "\n");
				}
			}
			
			System.out.printf("----> %s is written !!!\n", fileName);
			output2.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}