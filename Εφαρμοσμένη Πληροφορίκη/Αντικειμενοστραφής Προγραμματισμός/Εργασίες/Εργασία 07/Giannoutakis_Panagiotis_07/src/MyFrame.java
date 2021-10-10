
import java.text.DecimalFormat;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	private ArrayList<DEHInvoice> logariasmoi;
	
	JPanel panel = new JPanel();
	
	JTextField field1 = new JTextField(5);
	JTextField field2 = new JTextField(5);
	JTextField field3 = new JTextField(5);
	
	JButton button1 = new JButton("Create Normal House Invoice");
	JButton button2 = new JButton("Create House Night Invoice");
	JButton button3 = new JButton("Create Industrial Invoice");
	JButton button4 = new JButton("Print Total");
	
	JLabel label1 = new JLabel("Kodikos Paroxis: ");
	JLabel label2 = new JLabel("kWh: ");
	JLabel label3 = new JLabel("Square Meters: ");

	
	public MyFrame() {
		
		logariasmoi = new ArrayList<DEHInvoice>();
		
		panel.add(label1);
		panel.add(field1);
		
		panel.add(label2);
		panel.add(field2);
		
		panel.add(label3);
		panel.add(field3);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		button1.addActionListener(new NormalButtonListener());
		button2.addActionListener(new NightButtonListener());
		button3.addActionListener(new IndustrialButtonListener());
		button4.addActionListener(new PrintButtonListener());
		
		this.setTitle("DEH APPlication");
		this.setContentPane(panel);
		this.setSize(600,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addLogariasmoi(DEHInvoice logariasmos) {
		logariasmoi.add(logariasmos);
	}
		
	public class NormalButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			if((field1.getText().length() == 0) || (field2.getText().length() == 0) || (field3.getText().length() == 0)) {
				JOptionPane.showMessageDialog (null, "You haven't fill in all fields !", "Warning", JOptionPane.WARNING_MESSAGE);
				field1.setText(""); 
				field2.setText("");
				field3.setText("");
			}
			
			int kodikos = Integer.parseInt(field1.getText());
			double kWh = Double.parseDouble(field2.getText());
			double Square = Double.parseDouble(field3.getText());
			 
			field1.setText(""); 
			field2.setText("");
			field3.setText("");
			
			logariasmoi.add(new HouseInvoice(kodikos, kWh, Square));
	
		}
	}
	
	public class NightButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			JFrame frame = new JFrame();
		    String text = JOptionPane.showInputDialog(frame,"Please type night kilowatt hours");
		    if (text == null) {
		    	JOptionPane.showMessageDialog (null, "You haven't fill the night kWh !", "Warning", JOptionPane.WARNING_MESSAGE);
		    }
			
			if((field1.getText().length() == 0) || (field2.getText().length() == 0) || (field3.getText().length() == 0)) {
				JOptionPane.showMessageDialog (null, "You haven't fill in all fields !", "Warning", JOptionPane.WARNING_MESSAGE);
				field1.setText(""); 
				field2.setText("");
				field3.setText("");
			}
			
			int kodikos = Integer.parseInt(field1.getText());
			double kWh = Double.parseDouble(field2.getText());
			double Square = Double.parseDouble(field3.getText());
			double nightkWh = Double.parseDouble(text);
			 
			field1.setText(""); 
			field2.setText("");
			field3.setText("");
			text = "";
			
			logariasmoi.add(new HouseNightInvoice(kodikos, kWh, Square, nightkWh));
	
		}
	}

	public class IndustrialButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			if((field1.getText().length() == 0) || (field2.getText().length() == 0)) {
				JOptionPane.showMessageDialog (null, "You haven't fill in all fields !", "Warning", JOptionPane.WARNING_MESSAGE);
				field1.setText(""); 
				field2.setText("");
			}
			
			int kodikos = Integer.parseInt(field1.getText());
			double kWh = Double.parseDouble(field2.getText());
			 
			field1.setText(""); 
			field2.setText("");
			
			logariasmoi.add(new IndustrialInvoice(kodikos, kWh));
	
		}
	}
	
	public class PrintButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			DecimalFormat myFormat = new DecimalFormat(".###");
			
			double totalAmount = 0.0;
			for(int i=0; i<logariasmoi.size(); i++) {
				totalAmount += logariasmoi.get(i).upologismosKostous();
				System.out.println("Invoice of the " +logariasmoi.get(i).readInfo()+ " customer with supply number " +logariasmoi.get(i).kodikos+ " Total cost = " +myFormat.format(logariasmoi.get(i).upologismosKostous()));
			}
			
			JOptionPane.showMessageDialog (null, "Total amount = " +myFormat.format(totalAmount)+ " euros", "Message", JOptionPane.INFORMATION_MESSAGE);
	
		}
	}
	
}
