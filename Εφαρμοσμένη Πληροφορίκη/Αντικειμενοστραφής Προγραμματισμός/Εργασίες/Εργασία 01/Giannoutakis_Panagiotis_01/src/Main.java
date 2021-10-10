import java.text.DecimalFormat;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		double miles;
		double feet;
		double gallons;
		double kilometers;
		double meters;
		double liters;
		
		System.out.println("Please enter the miles: ");
		miles = in.nextDouble();
		kilometers = miles * 1.609344;
		DecimalFormat myFormat = new DecimalFormat(".##");
		System.out.println(miles +" miles = "+ myFormat.format(kilometers) +" Km\n");
		
		System.out.println("Please enter the feet: ");
		feet = in.nextDouble();
		meters = feet * 0.3048;
		System.out.println(feet +" feet = "+ myFormat.format(meters) +" meters\n");
		
		System.out.println("Please enter the US Gallons: ");
		gallons = in.nextDouble();
		liters = gallons * 3.78541178;
		System.out.println(gallons +" gallons = "+ myFormat.format(liters) +" liters\n");	

	}

}