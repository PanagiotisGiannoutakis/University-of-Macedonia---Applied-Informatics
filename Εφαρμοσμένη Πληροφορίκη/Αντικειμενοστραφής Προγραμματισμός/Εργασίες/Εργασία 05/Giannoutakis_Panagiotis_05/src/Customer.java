import java.util.ArrayList;


public class Customer {
	
	private int kodikos;
	private String onoma;
	private ArrayList<Rental> rentals;
	
	public Customer (int kodikos, String onoma) {
		this.kodikos = kodikos;
		this.onoma = onoma;
		rentals = new ArrayList<Rental>();
	}
	
	public void addRentals(Rental rental) {
		rentals.add(rental);
	}
	
	public double KostosCustomer() {
		double SunolikoKostosCustomer = 0.0;
		for(int i=0; i<rentals.size(); i++) {
			 SunolikoKostosCustomer += rentals.get(i).KostosRental();
		}
		return SunolikoKostosCustomer;
	}
	
	public double MesosOrosCustomer() {
		int SizeRental = 0;
		for(int i=0; i<rentals.size(); i++) {
			SizeRental += rentals.get(i).SizeMovie();
		}
		
		return KostosCustomer()/SizeRental;
	}
	
	public int PlithosTainion() {
		int PlithosTainion = 0;
		for(int i=0; i<rentals.size(); i++) {
			PlithosTainion += rentals.get(i).getPlithos();
		}
		return PlithosTainion;
	}

	public int getKodikos() {
		return kodikos;
	}

	public String getOnoma() {
		return onoma;
	}
	
}
