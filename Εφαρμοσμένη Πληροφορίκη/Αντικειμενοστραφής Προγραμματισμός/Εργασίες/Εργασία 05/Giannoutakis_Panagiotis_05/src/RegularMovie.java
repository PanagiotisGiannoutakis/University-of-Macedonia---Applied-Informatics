
public class RegularMovie extends Movie{
	
	private double KostosEnoikiasis;

	public RegularMovie(String titlos, int kodikos, double kostos) {
		super(titlos, kodikos);
		KostosEnoikiasis = kostos;
	}
	
	public double KostosEnoikiasis() {
		return KostosEnoikiasis;
	}
}
