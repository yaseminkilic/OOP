import java.io.IOException;

public class HW extends HW1{
	
	public static void main(String[] args) {
		
		try {
			
			hw1.addUser("Esra", 21, "K");
			hw1.addUser("Umut", 23, "E");
			hw1.addUser("Habib", 17, "E");
			hw1.addUser("Oguzhan", 18, "E");
			hw1.addUser("Veli", 16, "E");
			hw1.addUser("Hilal", 15, "K");
			hw1.addUser("Elif", 46, "K");
			System.out.println("***********************");
			
			hw1.getMovieDataFromFile("C:/Users/Yaseminn/workspace/Homework2015/input.txt");
			hw1.rateMovie("Wolverine", "Esra", 90);
			hw1.rateMovie("Wolverine", "Habib", 95);
			hw1.rateMovie("Wolverine", "Oguzhan", 85);
			hw1.addActor( hw1.movieNameAndActorsFromFile.get(0).toString(), hw1.movieNameAndActorsFromFile.get(1).toString() );
			System.out.println("***********************");
			
			hw1.rateMovie("The Usual Suspects", "Hilal", 60);
			hw1.rateMovie("The Usual Suspects", "Umut", 59);
			hw1.rateMovie("The Usual Suspects", "Veli", 61);
			hw1.addActor( hw1.movieNameAndActorsFromFile.get(2).toString(), hw1.movieNameAndActorsFromFile.get(3).toString() );
			System.out.println("***********************");
			
			hw1.addMovie("The Hunger Games", 2012, "Science-Fiction", "Gary Ross");
			hw1.rateMovie("The Hunger Games", "Habib", 100);
			hw1.rateMovie("The Hunger Games", "Umut", 52);
			hw1.rateMovie("The Hunger Games", "Hilal", 84);
			hw1.addActor("The Hunger Games", "Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth, Woody Harrelson, Elizabeth Banks, Lenny Kravitz, Stanley Tucci");
			System.out.println("***********************");
			
			hw1.addMovie("Ring", 1998 , "Horror" , "Hideo Nakata");
			hw1.rateMovie("Ring", "Oguzhan", 90);
			hw1.rateMovie("Ring", "Elif", 75);
			hw1.rateMovie("Ring", "Umut", 85);
			hw1.addActor("Ring", "Nanako Matsushima,Hiroyuki Sanada,Rikiya Otaka");
			
			System.out.println("***********************");
			
			hw1.addMovie("Secret Garden", 2010 , "Romance-Comedy,Fantasy,Action" , "Shin Woo-chul, Kwon Hyuk-chan");
			hw1.rateMovie("Secret Garden", "Oguzhan", 78);
			hw1.rateMovie("Secret Garden", "Elif", 85);
			hw1.rateMovie("Secret Garden", "Hilal", 90);
			hw1.addActor("Secret Garden", "Ha Ji-won, Hyun Bin, Yoon Sang-hyun, Kim Sa-rang");
			System.out.println("***********************");
			
			hw1.addMovie("The 100", 2013 , "Action,Adventure,Dystopian,Science-Fiction" , "Jason Rothenberg");
			hw1.rateMovie("The 100", "Hilal", 100);
			hw1.rateMovie("The 100", "Esra", 99);
			hw1.rateMovie("The 100", "Habib", 85);
			hw1.addActor("The 100", "Jason Rothenberg,Starring,Eliza Taylor,Paige Turco, Thomas McDonell,Eli Goree,Richard Harmon,Marie Avgeropoulos,Bob Morley,Kelly Hu");
			System.out.println("***********************");

			//hw1.rateMovie("Saw", "Veli", 48);
			//System.out.println("***********************");
			
			System.out.println(hw1.toString(listMovies,0));
			System.out.println("***********************");
			
			System.out.println( hw1.toString(listUsers, 0) );
			System.out.println("***********************");
			
			hw1.deleteUser("Veli");
			//hw1.deleteUser("Seda");
			System.out.println("***********************");
			
			hw1.deleteMovie("Ring");
			//hw1.deleteMovie("Saw");
			System.out.println("***********************");
			
			System.out.println( hw1.toString(listMovies, 0) );
			System.out.println( hw1.toString(listUsers, 0) );
			//System.out.println( hw1.toString(listRating, 0) );
			//System.out.println( hw1.toString(hw1.movieNameAndActorsFromFile, 0) );
			System.out.println("***********************");
			
			hw1.listRatings("Wolverine", "Wolverine.txt");
			hw1.listRatings("The Usual Suspects", "TheUsualSuspects.txt");
			hw1.listRatings("The Hunger Games", "TheHungerGames.txt");
			hw1.listRatings("Secret Garden", "Secret Garden.txt");
			hw1.listRatings("Ring", "Ring.txt");
			hw1.listRatings("The 100", "The100.txt");
			
			
		} catch (Exception e) {
			System.out.println(e);
			
		} finally{
			System.out.println("***********************");
			try {
				hw1.listMovies("Output.txt");
				hw1.br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}