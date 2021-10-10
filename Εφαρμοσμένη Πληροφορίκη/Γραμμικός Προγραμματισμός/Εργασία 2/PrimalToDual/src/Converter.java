import java.awt.Desktop;
import java.io.*;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class Converter {
	
	private int n, m, MinMax, i , j;
	
	private String[][] A;
	private String[] b;
	private String[] c;
	private String[] Eqin;
	
	private Parser myParser;
	
	private boolean correct;
	
	public Converter() {
	
		myParser = new Parser();

		initialize();
		
		if(correct) {
			writeInFile();
		
			Desktop dt = Desktop.getDesktop();
        	try {
    			dt.open(new File("Dual Linear Problem.txt") );
    		} catch (IOException e) {
    			JOptionPane.showMessageDialog(null, "To arxeio me to duiko provlima den mporei na anoiksei !!!", "Problem with file", JOptionPane.WARNING_MESSAGE);
    		}
    	}
		
	}
	
	public void initialize() {
		A = myParser.getA();
		b = myParser.getB();
		c = myParser.getC();
		Eqin = myParser.getEqin();
		MinMax = myParser.getMinMax();
		n = myParser.getN();
		m = myParser.getM();
		correct = myParser.getCorrect();
	}
	
	public void writeInFile() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Dual Linear Problem.txt"));
			
			if(MinMax == 1) {
				out.write("Min ");
				for(i=0; i<m; i++) {
					if((i==0) && b[i].startsWith("+")) {
						out.write(b[i].substring(1)+"w"+(i+1)+" ");
					} else {
						out.write(b[i]+"w"+(i+1)+" ");
					}
				}
				out.newLine();
				out.newLine();
				out.write("ST");
				out.newLine();
				for(i=0; i<n; i++) {
					for(j=0; j<m; j++) {
						if(A[j][i].startsWith("+") && (j == 0)) {
							if(A[j][i].equals("0")) {
								out.write("     ");
							} else if(A[j][i].equals("+1")) {
								out.write(A[j][i].substring(0,A[j][i].indexOf("+1"))+"w"+(j+1)+" ");
							} else {
								out.write(A[j][i].substring(1)+"w"+(j+1)+" ");
							}
						} else {
							if(A[j][i].equals("0")) {
								out.write("     ");
							} else if(A[j][i].equals("+1")) {
								out.write(A[j][i].substring(0,A[j][i].indexOf("1"))+"w"+(j+1)+" ");
							} else {
							out.write(A[j][i]+"w"+(j+1)+" ");
							}
						}
					}
					if(c[i].startsWith("+")) {
						out.write(" >= "+c[i].substring(1));
					} else {
						out.write(" >= "+c[i]);
					}
					out.newLine();
				}
				for(j=0; j<m; j++) {
					if(!((j+1) == m)) {
						if(Eqin[j].equals("1")) {
							out.write("w"+(j+1)+"<=0, ");
						} else if(Eqin[j].equals("-1")) {
							out.write("w"+(j+1)+">=0, ");
						} else {
							out.write("     ");
						}
					} else {
						if(Eqin[j].equals("1")) {
							out.write("w"+(j+1)+"<=0");
						} else if(Eqin[j].equals("-1")) {
							out.write("w"+(j+1)+">=0");
						}
					}
				}
			} else {
				out.write("Max ");
				for(i=0; i<m; i++) {
					if((i==0) && (b[i].startsWith("+"))) {
						out.write(b[i].substring(1)+"w"+(i+1)+" ");
					} else {
						out.write(b[i]+"w"+(i+1)+" ");
					}
				}
				out.newLine();
				out.newLine();
				out.write("ST");
				out.newLine();
				for(i=0; i<n; i++) {
					for(j=0; j<m; j++) {
						if(A[j][i].startsWith("+") && (j==0)) {
							if(A[j][i].equals("0")) {
								out.write("     ");
							} else if(A[j][i].equals("+1")) {
								out.write(A[j][i].substring(0,A[j][i].indexOf("+1"))+"w"+(j+1)+" ");
							} else {
								out.write(A[j][i].substring(1)+"w"+(j+1)+" ");
							}
						} else {
							if(A[j][i].equals("0")) {
								out.write("     ");
							} else if(A[j][i].equals("+1")) {
								out.write(A[j][i].substring(0,A[j][i].indexOf("1"))+"w"+(j+1)+" ");
							} else {
								out.write(A[j][i]+"w"+(j+1)+" ");
							}
						}
						
					}
					if(c[i].startsWith("+")) {
						out.write(" <= "+c[i].substring(1));
					} else {
						out.write(" <= "+c[i]);
					}
					out.newLine();
				}
				for(j=0; j<m; j++) {
					if(Eqin[j].equals("1")) {
						out.write("w"+(j+1)+">=0, ");
					} else if(Eqin[j].equals("-1")) {
						out.write("w"+(j+1)+"<=0, ");
					} else {
						out.write("     ");
					}
				}
			}
			out.newLine();
			out.write("End");
			out.close();
		} catch(IOException e) {
			System.out.println("PROVLIMA STIN EGGRAFI");
			e.printStackTrace();	
		}
	}
	
}
