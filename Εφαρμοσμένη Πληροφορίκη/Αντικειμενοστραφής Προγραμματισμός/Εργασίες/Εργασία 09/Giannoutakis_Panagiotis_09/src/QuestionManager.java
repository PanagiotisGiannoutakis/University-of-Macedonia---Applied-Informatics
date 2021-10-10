import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class QuestionManager implements Serializable {
	
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	
	public QuestionManager() {
		
	}
	
	public void addQuestions (Questions q) {
		questions.add(q);
	}

	public ArrayList<Questions> getQuestionList() {
		return questions;
	}
	
	public void saveEvolutionDataLocally() {
		try{	
			File outDir = new File("Game_Data");
			if(!outDir.exists())
				outDir.mkdir();
			FileOutputStream fileOut = new FileOutputStream(outDir + "\\QuestionsData.ser" );
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.flush();
			out.close();
			fileOut.close();
		}
		catch(Exception ioe) { 
			ioe.printStackTrace(); 
		}
	}
}
