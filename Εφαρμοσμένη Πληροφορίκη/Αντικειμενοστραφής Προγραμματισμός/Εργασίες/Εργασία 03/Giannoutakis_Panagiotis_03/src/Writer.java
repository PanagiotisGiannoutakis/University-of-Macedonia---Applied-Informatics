
public class Writer {
	
	private int afm;
	private String name;
	private double poso;
	
	public Writer() {
		afm = 0;
		name = "";
		poso = 0.0;
	}
	
	public Writer(String onoma, int arithmos, double poso_kerdous) {
		afm = arithmos;
		name = onoma;
		poso = poso_kerdous;
	}

	
	
	
	
	
	
	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPoso() {
		return poso;
	}

	public void setPoso(double poso) {
		this.poso = poso;
	}
	
	

}
