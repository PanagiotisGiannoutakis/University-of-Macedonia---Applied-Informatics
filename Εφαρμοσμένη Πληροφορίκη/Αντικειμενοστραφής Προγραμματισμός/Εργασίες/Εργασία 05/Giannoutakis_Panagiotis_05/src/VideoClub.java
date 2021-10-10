import java.util.ArrayList;
import java.text.DecimalFormat;

public class VideoClub {
	
	private ArrayList<Customer> customers;
	
	public VideoClub() {
		customers = new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public int findSmallest(int p1, int p2) {
		int smallestIndex = p1;
		for(int i=p1+1; i<p2; i++) {
			if(customers.get(i).PlithosTainion() < customers.get(smallestIndex).PlithosTainion()) {
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}
	
	public int findSmallest1(int p1, int p2) {
		int smallestIndex = p1;
		for(int i=p1+1; i<p2; i++) {
			if(customers.get(i).KostosCustomer() < customers.get(smallestIndex).KostosCustomer()) {
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}
	
	public void swapElements(int p1, int p2) {
		Customer temp = customers.get(p1);
		customers.set(p1,customers.get(p2));
		customers.set(p2,temp);
	}
	
	public void swapElements1(int p1, int p2) {
		Customer temp = customers.get(p1);
		customers.set(p1,customers.get(p2));
		customers.set(p2,temp);
	}
	
	public void sort() {
		for(int lh=0; lh<customers.size(); lh++) {
			int rh = findSmallest(lh, customers.size());
			swapElements(lh, rh);
		}
	}
	
	public void sort1() {
		for(int lh=0; lh<customers.size(); lh++) {
			int rh = findSmallest1(lh, customers.size());
			swapElements1(lh, rh);
		}
	}
	
	public void PrintPerissoteresTainies(int x) {
		System.out.println("Oi 3 kaluteroi pelates me tis perissoteres enoikiaseis einai: ");
		for(int i=customers.size() - x; i<customers.size(); i++) {
			System.out.println(customers.get(i).getOnoma()+ " me kodiko " +customers.get(i).getKodikos()+ " kai exei noikiasei " +customers.get(i).PlithosTainion()+ " tainies");
		}
	}

	public void PrintPerissoteraXrimata(int x) {
		DecimalFormat myFormat = new DecimalFormat(".##");
		System.out.println("\nOi 3 kaluteroi pelates pou exoun dosei ta perissotera xrimata se enoikiaseis tainion einai: ");
		for(int i=customers.size() - x; i<customers.size(); i++) {
			System.out.println(customers.get(i).getOnoma()+ " me kodiko " +customers.get(i).getKodikos()+ " kai exei dosei " +customers.get(i).KostosCustomer()+ " euro kai meso oro " +myFormat.format(customers.get(i).MesosOrosCustomer())+ " euro ana tainia");
		}
	}
}
