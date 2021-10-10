
public class Sphere extends Solid {
	
	public static int METRITIS3 = 0;
	private int counter = 0;
	
	public Sphere(double m, double r) {
		super(m, r);
		METRITIS3++;
		counter = METRITIS3;
	}
	
	public double calculateVolume() {
		return (4 * Math.PI * Math.pow(r, 3)) / 3;
	}
	
	public double calculateSurfaceArea() {
		return 4 * Math.PI * Math.pow(r, 2);
	}
	
	public double calculateMomentOfInertia() {
		return (2 * m * Math.pow(r, 2)) / 5;
	}
	
	public void rowSolid() {
		System.out.println("\nSphere no " +counter+ " mass = " +m+ " kg, base radius = " +r+ " m");
	}

}
