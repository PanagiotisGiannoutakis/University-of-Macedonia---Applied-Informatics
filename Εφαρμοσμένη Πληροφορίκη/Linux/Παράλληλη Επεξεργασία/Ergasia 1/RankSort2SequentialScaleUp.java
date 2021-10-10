


///////////////////// Το πρόγραμμα έχει συντακτικά λάθη και δεν τρέχει !!!!!!!!!!!







import javax.swing.*;

class RankSort2SequentialScaleUp {
	public static void main(String[] args) {
		
		Timer timer = new Timer(3000, new ActionListener() {
	  		@Override
	  		public void actionPerformed(ActionEvent arg0) {
	    		int N = 10000; // Μεγάλο μέγεθος προβλήματος για να σιγουρευτούμε 
							   //πως δεν θα τελειώσει το πρόγραμμα εξαιτίας του N
	
				int[] X = new int[N]; // Αρχικός πίνακας
				int[] Y = new int[N]; // Τελικός πίνακας
				int i, my_num, my_place, j;
				int size = 0;
		
				// Αρχικοποίηση πινάκων
				for (i=0; i<N; i++) {
					X[i] = N - i;
					Y[i] = 0;
				}

					for (j=0; j<N; j++) {
						my_num = X[j];
						my_place = 0;
						for (i=0; i<N; i++) {
							if ( my_num > X[i] ) {
								my_place++;
							}
						}
						Y[my_place] = my_num;
						size++;
					}
				System.out.println("Size of problem solved: " + size);	
	  		}
		});
		timer.setRepeats(false); // Only execute once
		timer.start(); // Go go go!
		
	}
}

