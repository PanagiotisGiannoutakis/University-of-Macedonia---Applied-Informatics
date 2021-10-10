
public class Main {

	public static void main(String[] args) {
		
		Account acc1 = new Account();
		
		acc1.setUpoloipo(10000.0);
		acc1.setKodikos("GR1101415471234567891234567");
		acc1.Analipsi(5535.0);
		acc1.Katathesi(7580.0);
		acc1.UpologismosTokon(0.05);
		acc1.printInfo("Giannis Giannakidis", "6931234567");
		
		Account acc2 = new Account();
		
		acc2.setUpoloipo(25000.0);
		acc2.setKodikos("GR1101119141234123412341234");
		acc2.Analipsi(13500.0);
		acc2.Katathesi(47350.0);
		acc2.UpologismosTokon(0.03);
		acc2.printInfo("Petros Petropoulos", "6931234123");

	}

}
