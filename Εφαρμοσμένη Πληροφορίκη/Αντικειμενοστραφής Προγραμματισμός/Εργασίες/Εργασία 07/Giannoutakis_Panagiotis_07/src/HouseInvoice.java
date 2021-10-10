
public class HouseInvoice extends DEHInvoice {
	
	private double tetragonikaMetra;
	
	public HouseInvoice(int kodikos, double kilovatores, double tm) {
		super(kodikos, kilovatores);
		tetragonikaMetra = tm;
	}
	
	public double upologismosKostous() {
		double kostos = 0.0;
		if(kilovatores<=800) {
			kostos = kilovatores * 0.05625;
		} else if((kilovatores>800) && (kilovatores<=1000)) {
			kostos = (800 * 0.05625) + ((kilovatores - 800) * 0.07850);
		} else if((kilovatores>1000) && (kilovatores<=2000)) {
			kostos = (800 * 0.05625) + (200 * 0.07850) + ((kilovatores - 1000) * 0.0815);
		} else if(kilovatores>2000) {
			kostos = (800 * 0.05625) + (200 * 0.07850) + (1000 * 0.0815) + ((kilovatores - 2000) * 0.09155);
		} 
		return kostos + (tetragonikaMetra * 2.5);
		
		}
	
	public String readInfo() {
		return "Normal House";
	}

}
