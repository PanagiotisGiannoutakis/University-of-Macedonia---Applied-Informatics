import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

//import HelloEvent.Inner;

public class Askisi_5 {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel stopLabel;
   private JPanel controlPanel;
   private JPanel resultTextAreaPanel;
   public static JPanel chooseThreadPanel;
   public static JTextArea resultTextArea;
   public static JTextField chooseThreadTextField;
   public static ArrayList<Thread> threads;
   public static int id;
   public static Thread t;
   public JFrame frame1 = new JFrame("Error");
   public JFrame frame2 = new JFrame("Error");
   public JFrame frame3 = new JFrame("Error");

   
   
   
   public static void main(String[] args){
	   Askisi_5 swingThreadManagmentDemo = new Askisi_5();  
	   swingThreadManagmentDemo.showEventDemo();    

      /* create and start threads */
      threads = new ArrayList<Thread>();
      
      t = Thread.currentThread(); // t einai to arxiko thread.
      id = 1;
      threads.add(t);
      
	  resultTextArea.setText("Main Thread 0: " +Thread.currentThread().getState() + "\n");

      boolean flag = true;
      while(flag) {
    	  resultTextArea.setText("Main Thread 0: " +Thread.currentThread().getState() + "\n"); // Pairnoume tin trexousa katastasi tou main thread pou trexei autin tin stigmi.
    	  for(int i=1; i<threads.size(); i++) {
    		  resultTextArea.append("Thread " + i + " " + threads.get(i).getState() + "\n"); // pairnoyme tin katastasi ton threads.
    	  }
    	  try {
    		  t.sleep(1000);
    	  } catch (InterruptedException e) {
    		  System.exit(0);
    	  }
      }
   }
   
   public Askisi_5(){
      prepareGUI();
   }
   
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Thread Managment");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(5, 1));
      mainFrame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
      
      headerLabel = new JLabel("",JLabel.CENTER );
      stopLabel = new JLabel("",JLabel.CENTER );
      resultTextArea = new JTextArea(5,25);
      chooseThreadTextField = new JTextField();
      chooseThreadTextField.setColumns(10);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      
      resultTextAreaPanel = new JPanel();
      resultTextAreaPanel.setLayout(new FlowLayout());
      
      chooseThreadPanel = new JPanel();
      
      mainFrame.add(headerLabel);
      
      mainFrame.add(controlPanel);
      mainFrame.add(resultTextAreaPanel);
      resultTextAreaPanel.add(resultTextArea);
      JScrollPane scrollPane = new JScrollPane(resultTextArea); 
      resultTextAreaPanel.add(scrollPane);
      mainFrame.setVisible(true);  
      
      frame1.setLayout(new GridLayout(2, 1));
	  JPanel myPanel1 = new JPanel();
	  frame1.setPreferredSize(new Dimension(400,150));
	  frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  JLabel textLabel1 = new JLabel("You need to count the thread you want to terminate first !!!",SwingConstants.CENTER);
	  textLabel1.setPreferredSize(new Dimension(300, 300));
	  JButton okButton1 = new JButton("OK");
	  frame1.add(textLabel1);
	  myPanel1.add(okButton1);
	  frame1.add(myPanel1);
	  frame1.setLocationRelativeTo(null);
	  frame1.pack();
	  frame1.setVisible(false);
	  okButton1.setActionCommand("OK1");
	  okButton1.addActionListener(new ButtonClickListener());
	  
	  frame2.setLayout(new GridLayout(2, 1));
	  JPanel myPanel2 = new JPanel();
	  frame2.setPreferredSize(new Dimension(400,150));
	  frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  JLabel textLabel2 = new JLabel("There is no thread you want to delete !!!",SwingConstants.CENTER);
	  textLabel2.setPreferredSize(new Dimension(300, 300));
	  JButton okButton2 = new JButton("OK");
	  frame2.add(textLabel2);
	  myPanel2.add(okButton2);
	  frame2.add(myPanel2);
	  frame2.setLocationRelativeTo(null);
	  frame2.pack();
	  frame2.setVisible(false);
	  okButton2.setActionCommand("OK2");
	  okButton2.addActionListener(new ButtonClickListener());
	  
	  frame3.setLayout(new GridLayout(2, 1));
	  JPanel myPanel3 = new JPanel();
	  frame3.setPreferredSize(new Dimension(400,150));
	  frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  JLabel textLabel3 = new JLabel("Thread is terminated. Build a new one !!!",SwingConstants.CENTER);
	  textLabel3.setPreferredSize(new Dimension(300, 300));
	  JButton okButton3 = new JButton("OK");
	  frame3.add(textLabel3);
	  myPanel3.add(okButton3);
	  frame3.add(myPanel3);
	  frame3.setLocationRelativeTo(null);
	  frame3.pack();
	  frame3.setVisible(false);
	  okButton3.setActionCommand("OK3");
	  okButton3.addActionListener(new ButtonClickListener());
   }
   
   private void showEventDemo(){
	   headerLabel.setText("Press Start to start a thread, Stop to finish."); 
	   stopLabel.setText("What thread do you want to end?"); 

	   JButton startButton = new JButton("Start");
	   JButton stopButton = new JButton("Stop");

	   startButton.setActionCommand("Start");
	   stopButton.setActionCommand("Stop");
	   startButton.addActionListener(new ButtonClickListener()); 
	   stopButton.addActionListener(new ButtonClickListener());
	   controlPanel.add(startButton); 
	   controlPanel.add(stopButton);
	   mainFrame.add(stopLabel);
	   mainFrame.add(chooseThreadPanel);
	   chooseThreadPanel.add(chooseThreadTextField);
	   mainFrame.setVisible(true);  
   }
   
   private class ButtonClickListener implements ActionListener{
	   
	   public void actionPerformed(ActionEvent e) {
		   String command = e.getActionCommand();  
         
		   if( command.equals( "Start" ))  {
 	 
				   threads.add(new Thread(new Inner(id))); // Dimiourgoume ta nea threads kathe fora pou patietai to koumpi Start.
				   id = id + 1;
				   threads.get(threads.size()-1).start(); // arxizoume ta threads otan patithei to pliktro Start.
				   
		   } else if( command.equals( "Stop" )) {
			   
			   if(chooseThreadTextField.getText().equals("")) {
				   frame1.setVisible(true);
			   } else if((!(Integer.parseInt(chooseThreadTextField.getText()) <= (threads.size()-1))) || (Integer.parseInt(chooseThreadTextField.getText()) < 0)) {
				   frame2.setVisible(true);
			   } else if(!threads.get(Integer.parseInt(chooseThreadTextField.getText())).isAlive()) {
				   frame3.setVisible(true);
			   } else {
				   Thread threadToStop = threads.get(Integer.parseInt(chooseThreadTextField.getText())); // Dialegoume to nima pou einai na ginei interrupt analoga me to pedio pou exei to TextField,
				   // dld ton arithmo tou nimatos.
				   threadToStop.interrupt(); // Kanoume interrupt to nima otan patithei to koumpi Stop.
			   }
        	 
		   } else if( command.equals( "OK1" )) {

			   frame1.setVisible(false);
			   
		   } else if( command.equals( "OK2" )) {
			  
			   frame2.setVisible(false);
			   
		   } else if( command.equals( "OK3" )) {
			  
			   frame3.setVisible(false);
			   
		   }
	   }	
      
   	}
   
   /* inner class containing code for each thread to execute */
   private class Inner implements Runnable {

       private long myID;

       public Inner(int id) {
           myID = id;
       }

       public void run() {
    	   while (true) {
           		if(Thread.currentThread().isInterrupted()) {
           			break; // Stamataei to nima mono otan ginei unterrupted. Allios trexei sunexeia.
           		}
    	   }
      }
   }
   
}
