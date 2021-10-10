import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;

public class AddQuestionsFrame extends JFrame {
	
	private QuestionManager questionManager;
	private int correct = 0;

	JFrame addQuestionsFrame = new JFrame();
	
	JPanel panelQuestion = new JPanel();
	JPanel mainPanelAnswer = new JPanel(new GridLayout(4, 0));
	JPanel panelAnswer1 = new JPanel(new FlowLayout());
	JPanel panelAnswer2 = new JPanel(new FlowLayout());
	JPanel panelAnswer3 = new JPanel(new FlowLayout());
	JPanel panelAnswer4 = new JPanel(new FlowLayout());
	JPanel panelButton = new JPanel(new FlowLayout());
	JPanel mainPanel = new JPanel(new BorderLayout());
	
	JLabel labelQuestion = new JLabel("Question: ");
	
	JTextField fieldQuestion = new JTextField(55);
	JTextField fieldAnswer1 = new JTextField(55);
	JTextField fieldAnswer2 = new JTextField(55);
	JTextField fieldAnswer3 = new JTextField(55);
	JTextField fieldAnswer4 = new JTextField(55);
	
	JRadioButton radioButton1 = new JRadioButton("Answer 1 ");
	JRadioButton radioButton2 = new JRadioButton("Answer 2 ");
	JRadioButton radioButton3 = new JRadioButton("Answer 3 ");
	JRadioButton radioButton4 = new JRadioButton("Answer 4 ");
	
	ButtonGroup buttonGroup = new ButtonGroup();
	
	JButton buttonMake = new JButton("Make Question");
	JButton buttonSave = new JButton("Save Questions to File");
	
	public AddQuestionsFrame() {
		
		try {
			File inDir = new File("Game_Data");
			if(!inDir.exists()) {
				inDir.mkdir();
			}
			FileInputStream fileIn = new FileInputStream(inDir + "\\QuestionsData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			questionManager = (QuestionManager) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			questionManager = new QuestionManager();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		panelQuestion.add(labelQuestion);
		panelQuestion.add(fieldQuestion);
		
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		buttonGroup.add(radioButton4);
		
		panelAnswer1.add(radioButton1);
		panelAnswer1.add(fieldAnswer1);
		panelAnswer2.add(radioButton2);
		panelAnswer2.add(fieldAnswer2);
		panelAnswer3.add(radioButton3);
		panelAnswer3.add(fieldAnswer3);
		panelAnswer4.add(radioButton4);
		panelAnswer4.add(fieldAnswer4);
		mainPanelAnswer.add(panelAnswer1);
		mainPanelAnswer.add(panelAnswer2);
		mainPanelAnswer.add(panelAnswer3);
		mainPanelAnswer.add(panelAnswer4);
		
		panelButton.add(buttonMake);
		panelButton.add(buttonSave);
		
		buttonMake.addActionListener(new ButtonListener());
		buttonSave.addActionListener(new ButtonListener());
		
		radioButton1.addActionListener(new RadioButtonListener());
		radioButton2.addActionListener(new RadioButtonListener());
		radioButton3.addActionListener(new RadioButtonListener());
		radioButton4.addActionListener(new RadioButtonListener());
		
		mainPanel.add(panelQuestion, BorderLayout.NORTH);
		mainPanel.add(mainPanelAnswer, BorderLayout.CENTER);
		mainPanel.add(panelButton, BorderLayout.SOUTH);
		
		addQuestionsFrame.setContentPane(mainPanel);
		addQuestionsFrame.setTitle("Add Question");
		addQuestionsFrame.pack();
		addQuestionsFrame.setVisible(true);
		addQuestionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == buttonMake) {
				if(sameQuestion()) {
					JOptionPane.showMessageDialog (null, "Has put questions that are already !!!", "Error", JOptionPane.WARNING_MESSAGE);
					clearFields();
				} else {
					questionManager.addQuestions(new Questions(fieldQuestion.getText(), fieldAnswer1.getText(), fieldAnswer2.getText(), fieldAnswer3.getText(), fieldAnswer4.getText(), correct));
					clearFields();
					JOptionPane.showMessageDialog (null, "Question added !", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (e.getSource() == buttonSave) {
				questionManager.saveEvolutionDataLocally();
				addQuestionsFrame.setVisible(false);
			}
		}
		
		private boolean sameQuestion() {
			for (int i=0; i<questionManager.getQuestionList().size(); i++) {
				if(fieldQuestion.getText().equals(questionManager.getQuestionList().get(i).getQuestion())) {
					return true;
				}
			}
			return false;
		}
		
		private void clearFields() {
			fieldQuestion.setText("");
			fieldAnswer1.setText("");
			fieldAnswer2.setText("");
			fieldAnswer3.setText("");
			fieldAnswer4.setText("");
			buttonGroup.clearSelection();
		}	
	}

	public class RadioButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
	         if (e.getSource() == radioButton1) {
	        	 correct = 1;
	         } else if (e.getSource() == radioButton2) {
	        	 correct = 2;
	         } else if (e.getSource() == radioButton3) {
	        	 correct = 3;
	         } else if (e.getSource() == radioButton4) {
	        	 correct = 4;
	         }
		}
	}

}
