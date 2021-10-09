import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.*;

public class Dice {
	
	public int N, M, kKontinoteroiGeitones;
	public double meanAbsoluteError;
	
	public ArrayList<Double> top3; // ArrayList pou periexei tis 3 (K-Kontinoterous Geitones) megaliteres times apo tin ArrayList diceTeliko.
	public ArrayList<Integer> deiktesProsTop3; // ArrayList pou deixnei tis theseis pou vriskontai sto diceTeliko oi 3 megaluteres times.
	public ArrayList<Double> diceTelikoTemp; // Voithitiki metavliti.
	
	// Kataskeuastis tis klassis Dice.
	public Dice(int N, int M, int kKontinoteroiGeitones) {
		
		this.N = N;
		this.M = M;
		this.kKontinoteroiGeitones = kKontinoteroiGeitones;
		
	}
	
	public double DiceCalculate() {
		
		// E R O T I M A    1
		
		Double[][] pinakasOfelous = new Double[N][M];
		Random r = new Random(); 
		double mean = 0.0;
		double var;
		double tuxaioPosostoGnostonVathmologion;
		
		// Arxikopoioume me omoiomorfi katanomi ton Pinaka Ofelous kai kanoume upologismous gia na einai oi arithmoi apo to 1 mexri to 10 kai akeraioi gia na einai pio emfani ta 
		// apotelesmata me to mati stin eksodo.
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
				//System.out.print(pinakasOfelous[i][j] + " ");
			}
			//System.out.println();
		}
		
		// E R O T I M A    3
		
		tuxaioPosostoGnostonVathmologion = Math.random() * 100;
		tuxaioPosostoGnostonVathmologion = round(tuxaioPosostoGnostonVathmologion,0);
		//double posostoDiagrafonMiasGrammis = (tuxaioPosostoGnostonVathmologion - M) / 10;
		
		double posostoDiagrafonMiasGrammis = 1.0; // Sta peiramata mas trexoume me autin tin metavliti sto 1, dld 10% kathe grammis, dld ena stoixeio apo kathe grammi tha afaireitai. 
		// Auto to kanoume gia na mporei na treksei kai na paragei apotelesmata to programma allios tha uparxoun polla Not a Number kai den tha mporoun na upologisthoun oi tupoi.
		//System.out.println();
		//System.out.println("POSOSTO DIAGRAFON: " + (int) posostoDiagrafonMiasGrammis * 10 + " %");
		
		// Diadikasia pou diagrafei ena stoixeio apo kathe grammi tuxaia.
		int counterDiagrafonGiaEnaXristi;
		for(int i=0; i<N; i++) {
			counterDiagrafonGiaEnaXristi = 1;
			for(int j=0; j< (int) posostoDiagrafonMiasGrammis; j++) {
				int deiktisPouThaMidenistei = (int) (Math.random() * 10);
				//System.out.println("Deiktis pou tha midenistei: " + deiktisPouThaMidenistei);
				
				while(pinakasOfelous[i][deiktisPouThaMidenistei] == 0.0 && counterDiagrafonGiaEnaXristi < 9) {
					deiktisPouThaMidenistei = (int) (Math.random() * 10);
					counterDiagrafonGiaEnaXristi++;
				}
				pinakasOfelous[i][deiktisPouThaMidenistei] = 0.0;
			}
		}
		
		// Kodikas gia to an theloume na emfanisoume ton pinaka mexri stigmis.
		/*System.out.println();
		System.out.println("Pinakas Ofelous meta tis diagrafes: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(pinakasOfelous[i][j] + " ");
			}
			System.out.println();
		}*/
		
		// E R O T I M A    2
		
		ArrayList<ArrayList<Double>> dice = new ArrayList<ArrayList<Double>>(N); // ArrayList pou periexei ArrayLists pou periexoun tis times dice pou upologistikan sumfona me
		// tin proti mideniki timi pou vrethike.
		ArrayList<Double> diceTemp1 = new ArrayList<Double>(M); // Voithitiki metavliti.
		ArrayList<Double> diceTemp2 = new ArrayList<Double>(M); // Voithitiki metavliti.
		ArrayList<Double> temp = new ArrayList<Double>(M); // Voithitiki metavliti.
		ArrayList<Double> diceTeliko; // AraayList pou periexei tis times dice gia ton enan xristi se sxesi me olous tous upoloipous xristes.
		Set<Double> set; // Voithitiki metavliti.
		ArrayList<Double> meanAbsoluteErrorArrayList = new ArrayList<>(N); // ArrayList pou periexei to meso apoluto lathos gia kathe xristi.
		
		//Arxikopoiisi tou dice.
		for(int i=0; i<M; i++) {
			dice.add(new ArrayList());
		}
		
		// Arxikopoiisi tis kathe ArrayList pou einai mesa stin dice ArrayList. Pairnei tis times pou den einai 0 giati tha xrisimopoiithoun stous upologismous.
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(pinakasOfelous[i][j] != 0.0) {
					dice.get(i).add((double) j);
					//System.out.println(dice.get(i));
				}
			}
		}
		
		int xristisProsSumplirosiVathmologias = 0; // o xristis pou vrethike to proto 0 ston pinaka ofelous.
		double arithmitis = 0; // o arithmitis ston tupo.
		double paronomastis = 0; // o paronomastis ston tupo.
		boolean vrethike; // Voithitiki metavliti.
		
		// Gia K-Kontinoterous Geitones epanelave ...
		for(int k=0; k<kKontinoteroiGeitones; k++) {
		
			vrethike = false;
			diceTemp1 = new ArrayList<>(M);
			diceTemp2 = new ArrayList<>(M);
			temp = new ArrayList<>(M);
			diceTeliko = new ArrayList<>(N);
				
			// Psaxnoume ton xristi me miediniki timi.
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(pinakasOfelous[i][j] == 0.0 && !vrethike) {
						xristisProsSumplirosiVathmologias = i;
						//System.out.println("O xristis pou eksetazoume einai i grammi: " + xristisProsSumplirosiVathmologias);
						vrethike = true;
					}
				}
			}
				
			// Gia kathe xristi upologise ton tupo.
			for(int i=0; i<dice.size(); i++) {
				arithmitis = 0;
				paronomastis = 0;
					
				// Ypologismos Arithmiti ston Dice tupo.
				for(int j=0; j<dice.get(i).size(); j++) {
					if(dice.get(xristisProsSumplirosiVathmologias).contains(dice.get(i).get(j))) {
						arithmitis++;
					}
				}

				// Upologismos paronomasti ston dice tupo.
				diceTemp1 = dice.get(i);
				diceTemp2 = dice.get(xristisProsSumplirosiVathmologias);
				
				paronomastis = diceTemp1.size() + diceTemp2.size();
					
				// Upologismos dice tupo.
				double temp1 = (2 * (double) arithmitis) / (double) paronomastis;
				diceTeliko.add(temp1);
					
				// Upologismos Mesou Apolutou Lathous (MAE)
				meanAbsoluteError = 0.0;
				for(int j=0; j<dice.get(i).size(); j++) {
					meanAbsoluteError += Math.abs(dice.get(xristisProsSumplirosiVathmologias).get(j) - dice.get(i).get(j));
				}
				meanAbsoluteError = meanAbsoluteError / dice.get(i).size();
				meanAbsoluteErrorArrayList.add(meanAbsoluteError);
			}
				
			// Upologismos tou top3 apo tin ArrayList diceTeliko.
			top3 = new ArrayList<Double>();
			deiktesProsTop3 = new ArrayList<Integer>();
			diceTelikoTemp = new ArrayList<>(diceTeliko);
				
			for (int i = 0; i < kKontinoteroiGeitones + 1; i++) {
				int maxIndex = 0;

				for (int j = 0; j < diceTelikoTemp.size(); j++) {
					if (diceTelikoTemp.get(j) >= diceTelikoTemp.get(maxIndex)) {
						maxIndex = j;
					}
				}
					
				deiktesProsTop3.add(maxIndex);
				double value = diceTelikoTemp.get(maxIndex);
				diceTelikoTemp.set(maxIndex,0.0);
				top3.add(value);
			}
				
			Collections.sort(deiktesProsTop3); // Taksinomoume ton pinaka me tous deiktes. Opote to stoixeio 0 tha exei panta timi 1.0 afou einai to megalutero kai itan o xristis
			// se sugkrisi me ton eauto tou. Gia auto uparxei panta 1.0 stin lista deiktesProsTop3. Opote tha lavoume upopsin ta 3 epomena stoixeia tis listas.
			
			double variable = 0.0;
			
			// Gia kathe 0.0 pou uparxei ston pinaka to antikathistoume me ton meso oro ton upoloipon vathmologion pou edosan oi K-Kontinoteroi Geitones.
			for(int j=0; j<M; j++) {
				if(pinakasOfelous[xristisProsSumplirosiVathmologias][j] == 0.0) {
					// Edo kanoume enan megalo elegxo me olous tous sundiasmous gia to an oi xristes pou einai oi kontinoteroi geitones periexoun 0.0 i times NaN ston pianaka ofelous.
					if((((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || ((Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && (pinakasOfelous[deiktesProsTop3.get(2)][j]) == 0.0) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || (Double.isNaN(pinakasOfelous[deiktesProsTop3.get(1)][j]) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || (pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j])) || ((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(2)][j]) && (pinakasOfelous[deiktesProsTop3.get(3)][j] == 0.0)) || ((pinakasOfelous[deiktesProsTop3.get(1)][j] == 0.0) && (pinakasOfelous[deiktesProsTop3.get(2)][j] == 0.0) && Double.isNaN(pinakasOfelous[deiktesProsTop3.get(3)][j]))) {
						pinakasOfelous[xristisProsSumplirosiVathmologias][j] = Double.NaN;	 // An isxuei i sinthiki tote vazoume NaN ston pinaka ofelous kai den mporese na provlefthei i timi
						// logo periorismenon dedomenon.
					} else {
						variable = (pinakasOfelous[deiktesProsTop3.get(1)][j] + pinakasOfelous[deiktesProsTop3.get(2)][j] + pinakasOfelous[deiktesProsTop3.get(3)][j]) / 3;
						pinakasOfelous[xristisProsSumplirosiVathmologias][j] = round(variable,0);
					}
				}
			}
		}
			
		// Kodikas gia emfanisi tou pinaka ofelous meta tin provlepsi timon.
		/*System.out.println();
		System.out.println("Pinakas Ofelous meta tis eisagoges: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(pinakasOfelous[i][j] + " ");
			}
			System.out.println();		
		}*/
			
		// Mesos oros apo ta mesi apoluta lathoi
		double generalMeanAbsoluteError = 0.0;
		for(int i=0; i<meanAbsoluteErrorArrayList.size(); i++) {
			generalMeanAbsoluteError += meanAbsoluteErrorArrayList.get(i);
		}
		generalMeanAbsoluteError = generalMeanAbsoluteError / meanAbsoluteErrorArrayList.size();
		
		return generalMeanAbsoluteError;
	}
	
	// Sunartisi pou stroggulopoiei to proto orisma
	static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
}