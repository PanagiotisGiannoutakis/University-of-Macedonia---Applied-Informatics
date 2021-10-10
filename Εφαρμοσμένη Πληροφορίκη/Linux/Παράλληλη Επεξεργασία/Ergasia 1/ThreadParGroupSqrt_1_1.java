class ThreadParGroupSqrt_1_1
{
	public static void main(String[] args)
	{	
		int i, j;
		int size = Integer.parseInt(args[0]); //Μέγεθος του προβλήματος
		int numOfThreads = Integer.parseInt(args[1]); // Αριθμός νημάτων
		String[] timeResult = new String[numOfThreads]; // Πίνακας με τα αποτελέσματα χρόνου για κάθε νήμα
		long start, elapsedTimeMillis;
		
		if(numOfThreads == 0) {
			numOfThreads = Runtime.getRuntime().availableProcessors();
		}

		int block = size / numOfThreads;	
		System.out.println("-----------------------\nSize: " + size + "\nNumber Of Threads: " + numOfThreads + "\nBlock: " + block + "\n-----------------------");	

		// Δημιουργία και αρχικοποίηση πίνακα
		double[] a = new double[size];
		for(i = 0; i < size; i++)
			a[i] = i; 

		// Δημιουργία πίνακα νημάτων
		SqrtGroupThread_1_1 threads[] = new SqrtGroupThread_1_1[numOfThreads];
		
		// Get current time
        	start = System.currentTimeMillis();

		// Δημιουργία και εκκίνηση νημάτων
		for(i = 0, j = 0; i < numOfThreads; i++,j += block) 
		{
			if(i == numOfThreads-1) {
				threads[i] = new SqrtGroupThread_1_1(a,i,j, block, size, timeResult);
			} else {
				threads[i] = new SqrtGroupThread_1_1(a,i,j, block, timeResult);
			}
			threads[i].start();
		}

		// Get elapsed time in milliseconds
                elapsedTimeMillis = System.currentTimeMillis()-start;

		// Εμφάνιση στοιχείων πίνακα
		for(i = 0; i < size; i++) 
		{
			System.out.println(a[i]);
		}

		System.out.println("-----------------------");

		for(i=0; i<numOfThreads; i++) {
			System.out.println(timeResult[i]);
		}

		System.out.println("Elapsed Time in milliseconds: " + elapsedTimeMillis);
	}
}
