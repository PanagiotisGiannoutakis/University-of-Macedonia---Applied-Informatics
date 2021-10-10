import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuFrame extends JFrame {
	
	JPanel menuPanel = new JPanel(new GridLayout(2, 0));
	
	JButton buttonPlay = new JButton("Play !!");
	JButton buttonAddQuestions = new JButton("Add Questions");
	
	public MenuFrame() {
		
		buttonPlay.setFont(new Font("Jokerman", Font.BOLD, 20));
		buttonAddQuestions.setFont(new Font("Jokerman", Font.BOLD, 20));
		
		buttonPlay.addActionListener(new ButtonListener());
		buttonAddQuestions.addActionListener(new ButtonListener());

		menuPanel.add(buttonPlay);
		menuPanel.add(buttonAddQuestions);

		this.setContentPane(menuPanel);
		this.setTitle("Who wants to be a billionaire!!");
		this.setVisible(true);
		this.setBounds(0, 0, 400, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonAddQuestions) {
				new AddQuestionsFrame();
			} else if (e.getSource() == buttonPlay) {
				new PlayFrame();
			}
			
		}
		
	}
}