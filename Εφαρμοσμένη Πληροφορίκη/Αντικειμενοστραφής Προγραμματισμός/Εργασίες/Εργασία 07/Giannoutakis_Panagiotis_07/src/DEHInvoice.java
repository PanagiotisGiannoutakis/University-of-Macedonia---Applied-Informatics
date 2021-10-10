
public abstract class DEHInvoice {
	
	protected int kodikos;
	protected double kilovatores;
	
	public DEHInvoice(int kodikos, double kilovatores) {
		this.kodikos = kodikos;
		this.kilovatores = kilovatores;
	}
	
	public abstract double upologismosKostous();
	public abstract String readInfo();

}
