import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Parser{
	
	private JPanel panel;
	private JFrame frame;
	private JLabel labelImage, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11;
	
	private String fileName, pattern, line;
	
	private int lineNumber;
	
	private final int MAX_SIZE_M = 100;
	private final int MAX_SIZE_N = 100;
	
	private String[][] A;
	private String[] b;
	private String[] c;
	private String[] Eqin;
	
	private ArrayList<Integer> errorList;
	
	private int MinMax, i, j, n;
	
	private boolean thisIsTheEnd, antikeimeniki;
	
	public Parser() {
		
		initialize(); //arxikopoiisi metavliton.
		
		fileName = chooseFile();

		if(correctType(fileName)) {
			writeInFile();
			JOptionPane.showMessageDialog(null, "To arxeiko keimenou me tis mitres dimiourgithike me epituxia !!!", "Linear Problem compiled", JOptionPane.INFORMATION_MESSAGE);
		} else {
			errorGUI();
		}
		
	}
	
	public String chooseFile() {
		JFileChooser chooser = new JFileChooser();
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    	chooser.setFileFilter(filter);
    	File afile = null;
    	int returnVal = chooser.showOpenDialog(new JFrame());
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    		afile = chooser.getSelectedFile();
       		JOptionPane.showMessageDialog(null, "You chose to open this file: " +chooser.getSelectedFile().getName(), "", JOptionPane.INFORMATION_MESSAGE);	
       		return afile.getName();
    	} else {
    		return "";
    	}
    	
	}
	
	public void initialize() {
		A = new String[MAX_SIZE_M][MAX_SIZE_N];
		b = new String[MAX_SIZE_M];
		c = new String[MAX_SIZE_N];
		Eqin = new String[MAX_SIZE_M];
		
		errorList = new ArrayList<Integer>();
		
		lineNumber = 0;
		
		fileName = null;
		
		thisIsTheEnd = false;
		antikeimeniki = true;
		
		n = 0;
		
		for(i=0; i<MAX_SIZE_M; i++) {
			for(int j=0; j<MAX_SIZE_N; j++) {
				A[i][j] = "0";
			}
			b[i]= "0";
			Eqin[i] = "0";
		}
		for(j=0; j<MAX_SIZE_N; j++) {
			c[j] = "0";
		}
		MinMax = 0;
	}
	
	public boolean correctType(String filename) {
	
		boolean isCorrect = true;
		Scanner s = null;
		
		try {
			
			s = new Scanner(new BufferedReader(new FileReader(filename)));
			
			while(s.hasNextLine() && isCorrect) {
			
				line = s.nextLine();
				while((line.trim().equals("")) && (s.hasNextLine())) {
					line = s.nextLine();          //Agnoei tis kenes grammes.
				}
				
				line = line.toLowerCase().replace(" ", "");	  //Apaleifei ola ta kena stin grammi kai antikathiste ta kefalaia grammata me mikra.
				
				// Elegxos gia Max i Min.
				if((antikeimeniki) && ((!(line.contains("min") || (line.contains("max")))))) {
					isCorrect = false;
					errorList.add(1);
				}
				
				
				// Elegxos gia End.
				if((!s.hasNextLine()) && (!line.contains("end"))) {
					isCorrect = false;
					errorList.add(2);
				}
				
				if(antikeimeniki && isCorrect) {
				
					if(line.startsWith("max")) {
						MinMax = 1;
						line = line.substring(3);
					} else if(line.startsWith("min")) {
						MinMax = -1;
						line = line.substring(3);
					}
					
					if((Character.isDigit(line.charAt(0))) || Character.isAlphabetic(line.charAt(0))) {
						line = new StringBuilder(line).insert(0, "+").toString();  //Vazoume to prosimo + ston proto suntelesti.
					}
						
					line = line.replaceAll("-(?![0-9]+)", "-1"); //Vazoume ton suntelesti 1 pou polles fores agnoeitai.
					line = line.replaceAll("\\+(?![0-9]+)", "+1");
					
					
					//Elegxos gia tin morfi tis antikeimenikis sunartisis.
					pattern = "((\\+|-)(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)[0-9]+)*";
					if(!line.matches(pattern)) {
						isCorrect = false;
						errorList.add(3);
					}
					
					
					//Upologismos ton stilon tou grammikou provlimatos.	
					Pattern pattern1 = Pattern.compile("(\\+|-)(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)([0-9]+)");
					Matcher matcher = pattern1.matcher(line);
					while (matcher.find()) {
      					n++;
    				}					
					
					//Elegxos gia sosto arithmo metavliton.
					if(isCorrect) {
						pattern = "((\\+|-))(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)";
						String[] st5 = line.split(pattern);
						int max = Integer.parseInt(st5[1]);
						for(i=2; i<st5.length; i++) {
							if(Integer.parseInt(st5[i]) > max) {
								max = Integer.parseInt(st5[i]);
							}
							if(!(Integer.parseInt(st5[i]) == (Integer.parseInt(st5[i-1])+1))) {
								isCorrect = false;        //Elegxos gia auksousa seira metavliton.
								errorList.add(4);
							}
						}
						if(max != n) {
							isCorrect = false;
							errorList.add(5);
						}
					}
					
					
					//Upologismos mitras c.
					pattern = "[a-z]+[0-9]+";
					String[] st1 = line.split(pattern);
					for(j=0; j<st1.length; j++) {
						c[j] = st1[j];
					}
					
					antikeimeniki = false;
					
				} else {

					//Elegxos gia st.
					if((line.startsWith("st")) && (lineNumber == 0)) {
						line = line.substring(2);
						if(line.equals("")) {
							lineNumber = -1;
						}
					} else if((!line.startsWith("st")) && (lineNumber == 0)){
						isCorrect = false;
						errorList.add(6);
					}

					if((line.contains("end")) && (!thisIsTheEnd)) {
						line = line.substring(0, line.toLowerCase().indexOf("end"));
						thisIsTheEnd = true;
					}
					
					if(!line.equals("") && isCorrect) {
						
						if(lineNumber == -1) {
							lineNumber = 0;
						}
						
						if((Character.isDigit(line.charAt(0)) || Character.isAlphabetic(line.charAt(0)))) {
							line = new StringBuilder(line).insert(0, "+").toString();  
						}
					
						line = line.replaceAll("-(?![0-9]+)", "-1");
						line = line.replaceAll("\\+(?![0-9]+)", "+1");
						
						//Elegxos gia sosti morfi ton periorismon.
						pattern = "((((\\+|-)(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)([0-9]+)+)+))(((<=|=|>=){1})((\\+|-){0,1})(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+)){1})";
						if(!line.matches(pattern)) {
							isCorrect = false;
							errorList.add(7);
						}
						
						//Upologismos tis mitras b.
						pattern = ".*<=|.*>=|.*=";
						String[] st2 = line.toLowerCase().split(pattern);
						for(j=0; j<st2.length; j++) {
							if(!st2[j].equals("")) {
								if(Character.isDigit(st2[j].charAt(0))) {
									st2[j] = new StringBuilder(st2[j]).insert(0, "+").toString();  
								}
								b[lineNumber] = st2[j];
							}
						}
						
						//Upologismos mitras Eqin.
						if(line.contains("<=")) {
							line = line.substring(0, line.indexOf("<="));
							Eqin[lineNumber] = "-1";
						} else if(line.contains(">=")) {
							line = line.substring(0, line.indexOf(">="));
							Eqin[lineNumber] = "1";
						} else if(line.contains("=")){
							line = line.substring(0, line.indexOf("="));
							Eqin[lineNumber] = "0";
						}
						
						//Elegxos gia sosto arithmo metavliton stous periorismous.
						if(isCorrect) {
							pattern = "((\\+|-))(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)";
							String[] st6 = line.split(pattern);
							int max = Integer.parseInt(st6[1]);
							for(i=2; i<st6.length; i++) {
								if(Integer.parseInt(st6[i]) > max) {
									max = Integer.parseInt(st6[i]);
								}
								if(Integer.parseInt(st6[i]) <= Integer.parseInt(st6[i-1])) {
									isCorrect = false;     //Elegxos gia auksousa topotethisi ton metavliton.
									errorList.add(8);
								}
							}
							if(max > n) {
								isCorrect = false;
								errorList.add(9);
							}
						}
					
						//Diadikasia upologismou ton theseon pou prepei na topothetithoun stin mitra A, oi suntelestes ton metavliton.
						if(isCorrect) {
							pattern = "(\\+|-)(([0-9]+)|([0-9]+)((\\.|,){1})([0-9]+))([a-z]+)";
							String[] st4 = line.toLowerCase().split(pattern);
							int[] st4Int = new int[st4.length-1];
							for(i=1; i<st4.length; i++) {
								st4Int[i-1] = Integer.parseInt(st4[i]);
							}
						
							//Upologismos mitras A.
							pattern = "[a-z]+[0-9]|<=[0-9]|>=[0-9]|=[0-9]";
							String[] st3 = line.toLowerCase().split(pattern);
							int k = 0;
							for(i=0; i<st4Int[st4Int.length-1]; i++) {
								if(i+1 == st4Int[k]) {
									A[lineNumber][i] = st3[k];
									k++;
								}
							}
						}
						lineNumber++;
					}
					
				}
			}
			s.close();	
		} catch(IOException e) {
			errorList.add(10);
			isCorrect = false;
		}
		return isCorrect;
		
	}
	
	public void writeInFile() {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("Arrays.txt"));
				out.write("A = |");
				for(i=0; i<lineNumber; i++) {
					for(j=0; j<n; j++) {
						if(!(j == (n-1))) {
							out.write(A[i][j]+" ,");
						} else {
							out.write(A[i][j]);
						}
					}
					out.write("|");
					out.newLine();
					if(!(i==(lineNumber-1))) {
						out.write("    |");
					}
				}
				
				out.newLine();
				out.write("b = |");
				for(i=0; i<lineNumber; i++) {
					out.write(b[i]+"|");
					out.newLine();
					if(!(i==(lineNumber-1))) {
						out.write("    |");
					}
				}
				
				out.newLine();
				out.write("c = |");
				for(j=0; j<n; j++) {
					if(!(j == (n-1))) {
						out.write(c[j]+" ,");
					} else {
						out.write(c[j]);
					}
				}
				out.write("|");
				
				out.newLine();
				out.newLine();
				out.write("Eqin = |");
				for(i=0; i<lineNumber; i++) {
					out.write(Eqin[i]+"|");
					out.newLine();
					if(!(i==(lineNumber-1))) {
						out.write("       |");
					}
				}
				
				out.newLine();
				out.write("MinMax = |"+MinMax+"|");
				
				out.close();
		} catch(IOException e) {
			errorList.add(11);	
		}
	}
	
	public void errorGUI() {
		label1 = new JLabel("Den uparxei to Max i Min.");
		label2 = new JLabel("Den uparxei to End.");
		label3 = new JLabel("I antikeimeniki sunartisi den exei sosti morfi.");
		label4 = new JLabel("Oi metavlites tis antikeimenikis sunartisis den einai se auksousa seira.");
		label5 = new JLabel("Oi metavlites tis antikeimenikis sunartisis den einai sosta arithmimenes.");
		label6 = new JLabel("Den uparxei to st.");
		label7 = new JLabel("Oi periorismoi den exoun sosti morfi.");
		label8 = new JLabel("Oi metavlites stous periorismous den einai se auksousa seira.");
		label9 = new JLabel("Oi metavlites stous periorismous den einai sosta arithmimenes.");
		label10 = new JLabel("Provlima me to arxeio eisodou.");
		label11 = new JLabel("Provlima stin eggrafi tou arxeiou.");
			
		Font f = new Font("Arial", Font.BOLD, 20);
			
		label1.setFont(f);
		label2.setFont(f);
		label3.setFont(f);
		label4.setFont(f);
		label5.setFont(f);
		label6.setFont(f);
		label7.setFont(f);
		label8.setFont(f);
		label9.setFont(f);
		label10.setFont(f);
		label11.setFont(f);
			
			
		frame = new JFrame();
		panel = new JPanel(new BorderLayout());
		JPanel panel1 = new JPanel(new GridLayout(11,1));
			
		labelImage = new JLabel();
		ImageIcon icon = new ImageIcon("Cone.png"); 
		labelImage.setIcon(icon);
        
		panel.add(labelImage, BorderLayout.WEST);
			
		for(i=0; i<errorList.size(); i++) {
			if(errorList.get(i) == 1) {
				panel1.add(label1);
			} else if(errorList.get(i) == 2) {
				panel1.add(label2);
			} else if(errorList.get(i) == 3) {
				panel1.add(label3);
			} else if(errorList.get(i) == 4) {
				panel1.add(label4);
			} else if(errorList.get(i) == 5) {
				panel1.add(label5);
			} else if(errorList.get(i) == 6) {
				panel1.add(label6);
			} else if(errorList.get(i) == 7) {
				panel1.add(label7);
			} else if(errorList.get(i) == 8) {
				panel1.add(label8);
			} else if(errorList.get(i) == 9) {
				panel1.add(label9);
			} else if(errorList.get(i) == 10) {
				panel1.add(label10);
			} else if(errorList.get(i) == 11) {
				panel1.add(label11);
			}
		}
			
		panel1.setBackground(Color.yellow);
		panel.add(panel1, BorderLayout.CENTER);
			
		panel.setBackground(Color.yellow);
		frame.setContentPane(panel);
		frame.setTitle("Error Report !!!");
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JOptionPane.showMessageDialog(null, "To grammiko provlima den exei sosti morfi. Den ginetai na paraxthei to apotelesma.", "Syntax Error", JOptionPane.WARNING_MESSAGE);
	}
}
