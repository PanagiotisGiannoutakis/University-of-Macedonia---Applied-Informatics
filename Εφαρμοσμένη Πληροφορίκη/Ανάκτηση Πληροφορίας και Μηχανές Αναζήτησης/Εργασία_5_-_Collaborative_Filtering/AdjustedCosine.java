import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.*;

public class AdjustedCosine {
	
	public int N, M, kKontinoteroiGeitones, counterAParonomastis, counterBParonomastis;
	public double meanX, meanY, arithmitisMeanX, arithmitisMeanY, protoSkelosParonomasti, deuteroSkelosParonomasti;
	public double meanAbsoluteError;
	
	public ArrayList<Double> top3;
	public ArrayList<Integer> deiktesProsTop3;
	public ArrayList<Double> adjustedCosineTelikoTemp;
	
	public AdjustedCosine(int N, int M, int kKontinoteroiGeitones) {
		
		this.N = N;
		this.M = M;
		this.kKontinoteroiGeitones = kKontinoteroiGeitones;

	}
	
	public double AdjustedCosineCalculate() {
		
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
		
		ArrayList<ArrayList<Double>> adjustedCosine = new ArrayList<ArrayList<Double>>(N);
		ArrayList<Double> adjustedCosineTemp1 = new ArrayList<Double>(M);
		ArrayList<Double> adjustedCosineTemp2 = new ArrayList<Double>(M);
		ArrayList<Double> temp = new ArrayList<Double>(M);
		ArrayList<Double> union = new ArrayList<Double>(M);
		ArrayList<Double> adjustedCosineTeliko;
		Set<Double> set;
		ArrayList<Double> meanAbsoluteErrorArrayList = new ArrayList<>(N);
		
		for(int i=0; i<M; i++) {
			adjustedCosine.add(new ArrayList());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(pinakasOfelous[i][j] != 0.0) {
					adjustedCosine.get(i).add((double) j);
				}
			}
		}
		
		int xristisProsSumplirosiVathmologias = 0;
		double arithmitis = 0;
		double paronomastis = 0;
		boolean vrethike;
		
		for(int k=0; k<kKontinoteroiGeitones; k++) {
		
			vrethike = false;
			
			adjustedCosineTemp1 = new ArrayList<>(M);
			adjustedCosineTemp2 = new ArrayList<>(M);
			temp = new ArrayList<>(M);
			union = new ArrayList<>(M);
			adjustedCosineTeliko = new ArrayList<>(N);
				
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(pinakasOfelous[i][j] == 0.0 && !vrethike) {
						xristisProsSumplirosiVathmologias = i;
						vrethike = true;
					}
				}
			}
				
			for(int i=0; i<adjustedCosine.size(); i++) {
				arithmitis = 0;
				paronomastis = 0;
				
				// Ypologismos Arithmiti ston adjustedCosine tupo.
				meanX = 0.0;
				meanY = 0.0;
				arithmitisMeanX = 0.0;
				arithmitisMeanY = 0.0;
				protoSkelosParonomasti = 0.0;
				deuteroSkelosParonomasti = 0.0;
					
				for(int j=0; j<adjustedCosine.get(i).size(); j++) {
					arithmitisMeanX += adjustedCosine.get(i).get(j);
					arithmitisMeanY += adjustedCosine.get(xristisProsSumplirosiVathmologias).get(j);
				}
					
				meanX = arithmitisMeanX / adjustedCosine.get(i).size();
				meanY = arithmitisMeanY / adjustedCosine.get(xristisProsSumplirosiVathmologias).size();
					
				for(int j=0; j<adjustedCosine.get(i).size(); j++) {
					arithmitis += (adjustedCosine.get(i).get(j) - meanX) * (adjustedCosine.get(xristisProsSumplirosiVathmologias).get(j) - meanY);
				}
					
				// Upologismos paronomasti ston adjustedCosine tupo.
				for(int j=0; j<adjustedCosine.get(i).size(); j++) {
					protoSkelosParonomasti += Math.pow((adjustedCosine.get(i).get(j) - meanX),2);
					deuteroSkelosParonomasti += Math.pow((adjustedCosine.get(xristisProsSumplirosiVathmologias).get(j) - meanY),2);
				}
					
				paronomastis = Math.sqrt(protoSkelosParonomasti) * Math.sqrt(deuteroSkelosParonomasti);
					
				// Upologismos adjustedCosine tupo.
				double temp1 = (double) arithmitis / (double) paronomastis;
				adjustedCosineTeliko.add(temp1);
					
				// Upologismos Mesou Apolutou Lathous (MAE)
				meanAbsoluteError = 0.0;
				for(int j=0; j<adjustedCosine.get(i).size(); j++) {
					meanAbsoluteError += Math.abs(adjustedCosine.get(i).get(j) - adjustedCosine.get(xristisProsSumplirosiVathmologias).get(j));
				}
				meanAbsoluteError = meanAbsoluteError / adjustedCosine.get(i).size();
				meanAbsoluteErrorArrayList.add(meanAbsoluteError);
			}
				
			// Upologismos tou top3 apo tin ArrayList adjustedCosineTeliko.
			top3 = new ArrayList<Double>();
			deiktesProsTop3 = new ArrayList<Integer>();
			adjustedCosineTelikoTemp = new ArrayList<>(adjustedCosineTeliko);		
				
			for (int i = 0; i < kKontinoteroiGeitones + 1; i++) {
				int maxIndex = 0;

				for (int j = 0; j < adjustedCosineTelikoTemp.size(); j++) {
					if (adjustedCosineTelikoTemp.get(j) >= adjustedCosineTelikoTemp.get(maxIndex)) {
						maxIndex = j;
					}
				}
					
				deiktesProsTop3.add(maxIndex);
				double value = adjustedCosineTelikoTemp.get(maxIndex);
				adjustedCosineTelikoTemp.set(maxIndex,0.0);
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