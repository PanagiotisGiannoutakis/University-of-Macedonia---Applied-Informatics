import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class myFrame extends JFrame{
	
	private int patithikeNoumero = 0;
	private String praksi = ""; 
	private boolean eginePraksi = false;
	private boolean clearAfterEqual = true;
	private boolean flagSecondDot = true;
	
	JPanel masterPanel = new JPanel(new BorderLayout());	
	JPanel mainPanel = new JPanel(new GridLayout(4, 4));
	
	JTextField  textField = new JTextField(5);
	
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton buttonDot = new JButton(".");
	JButton buttonC = new JButton("C");
	JButton buttonPlus = new JButton("+");
	JButton buttonRemove = new JButton("-");
	JButton buttonMultiplication = new JButton("*");
	JButton buttonDivision = new JButton("/");
	JButton buttonEqual = new JButton("=");

	public myFrame() {
		mainPanel.add(button7);
		mainPanel.add(button8);
		mainPanel.add(button9);
		mainPanel.add(buttonDivision);
		mainPanel.add(button4);
		mainPanel.add(button5);
		mainPanel.add(button6);
		mainPanel.add(buttonMultiplication);
		mainPanel.add(button1);
		mainPanel.add(button2);
		mainPanel.add(button3);
		mainPanel.add(buttonRemove);
		mainPanel.add(button0);
		mainPanel.add(buttonDot);
		mainPanel.add(buttonC);
		mainPanel.add(buttonPlus);
		
		masterPanel.add(textField, BorderLayout.NORTH);
		masterPanel.add(mainPanel, BorderLayout.CENTER);
		masterPanel.add(buttonEqual, BorderLayout.SOUTH);
		
		button0.addActionListener(new NumberButtonListener());
		button1.addActionListener(new NumberButtonListener());
		button2.addActionListener(new NumberButtonListener());
		button3.addActionListener(new NumberButtonListener());
		button4.addActionListener(new NumberButtonListener());
		button5.addActionListener(new NumberButtonListener());
		button6.addActionListener(new NumberButtonListener());
		button7.addActionListener(new NumberButtonListener());
		button8.addActionListener(new NumberButtonListener());
		button9.addActionListener(new NumberButtonListener());
		buttonPlus.addActionListener(new PrakseisButtonListener());
		buttonRemove.addActionListener(new PrakseisButtonListener());
		buttonMultiplication.addActionListener(new PrakseisButtonListener());
		buttonDivision.addActionListener(new PrakseisButtonListener());
		buttonDot.addActionListener(new NumberButtonListener());
		buttonEqual.addActionListener(new EqualButtonListener());
		buttonC.addActionListener(new NumberButtonListener());
		
		this.setContentPane(masterPanel);
	    this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public class NumberButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (clearAfterEqual == false) {
				textField.setText("");
				clearAfterEqual = true;
			}
			
			if (clearAfterEqual) {
				if(e.getSource() == button0) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("0"));
				} else if (e.getSource() == button1) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("1"));
				} else if (e.getSource() == button2) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("2"));
				} else if (e.getSource() == button3) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("3"));
				} else if (e.getSource() == button4) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("4"));
				} else if (e.getSource() == button5) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("5"));
				} else if (e.getSource() == button6) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("6"));
				} else if (e.getSource() == button7) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("7"));
				} else if (e.getSource() == button8) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("8"));
				} else if (e.getSource() == button9) {
					patithikeNoumero ++;
					textField.setText(textField.getText().concat("9"));
				} else if (e.getSource() == buttonDot) {
					if(((!(textField.getText().endsWith(".")) && (patithikeNoumero != 0) && (flagSecondDot)))) {
						textField.setText(textField.getText().concat("."));
						flagSecondDot = false;
					}
				} else if (e.getSource() == buttonC) {
					textField.setText("");
					patithikeNoumero = 0;
					eginePraksi = false;
					clearAfterEqual = true;
					flagSecondDot = true;
				}
			}	
		}

	}
	
	public class PrakseisButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonPlus) {
				praksi = "\\+";
				textField.setText(textField.getText().concat("+"));
				flagSecondDot = true;
				eginePraksi = true;
			} else if (e.getSource() == buttonRemove) {
				praksi = "-";
				textField.setText(textField.getText().concat("-"));
				flagSecondDot = true;
				eginePraksi = true;
			} else if (e.getSource() == buttonMultiplication) {
				praksi = "\\*";
				textField.setText(textField.getText().concat("*"));
				flagSecondDot = true;
				eginePraksi = true;
			} else if (e.getSource() == buttonDivision) {
				praksi = "/";
				textField.setText(textField.getText().concat("/"));
				flagSecondDot = true;
				eginePraksi = true;
			} 
		}
	}
	
	public class EqualButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonEqual) {
				if ((eginePraksi) && (patithikeNoumero != 0)) {
					String[] splits = textField.getText().split(praksi);
					String stringNumber1 = splits[0];
					String stringNumber2 = splits[1];
					double number1 = Double.parseDouble(stringNumber1);
					double number2 = Double.parseDouble(stringNumber2);
					String stringTotal = "";
					if (praksi.equals("\\+")) {
						textField.setText(stringTotal.valueOf(number1 + number2));
					} else if (praksi.equals("-")) {
						textField.setText(stringTotal.valueOf(number1 - number2));
					} else if (praksi.equals("\\*")) {
						textField.setText(stringTotal.valueOf(number1 * number2));
					} else if (praksi.equals("/")) {
						textField.setText(stringTotal.valueOf(number1 / number2));
					}
					clearAfterEqual = false;
					flagSecondDot = true;
					patithikeNoumero = 0;
				}
			}
		}
	}
}


