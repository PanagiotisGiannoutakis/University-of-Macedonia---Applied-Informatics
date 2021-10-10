
public abstract class Solid {
	
	protected double m;
	protected double r;
	
	public Solid(double m, double r) {
		this.m = m;
		this.r = r;;
	}
	
	public abstract double calculateVolume();
	public abstract double calculateSurfaceArea();
	public abstract double calculateMomentOfInertia();
	public abstract void rowSolid();

}
