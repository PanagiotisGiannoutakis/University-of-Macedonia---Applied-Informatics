
public class Cylinder extends Solid {
	
	private double h;
	public static int METRITIS1 = 0;
	private int counter = 0;
	
	public Cylinder(double m, double r, double h) {
		super(m, r);
		this.h = h;
		METRITIS1++;
		counter = METRITIS1;
	}
	
	public double calculateVolume() {
		return (Math.PI * Math.pow(r, 2) * h) / 3;
	}
	
	public double calculateSurfaceArea() {
		return 2 * Math.PI * r * (r + h);
	}
	
	public double calculateMomentOfInertia() {
		return (m * Math.pow(r, 2))/2;
	}
	
	public void rowSolid() {
		System.out.println("\nCylinder no " +counter+ " mass = " +m+ " kg, base radius = " +r+ " m, height = " +h+ " m");
	}
}
