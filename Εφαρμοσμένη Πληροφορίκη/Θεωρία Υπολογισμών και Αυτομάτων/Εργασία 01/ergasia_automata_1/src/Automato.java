import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automato {
	private int arithmosKatastaseon;
	private int arxikiKatastasi;
	private int arithmosTelikonKatastaseon;
	private ArrayList<Integer> telikesKatastaseis;
	private int arithmosMetavaseon;
	private String regex1, regex2;
	private static String[][] pinakasMetavaseon;
	private ArrayList<String> alfabito;
	
	public Automato(File file) throws IOException {
		
	    telikesKatastaseis = new ArrayList<Integer>();
	    alfabito = new ArrayList<String>();
	    
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
		    char line1Character1 = br.readLine().toCharArray()[0]; //Διαλέγει τον πρώτο χαρακτήρα από την πρώτη γραμμή αγνοώντας τους υπόλοιπους χαρακτήρες.
		    if (Character.isDigit(line1Character1)) {
		    	arithmosKatastaseon = Character.getNumericValue(line1Character1);
			    System.out.println("O arithmos ton katastaseon sunolika einai: " + arithmosKatastaseon);
		    } else {
		        System.out.println("NO MATCH");
		    }
		    
		    char line2Character1 = br.readLine().toCharArray()[0]; //Διαλέγει τον πρώτο χαρακτήρα από την δεύτερη γραμμή αγνοώντας τους υπόλοιπους χαρακτήρες.		    
		    if ((Character.isDigit(line2Character1)) && (Character.getNumericValue(line2Character1)<arithmosKatastaseon)) { // Η αρχική κατάσταση δεν μπορεί να έχει αρίθμιση μεγαλύτερη από τον συνολικό αριθμό των καταστάσεων.
		    	arxikiKatastasi = Character.getNumericValue(line2Character1);
			    System.out.println("I arxiki katastasi einai i katastasi : " + arxikiKatastasi);
		    } else {
		        System.out.println("NO MATCH2");
		    }
		    
		    char line3Character1 = br.readLine().toCharArray()[0]; //Διαλέγει τον πρώτο χαρακτήρα από την τρίτη γραμμή αγνοώντας τους υπόλοιπους χαρακτήρες.
		    if ((Character.isDigit(line3Character1)) && (Character.getNumericValue(line2Character1) < arithmosKatastaseon)) { // Ο αριθμός των τελικών καταστάσεων δεν μπορεί να είναι μεγαλύτερος από το συνολικό αριθμό των καταστάσεων.
		    	arithmosTelikonKatastaseon = Character.getNumericValue(line3Character1);
			    System.out.println("O arithmos ton telikon katastaseon einai : " + arithmosTelikonKatastaseon);
		    } else {
		        System.out.println("NO MATCH3");
		    }
		    
		    //Η τέταρτη γραμμή πρέπει να έχει ακριβώς τόσα νούμερα όσα η μεταβλητή arithmosTelikonKatastaseon.
		    StringTokenizer line4 = new StringTokenizer(br.readLine());
		    if(line4.countTokens() == arithmosTelikonKatastaseon) {
		    	int[] numbers = new int[line4.countTokens()];
			    for(int i=0; line4.hasMoreTokens(); i++)
			    {
			       numbers[i] = Integer.parseInt(line4.nextToken());
			    }
			    for (int number : numbers) 
			    	telikesKatastaseis.add(number); 
		  
		        System.out.println(telikesKatastaseis);
		    } else {
		    	System.out.println("NO MATCH4");
		    }
		    
		    char line5Character1 = br.readLine().toCharArray()[0]; //Διαλέγει τον πρώτο χαρακτήρα από την πέμπτη γραμμή αγνοώντας τους υπόλοιπους χαρακτήρες.
		    if (Character.isDigit(line5Character1)) {
		    	arithmosMetavaseon = Character.getNumericValue(line5Character1);
			    System.out.println("O arithmos ton metavaseon einai: " + arithmosMetavaseon);
		    } else {
		        System.out.println("NO MATCH");
		    }

		    pinakasMetavaseon = new String[arithmosMetavaseon][3];
		    
		    for(int i=0; i<arithmosMetavaseon; i++) {
		    	StringTokenizer line = new StringTokenizer(br.readLine());
		    	for(int j=0; line.hasMoreTokens(); j++)
			    {
		    		pinakasMetavaseon[i][j] = line.nextToken();
			    }
		    	if(!alfabito.contains(pinakasMetavaseon[i][1])) {
	    			alfabito.add(pinakasMetavaseon[i][1]);
	    		}
		    }
		    
		    System.out.println("O pinakas metavaseon einai o akolouthos: ");
		    for(int i=0; i<arithmosMetavaseon; i++) {
		    	for(int j=0; j<3; j++) {
		    		System.out.print(pinakasMetavaseon[i][j] + " ");
		    	}
	            System.out.println();
            }
		    
	    }	
	    
	}
	
	public static String[][] getPinakaMetavaseon() {
		return pinakasMetavaseon;
	}
	
	public ArrayList<Integer> getTelikesKatastaseis() {
		return telikesKatastaseis;
	}

	public ArrayList<String> getAlfabito() {
		return alfabito;
	}
	
	public int getArxikiKatastasi() {
		return arxikiKatastasi;
	}
}
