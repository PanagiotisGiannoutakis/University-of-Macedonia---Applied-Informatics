
public abstract class Movie {

	private String titlos;
	private int kodikos;
	private int metritis;
	
	public Movie(String titlos, int kodikos) {
		this.titlos = titlos;
		this.kodikos = kodikos;
		metritis = 0;
	}
	
	public abstract double KostosEnoikiasis();
	
	public int getMetritis() {
		return metritis;
	}

	public void setMetritis(int metritis) {
		this.metritis = metritis;
	}
	
	public int getKodikos() {
		return kodikos;
	}

}
