import java.util.*;

class RankSort2Sequential {
	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]); // Μέγεθος προβλήματος
	
		int[] X = new int[N]; // Αρχικός πίνακας
		int[] Y = new int[N]; // Τελικός πίνακας
		int i, my_num, my_place, j;
		
		// Αρχικοποίηση πινάκων
		for (i=0; i<N; i++) {
			X[i] = N - i;
			Y[i] = 0;
		}
		
		long start = System.currentTimeMillis();
		
		for (j=0; j<N; j++) {
			my_num = X[j];
			my_place = 0;
				for (i=0; i<N; i++) {
					if ( my_num > X[i] ) {
						my_place++;
					}
				}
			Y[my_place] = my_num;
		}

		long elapsedTimeMillis = System.currentTimeMillis()-start;

		for (i=0; i<N; i++) {
			System.out.println("Y[" + i + "]: " + Y[i]);
		}

		System.out.println("Elapsed time in milliseconds: " + elapsedTimeMillis);
	}
}

