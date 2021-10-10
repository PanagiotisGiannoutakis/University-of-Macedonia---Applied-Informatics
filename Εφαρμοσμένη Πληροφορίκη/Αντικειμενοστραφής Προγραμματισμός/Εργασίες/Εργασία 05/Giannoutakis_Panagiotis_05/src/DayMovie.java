
public class DayMovie extends Movie {
	
	private int imeres;
	private double kostosImeras;

	public DayMovie(String titlos, int kodikos, int imeres, double kostosImeras) {
		super(titlos, kodikos);
		this.imeres = imeres;
		this.kostosImeras = kostosImeras;
	}

	public double KostosEnoikiasis () {
		 return imeres * kostosImeras;
	}
	
}
