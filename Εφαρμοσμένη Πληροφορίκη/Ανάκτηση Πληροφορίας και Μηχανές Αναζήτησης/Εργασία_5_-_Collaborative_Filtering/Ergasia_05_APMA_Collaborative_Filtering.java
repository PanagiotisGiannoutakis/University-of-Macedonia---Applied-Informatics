import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.*;
import java.util.Scanner;


public class Ergasia_05_APMA_Collaborative_Filtering {
	
	public static void main(String[] args) {
		
		int N = 10; // Xristes pou exei to peirama.
		int M = 10;	// Antikeimena pou vathmologountai sto peirama
		Double[][] pinakasOfelous = new Double[N][M]; // O pinakas ofelous tou peiramatos.
		int kKontinoteroiGeitones = 3; // K-Kontinoteroi Geitones.
		double generalMeanAbsoluteError = 0.0; // Genikos Meso Apoluto Lathos olon ton epanalipseon.
		
		// Allilepidrasi me ton xristi gia na dosei ton algorithmo. I apantisi prepei na isoutai me "1", "2", "3" i "4" kai tipota diaforetiko.
		System.out.println("Poion algorithmo theleis na xrisimopoiiseis? <1: Jaccard> <2: Dice> <3: Cosine> <4: Adjusted Cosine>");
		Scanner scanner = new Scanner(System.in);
		String epilogiAlgorithmou = scanner.nextLine();
		
		// Allilepidrasi me ton xristi gia na dosei tis epanalipseis tou algorithmou. I apantisi prepei na einai enas akeraios thetikos arithmos kai mono.
		System.out.println("Me poses epanalipseis theleis na ektelestei o algorithmos?");
		int T = Integer.parseInt(scanner.nextLine());
		
		long startTime = System.currentTimeMillis();
		
		if(epilogiAlgorithmou.equals("1")) {
			for(int i=0; i<T; i++) {
				Jaccard jaccard = new Jaccard(N, M, kKontinoteroiGeitones);
				generalMeanAbsoluteError += jaccard.JaccardCalculate();
			}
		} else if(epilogiAlgorithmou.equals("2")) {
			for(int i=0; i<T; i++) {
				Dice dice = new Dice(N, M, kKontinoteroiGeitones);
				generalMeanAbsoluteError += dice.DiceCalculate();
			}
		} else if(epilogiAlgorithmou.equals("3")) {
			for(int i=0; i<T; i++) {
				Cosine cosine = new Cosine(N, M, kKontinoteroiGeitones);
				generalMeanAbsoluteError += cosine.CosineCalculate();
			}
		} else if(epilogiAlgorithmou.equals("4")) {
			for(int i=0; i<T; i++) {
				AdjustedCosine adjustedCosine = new AdjustedCosine(N, M, kKontinoteroiGeitones);
				generalMeanAbsoluteError += adjustedCosine.AdjustedCosineCalculate();
			}
		} else {
			System.out.println("Wrong answer !!!");
		}
		
		System.out.println("To geniko Meso Apoluto Lathos olon ton epanalipseon einai: " + (generalMeanAbsoluteError / T));
		
		long endTime = System.currentTimeMillis();
		
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
	}

}
