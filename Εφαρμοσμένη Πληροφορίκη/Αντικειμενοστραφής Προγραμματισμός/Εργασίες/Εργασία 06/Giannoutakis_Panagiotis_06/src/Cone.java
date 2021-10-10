
public class Cone extends Solid {
	
	private double h;
	public static int METRITIS2 = 0;
	private int counter = 0;
	
	public Cone(double m, double r, double h) {
		super(m, r);
		this.h = h;
		METRITIS2++;
		counter = METRITIS2;
	}
	
	public double calculateVolume() {
		return Math.PI * Math.pow(r, 2) * h;
	}
	
	public double calculateSurfaceArea() {
		return Math.PI * r * (r + h);
	}
	
	public double calculateMomentOfInertia() {
		return (3 * m * Math.pow(r, 2)) / 10;
	}
	
	public void rowSolid() {
		System.out.println("\nCone no " +counter+ " mass = " +m+ " kg, base radius = " +r+ " m, height = " +h+ " m");
	}

}
