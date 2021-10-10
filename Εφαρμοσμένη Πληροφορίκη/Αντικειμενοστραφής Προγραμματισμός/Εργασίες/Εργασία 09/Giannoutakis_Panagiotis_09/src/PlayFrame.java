import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class PlayFrame extends JFrame{
	
	ArrayList<Questions> usedQuestions = new ArrayList<Questions>();
	
	private QuestionManager questionManager;
	private ArrayList<Questions> questions;
	private int correctAnswers = 0;
	private int wrongAnswers = 0;
	private int currentAnswer = 0;
	private Questions randomQuestion;
	private int currentNumberQuestion = 0;
	private boolean firstClose = false;
	
	JFrame playFrame = new JFrame();
	
	JLabel label1 = new JLabel("Question: ");
	JLabel labelQuestion = new JLabel("");
	JLabel labelCorrect = new JLabel("Correct Answers: " +correctAnswers);
	JLabel labelWrong = new JLabel("     Wrong Answers: " +wrongAnswers);
	
	JRadioButton radioButton1 = new JRadioButton("");
	JRadioButton radioButton2 = new JRadioButton("");
	JRadioButton radioButton3 = new JRadioButton("");
	JRadioButton radioButton4 = new JRadioButton("");
	
	JButton buttonCommit = new JButton("Commit");
	
	JPanel mainPanel = new JPanel(new GridLayout(5, 1));
	JPanel panelQuestion = new JPanel(new FlowLayout());
	JPanel panelRadioButtons = new JPanel(new FlowLayout());
	JPanel panelResults = new JPanel(new FlowLayout());
	JPanel panelCommit = new JPanel();
	
	ButtonGroup buttonGroup = new ButtonGroup();
	
	public PlayFrame() {
		
		try {
			File inDir = new File("Game_Data");
			if(!inDir.exists()) {
				inDir.mkdir();
			}
			FileInputStream fileIn = new FileInputStream(inDir + "\\QuestionsData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			questionManager = (QuestionManager) in.readObject();
			questions = questionManager.getQuestionList();
			in.close();
			fileIn.close();		
		}
		catch(IOException i) {
			JOptionPane.showMessageDialog (null, "You should make questions at first.", "Warning", JOptionPane.WARNING_MESSAGE);
			firstClose = true;
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		label1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		labelQuestion.setFont(new Font("Lucida Bright", Font.ITALIC, 20));
		labelCorrect.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		labelWrong.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		labelCorrect.setForeground(Color.BLUE);
		labelWrong.setForeground(Color.RED);
		
		panelQuestion.add(label1);
		panelQuestion.add(labelQuestion);
		
		panelRadioButtons.add(radioButton1);
		panelRadioButtons.add(radioButton2);
		panelRadioButtons.add(radioButton3);
		panelRadioButtons.add(radioButton4);
		
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		buttonGroup.add(radioButton4);
		
		panelResults.add(labelCorrect);
		panelResults.add(labelWrong);
		
		panelCommit.add(buttonCommit);
		
		mainPanel.add(panelQuestion);
		mainPanel.add(panelRadioButtons);
		mainPanel.add(panelCommit);
		mainPanel.add(panelResults);
		
		buttonCommit.addActionListener(new ButtonListener());
		
		radioButton1.addActionListener(new RadioButtonListener());
		radioButton2.addActionListener(new RadioButtonListener());
		radioButton3.addActionListener(new RadioButtonListener());
		radioButton4.addActionListener(new RadioButtonListener());
		
		playFrame.setContentPane(mainPanel);
		playFrame.setTitle("Play!");
		playFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		playFrame.setBounds(0, 0, 650, 250);
		if(!firstClose) {
			playFrame.setVisible(true);
		}
		
		this.afterCommit();
	}
	
	public void afterCommit() {
		
		currentNumberQuestion++;
		Random r = new Random(System.currentTimeMillis());
		int randomIndex = r.nextInt(questions.size());
		randomQuestion = questions.get(randomIndex);
			
		while(usedQuestions.contains(randomQuestion)){
			randomIndex = r.nextInt(questions.size());
			randomQuestion = questions.get(randomIndex);
		}
		usedQuestions.add(randomQuestion);
		
		labelQuestion.setText(randomQuestion.getQuestion());
			
		radioButton1.setText(randomQuestion.getAnswer1());
		radioButton2.setText(randomQuestion.getAnswer2());
		radioButton3.setText(randomQuestion.getAnswer3());
		radioButton4.setText(randomQuestion.getAnswer4());
			
		labelCorrect.setText("Correct Answers: " +correctAnswers);
		labelWrong.setText("    Wrong Answers: " +wrongAnswers);
	}
	
	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(currentAnswer == randomQuestion.getCorrectAnswer()) {
				correctAnswers++;
				JOptionPane.showMessageDialog (null, "Answer Correct!!!", "Correct!!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				wrongAnswers++;
				 JOptionPane.showMessageDialog (null, "Sorry! wrong answer!\n Correct Answer is " +randomQuestion.getCorrectAnswer(), "Wrong", JOptionPane.ERROR_MESSAGE);
			}
			
			labelCorrect.setText("Correct Answers: " +correctAnswers);
			labelWrong.setText("     Wrong Answers: " +wrongAnswers);
			buttonGroup.clearSelection();
			
			if(currentNumberQuestion == questions.size()) {
				
				if(correctAnswers > wrongAnswers) {
					JOptionPane.showMessageDialog (null, "Congratulations, You're a genius", "Win !!!", JOptionPane.INFORMATION_MESSAGE);
					playFrame.setVisible(false);
				} else if (correctAnswers < wrongAnswers) {
					JOptionPane.showMessageDialog (null, "Sorry,You lose. Go back to school again !!", "Lose", JOptionPane.WARNING_MESSAGE);
					playFrame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog (null, "Try again", "A Draw !", JOptionPane.NO_OPTION);
					playFrame.setVisible(false);
				}
			} else {
				afterCommit();
			}
		}
	}
	
	public class RadioButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
	         if (e.getSource() == radioButton1) {
	        	 currentAnswer = 1;
	         } else if (e.getSource() == radioButton2) {
	        	 currentAnswer = 2;
	         } else if (e.getSource() == radioButton3) {
	        	 currentAnswer = 3;
	         } else if (e.getSource() == radioButton4) {
	        	 currentAnswer = 4;
	         }
		}
	}
}