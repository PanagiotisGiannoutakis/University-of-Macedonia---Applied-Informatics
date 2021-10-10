import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class SolidObjectManager {
	
	Scanner myScanner = new Scanner(System.in);
	
	private ArrayList<Solid> solids;
	
	public SolidObjectManager() {
		solids = new ArrayList<Solid>();
	}
	
	public void addSolid(Solid solid) {
		solids.add(solid);
	}
	
	public void Manager() {
		
		int ArithmosSolid = 0;
		int sxima = 0;
		
		System.out.println("Posa sterea theleis na ftiakseis? ");
		ArithmosSolid = myScanner.nextInt();
		
		for(int i=0; i<ArithmosSolid; i++) {
			System.out.println("Ti stereo theleis na ftiakseis? (1=Cylinder, 2=Cone, 3=Sphere)");
			sxima = myScanner.nextInt();
			myScanner.nextLine();
			if(sxima == 1) {
				System.out.println("Dose tin maza se kila: ");
				String mazaText = myScanner.nextLine();
				double maza = Double.parseDouble(mazaText);
				System.out.println("Dose tin aktina se metra: ");
				String aktinaText = myScanner.nextLine();
				double aktina = Double.parseDouble(aktinaText);
				System.out.println("Dose to upsos se metra: ");
				String upsosText = myScanner.nextLine();
				double upsos = Double.parseDouble(upsosText);
				solids.add(new Cylinder(maza, aktina, upsos));
			} else if(sxima == 2) {
				System.out.println("Dose tin maza se kila: ");
				String mazaText = myScanner.nextLine();
				double maza = Double.parseDouble(mazaText);
				System.out.println("Dose tin aktina se metra: ");
				String aktinaText = myScanner.nextLine();
				double aktina = Double.parseDouble(aktinaText);
				System.out.println("Dose to upsos se metra: ");
				String upsosText = myScanner.nextLine();
				double upsos = Double.parseDouble(upsosText);
				solids.add(new Cone(maza, aktina, upsos));
			} else {
				System.out.println("Dose tin maza se kila: ");
				String mazaText = myScanner.nextLine();
				double maza = Double.parseDouble(mazaText);
				System.out.println("Dose tin aktina se metra: ");
				String aktinaText = myScanner.nextLine();
				double aktina = Double.parseDouble(aktinaText);
				solids.add(new Sphere(maza, aktina));
			}
		}
	}
	
	public void printResults() {
		
		DecimalFormat myFormat = new DecimalFormat(".##");
		
		for(int i=0; i<solids.size(); i++) {
			solids.get(i).rowSolid();
			System.out.println("- Volume: " +myFormat.format(solids.get(i).calculateVolume())+ " m^3");
			System.out.println("- Surface area: " +myFormat.format(solids.get(i).calculateSurfaceArea())+ " m^2");
			System.out.println("- Moment of Inertia: " +myFormat.format(solids.get(i).calculateMomentOfInertia())+ " kg.m^2");
		}
		System.out.println("\n----------------------------------------------------------------\n");
		System.out.println("Total Spheres made: " +Sphere.METRITIS3);
		System.out.println("Total Cones made: " +Cone.METRITIS2);
		System.out.println("Total Cylinders made: " +Cylinder.METRITIS1);
	}

}
