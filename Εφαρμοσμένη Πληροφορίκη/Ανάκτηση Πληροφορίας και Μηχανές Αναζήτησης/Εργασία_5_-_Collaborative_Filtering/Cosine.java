import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.*;

public class Cosine {
	
	public int N, M, kKontinoteroiGeitones, counterAParonomastis, counterBParonomastis;
	public double meanAbsoluteError;
	
	public ArrayList<Double> top3;
	public ArrayList<Integer> deiktesProsTop3;
	public ArrayList<Double> cosineTelikoTemp;
	
	public Cosine(int N, int M, int kKontinoteroiGeitones) {
		
		this.N = N;
		this.M = M;
		this.kKontinoteroiGeitones = kKontinoteroiGeitones;
		
	}
	
	public double CosineCalculate() {
		
		Double[][] pinakasOfelous = new Double[N][M];
		Random r = new Random(); 
		double mean = 0.0;
		double var;
		double tuxaioPosostoGnostonVathmologion;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				var = r.nextGaussian();
				
				if(var < -1) {
					var = -1;
				} else if(var > 1) {
					var = 1;
				}
	
				mean = (double) (10 + 1) / 2; // = 5.5
				var = var + mean;
				
				double OldRange = 6.5 - 4.5;  
				double NewRange = 10 - 1;  
			    double newVar = (((var - 4.5) * NewRange) / OldRange) + 1;
				
			    newVar = round(newVar,0);
				pinakasOfelous[i][j] = newVar;
			}
		}
		
		// E R O T I M A    3
		
		tuxaioPosostoGnostonVathmologion = Math.random() * 100;
		tuxaioPosostoGnostonVathmologion = round(tuxaioPosostoGnostonVathmologion,0);
		
		double posostoDiagrafonMiasGrammis = 1.0;

		int counterDiagrafonGiaEnaXristi;
		for(int i=0; i<N; i++) {
			counterDiagrafonGiaEnaXristi = 1;
			for(int j=0; j< (int) posostoDiagrafonMiasGrammis; j++) {
				int deiktisPouThaMidenistei = (int) (Math.random() * 10);			
				
				while(pinakasOfelous[i][deiktisPouThaMidenistei] == 0.0 && counterDiagrafonGiaEnaXristi < 9) {
					deiktisPouThaMidenistei = (int) (Math.random() * 10);
					counterDiagrafonGiaEnaXristi++;
				}
				
				pinakasOfelous[i][deiktisPouThaMidenistei] = 0.0;
			}
			
		}
		
		ArrayList<ArrayList<Double>> cosine = new ArrayList<ArrayList<Double>>(N);
		ArrayList<Double> cosineTemp1 = new ArrayList<Double>(M);
		ArrayList<Double> cosineTemp2 = new ArrayList<Double>(M);
		ArrayList<Double> temp = new ArrayList<Double>(M);
		ArrayList<Double> union = new ArrayList<Double>(M);
		ArrayList<Double> cosineTeliko;
		Set<Double> set;
		ArrayList<Double> meanAbsoluteErrorArrayList = new ArrayList<>(N);
		
		for(int i=0; i<M; i++) {
			cosine.add(new ArrayList());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(pinakasOfelous[i][j] != 0.0) {
					cosine.get(i).add((double) j);
					//System.out.println("cosine.get(i).add(j) " + cosine.get(i));
				}
				
			}
		}
		
		int xristisProsSumplirosiVathmologias = 0;
		double arithmitis = 0;
		double paronomastis = 0;
		boolean vrethike;
		
		for(int k=0; k<kKontinoteroiGeitones; k++) {
		
			vrethike = false;
			
			cosineTemp1 = new ArrayList<>(M);
			cosineTemp2 = new ArrayList<>(M);
			temp = new ArrayList<>(M);
			union = new ArrayList<>(M);
			cosineTeliko = new ArrayList<>(N);
				
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(pinakasOfelous[i][j] == 0.0 && !vrethike) {
						xristisProsSumplirosiVathmologias = i;
						vrethike = true;
					}
				}
			}
				
			for(int i=0; i<cosine.size(); i++) {
				arithmitis = 0;
				paronomastis = 0;
					
				// Ypologismos Arithmiti ston cosine tupo.
				for(int j=0; j<cosine.get(i).size(); j++) {
					arithmitis += cosine.get(i).get(j) + cosine.get(xristisProsSumplirosiVathmologias).get(j);
				}

				// Upologismos paronomasti ston cosine tupo.
				counterAParonomastis = 0;
				counterBParonomastis = 0;
				for(int j=0; j< cosine.get(i).size(); j++) {
					counterAParonomastis += Math.pow(cosine.get(i).get(j),2);
					counterBParonomastis += Math.pow(cosine.get(xristisProsSumplirosiVathmologias).get(j),2);
				}
					
				paronomastis = Math.sqrt(counterAParonomastis) * Math.sqrt(counterBParonomastis);
					
				// Upologismos cosine tupo.
				double temp1 = (double) arithmitis / (double) paronomastis;
				cosineTeliko.add(temp1);
					
				// Upologismos Mesou Apolutou Lathous (MAE)
				meanAbsoluteError = 0.0;
				for(int j=0; j<cosine.get(i).size(); j++) {
					meanAbsoluteError += Math.abs(cosine.get(i).get(j) - cosine.get(xristisProsSumplirosiVathmologias).get(j));
				}
				meanAbsoluteError = meanAbsoluteError / cosine.get(i).size();
				meanAbsoluteErrorArrayList.add(meanAbsoluteError);
			}
				
			// Upologismos tou top3 apo tin ArrayList cosineTeliko.
			top3 = new ArrayList<Double>();
			deiktesProsTop3 = new ArrayList<Integer>();
			cosineTelikoTemp = new ArrayList<>(cosineTeliko);
				
			for (int i = 0; i < kKontinoteroiGeitones + 1; i++) {
				int maxIndex = 0;

				for (int j = 0; j < cosineTelikoTemp.size(); j++) {
					if (cosineTelikoTemp.get(j) >= cosineTelikoTemp.get(maxIndex)) {
						maxIndex = j;
					}
				}
					
				deiktesProsTop3.add(maxIndex);
				double value = cosineTelikoTemp.get(maxIndex);
				cosineTelikoTemp.set(maxIndex,0.0);
				top3.add(value);
			}
				
			Collections.sort(deiktesProsTop3);
				
			double variable = 0.0;
				
			for(int j=0; j<M; j++) {
				if(pinakasOfelous[xristisProsSumplirosiVathmologias][j] == 0.0) {
					if((((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || ((Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && (pinakasOfelous[deiktesProsTop3.get(2)][j]) == 0.0) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || (pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || ((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || ((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j]))) {
						pinakasOfelous[xristisProsSumplirosiVathmologias][j] = Double.NaN;	
					} else {
						variable = (pinakasOfelous[deiktesProsTop3.get(1)][j] + pinakasOfelous[deiktesProsTop3.get(2)][j] + pinakasOfelous[deiktesProsTop3.get(3)][j]) / 3;
						pinakasOfelous[xristisProsSumplirosiVathmologias][j] = round(variable,0);
					}
				}
			}
		}
		
		// Mesos oros apo ta mesi apoluta lathoi
		
		double generalMeanAbsoluteError = 0.0;
		for(int i=0; i<meanAbsoluteErrorArrayList.size(); i++) {
			generalMeanAbsoluteError += meanAbsoluteErrorArrayList.get(i);
		}
		generalMeanAbsoluteError = generalMeanAbsoluteError / meanAbsoluteErrorArrayList.size();
		
		return generalMeanAbsoluteError;
	}
	
	static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
}