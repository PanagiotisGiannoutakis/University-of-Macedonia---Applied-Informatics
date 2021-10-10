import java.util.*;


public class Electric_Board {
	
	private ArrayList<Electric_Line> lines;
	private double MaxBoard;
	
	public Electric_Board() {
		lines = new ArrayList<Electric_Line>();
		MaxBoard = 0.0;
	}
	
	public void addLines(Electric_Line line) {
		lines.add(line);
	}
	
	public void CalculateMaxBoard() {
		lines.get(0).CalculateMaxLine();
		lines.get(0).EntasiAsfaleias();
		lines.get(0).DiatomiKalodiou();
		MaxBoard = lines.get(0).getMaxLine();
		for(int i=1; i<lines.size(); i++) {
			lines.get(i).CalculateMaxLine();
			if(lines.get(i).getMaxLine() > lines.get(i-1).getMaxLine()) {
				MaxBoard = lines.get(i).getMaxLine();
			}
			lines.get(i).EntasiAsfaleias();
			lines.get(i).DiatomiKalodiou();
		}
	}
	
	public void printResults() {
		for(int i=0; i<lines.size(); i++) {
			System.out.println("I entasi tou asfaleiodiakopti tis ilektrikis grammis " +(i+1)+ " einai " +lines.get(i).getAsfaleia()+ " Ampere kai i diatomi tou kalodiou einai " +lines.get(i).getDiatomi()+ " mm2");
		}
		System.out.println("I megisti entasi tou pinaka gia tin asfaleia tou spitiou einai " +MaxBoard+ " Ampere");
	}
}
