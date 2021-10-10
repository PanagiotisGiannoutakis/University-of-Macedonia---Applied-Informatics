import java.util.ArrayList;


public class Rental {
	
	private ArrayList<Movie> movies;
	private int plithos;

	public Rental() {
		movies = new ArrayList<Movie>();
		plithos = 0;
	}
	
	public void addMovies(Movie movie) {
		movies.add(movie);
		plithos = plithos +1;
		movie.setMetritis(movie.getMetritis() + 1);
	}
	
	public double KostosRental() {
		double SunolikoKostosRental = 0.0;
		for(int i=0; i<movies.size(); i++) {
			SunolikoKostosRental += movies.get(i).KostosEnoikiasis();
		}
		return SunolikoKostosRental;
	}
	
	public int SizeMovie() {
		return movies.size();
	}

	public int getPlithos() {
		return plithos;
	}
	
}