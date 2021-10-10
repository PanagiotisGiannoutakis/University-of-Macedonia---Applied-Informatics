
public class IndustrialInvoice extends DEHInvoice {
	
	public IndustrialInvoice(int kodikos, double kilovatores) {
		super(kodikos, kilovatores);
	}
	
	public double upologismosKostous() {
		return kilovatores * 0.093;
	}
	
	public String readInfo() {
		return "industrial";
	}

}
