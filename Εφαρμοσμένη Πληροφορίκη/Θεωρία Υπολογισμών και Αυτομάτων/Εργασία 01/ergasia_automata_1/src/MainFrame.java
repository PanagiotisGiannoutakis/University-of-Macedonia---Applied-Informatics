import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.*;

public class MainFrame extends JFrame{
		
	private JLabel label1, label2, label3, label4, label5;
	private JPanel mainPanel, panel1, panel2, continuePanel, continuePanelYes, continuePanelNo, textFieldPanel;
	private JFileChooser jFileChooser;
	private JButton openButton, submitButton, yesButton, noButton;
	private JTextField textField; 
	private boolean flag;
	private int proigoumeniKatastasi, toriniKatastasi;
	private static String[][] pinakasMetavaseon;
	private Automato myAytomato;
	private ArrayList<Integer> telikesKatastaseis;
	private ArrayList<String> alfabito;
	
	public MainFrame() {
		telikesKatastaseis = new ArrayList<Integer>();
	    alfabito = new ArrayList<String>();
		
		flag = false;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9, 1));
		
		panel1 = new JPanel();
		
		panel2 = new JPanel();
		
		textFieldPanel = new JPanel();
		
		continuePanel = new JPanel();
		continuePanel.setLayout(new GridLayout(1, 2));
		
		continuePanelYes = new JPanel();
		
		continuePanelNo = new JPanel();
		
		
		label1 = new JLabel("Δώσε το αρχείο που θα διαβαστεί ο ορισμός του αυτομάτου.", SwingConstants.CENTER);
		label1.setFont(new Font(label1.getFont().getName() ,Font.ITALIC+Font.BOLD, 12));
		
		label2 = new JLabel("", SwingConstants.CENTER);
		label2.setFont(new Font(label2.getFont().getName() ,Font.ITALIC+Font.BOLD, 12));
		
		label3 = new JLabel("", SwingConstants.CENTER);
		
		label4 = new JLabel("", SwingConstants.CENTER);
		label4.setFont(new Font(label4.getFont().getName() ,Font.ITALIC+Font.BOLD, 16));
		
		label5 = new JLabel("", SwingConstants.CENTER);
		label5.setFont(new Font(label5.getFont().getName() ,Font.ITALIC+Font.BOLD, 16));
		
		openButton = new JButton("Διάλεξε ένα αρχείο...");
		openButton.setFont(new Font(openButton.getFont().getName() ,Font.ITALIC+Font.BOLD, 18));
		
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font(submitButton.getFont().getName() ,Font.ITALIC+Font.BOLD, 18));
		
		yesButton = new JButton("Yes");
		yesButton.setFont(new Font(openButton.getFont().getName() ,Font.ITALIC+Font.BOLD, 18));
		
		noButton = new JButton("No");
		noButton.setFont(new Font(openButton.getFont().getName() ,Font.ITALIC+Font.BOLD, 18));
		
		jFileChooser = new JFileChooser();
		
		textField = new JTextField();
		textField.setFont(new Font(textField.getFont().getName() ,Font.ITALIC+Font.BOLD, 18));
		
		mainPanel.add(label1);
		mainPanel.add(panel1);
		panel1.add(openButton);
		mainPanel.add(label2);
		mainPanel.add(label3);
		
		this.setContentPane(mainPanel);
			
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Θεωρία Υπολογισμών και Αυτομάτων: Εργασία 01");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//υλοποίηση ακροατή
		ButtonListener1 listener1 = new ButtonListener1();
		//σύνδεση ακροατή με πηγή συμβάντων (πλήκτρο openButton)
		openButton.addActionListener(listener1);
		
		//υλοποίηση ακροατή
		ButtonListener2 listener2 = new ButtonListener2();
		//σύνδεση ακροατή με πηγή συμβάντων (πλήκτρο submitButton)
		submitButton.addActionListener(listener2);
		
		//υλοποίηση ακροατή
		ButtonListener3 listener3 = new ButtonListener3();
		//σύνδεση ακροατή με πηγή συμβάντων (πλήκτρο yesButton)
		yesButton.addActionListener(listener3);
		
		//υλοποίηση ακροατή
		ButtonListener4 listener4 = new ButtonListener4();
		//σύνδεση ακροατή με πηγή συμβάντων (πλήκτρο noButton)
		noButton.addActionListener(listener4);
		
		jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "eclipse-workspace\\ergasia_automata_1"));
	}
	
	class ButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = jFileChooser.showOpenDialog(new JFrame());
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jFileChooser.getSelectedFile();
				label2.setText("Διαβάστηκε το αρχείο: " + selectedFile.getName());
				label3.setText("Πληκτρολόγησε χαρακτήρες:");
				textField.setColumns(20);
				textFieldPanel.add(textField);
				mainPanel.add(textFieldPanel);
				mainPanel.add(panel2);
				panel2.add(submitButton);
				
				try {
					myAytomato = new Automato(selectedFile);
					pinakasMetavaseon = Automato.getPinakaMetavaseon();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textField.setEditable(false);
			mainPanel.add(label4);
			try {
				flag = elegxosAytomatou();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			if(flag) {
				label4.setText("Το αυτόματο βρέθηκε σε τελική κατάσταση.");
			} else {
				label4.setText("Το αυτόματο βρέθηκε σε μη-τελική κατάσταση.");
			}
			label5.setText("Θέλεις να συνεχίσεις;");
			mainPanel.add(label5);
			continuePanelYes.add(yesButton);
			continuePanel.add(continuePanelYes);
			continuePanelNo.add(noButton);
			continuePanel.add(continuePanelNo);
			mainPanel.add(continuePanel);
			
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		@SuppressWarnings("unlikely-arg-type")
		private boolean elegxosAytomatou() throws BadLocationException {
			toriniKatastasi = myAytomato.getArxikiKatastasi();
		    alfabito = myAytomato.getAlfabito();
			for(int i=0; i<textField.getText().length(); i++) {
				for(int t=0; t<alfabito.size(); t++) {
					if(alfabito.get(t).contains(textField.getText(i,1))) {
						for(int j=0; j<pinakasMetavaseon.length; j++) {
							if(toriniKatastasi == Integer.parseInt(pinakasMetavaseon[j][0])) {
								if(pinakasMetavaseon[j][1].equals(String.valueOf(textField.getText(i,alfabito.get(t).length())))) {
									toriniKatastasi = Integer.parseInt(pinakasMetavaseon[j][2]);
								}
							}
						}
					}
				}
			}
			boolean flag1 = false;
			telikesKatastaseis = myAytomato.getTelikesKatastaseis();
			for(int r=0; r<telikesKatastaseis.size(); r++) {
				if(toriniKatastasi == telikesKatastaseis.get(r)) {
					flag1 = true;
				}
			}
			return flag1;
		}
	}
	
	class ButtonListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textField.setEditable(true);
			textField.setText("");
			label4.setText("");
			label5.setText("");
			mainPanel.remove(continuePanel);
			
			mainPanel.revalidate();
			mainPanel.repaint();
			
			toriniKatastasi = myAytomato.getArxikiKatastasi();
		}
	}
	
	class ButtonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
}
