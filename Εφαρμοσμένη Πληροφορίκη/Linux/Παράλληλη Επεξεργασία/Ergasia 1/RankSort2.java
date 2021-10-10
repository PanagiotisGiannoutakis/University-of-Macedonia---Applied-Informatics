import java.util.*;

class RankSort2 {
	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]); // Μέγεθος προβλήματος
		int numOfThreads = Integer.parseInt(args[1]); // Αριθμός νημάτων
		
		if(numOfThreads == 0) {
			numOfThreads = Runtime.getRuntime().availableProcessors();
		}

		ArrayList<RankSortThread2> threads = new ArrayList<RankSortThread2>();

		int block = N / numOfThreads; // Μέγεθος block(chunk)	
		int[] X = new int[N]; // Αρχικός πίνακας
		int[] Y = new int[N]; // Τελικός πίνακας
		int i;

		boolean semiThread = false;
		
		// Αρχικοποίηση πινάκων
		for (i=0; i<N; i++) {
			X[i] = N - i;
			Y[i] = 0;
		}
		
		long start = System.currentTimeMillis();
		
		// Δημιουργία νήματων
		for(i=0; i<numOfThreads; i++) {
			RankSortThread2 myThread = new RankSortThread2(X,Y,block,N,numOfThreads,semiThread);
			myThread.start();
			threads.add(myThread);
		}
		
		// Δημιουργία επιπλέον ενός νήματος για την επεξεργασία των δεδομένων που δεν έκαναν τα υπόλοιπα νήματα εξαιτίας της
		// μη ακέραιας διαίρεσης του Ν με το numOfThreads.
		if(block*numOfThreads != N) {
			semiThread = true;
			RankSortThread2 myThread = new RankSortThread2(X,Y,N-(block*numOfThreads),N,numOfThreads+1,semiThread);
			myThread.start();
			threads.add(myThread);
		}
		
		//Τερματισμός όλων των νημάτων για να ακολουθήσει η εκτύπωση των αποτελεσμάτων
		for(i=0; i<threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (Exception e) {
				System.out.println(e); 
			}
		}

		long elapsedTimeMillis = System.currentTimeMillis()-start;

		for (i=0; i<N; i++) {
			System.out.println("Y[" + i + "]: " + Y[i]);
		}

		System.out.println("Elapsed time in milliseconds: " + elapsedTimeMillis);
	}
}
