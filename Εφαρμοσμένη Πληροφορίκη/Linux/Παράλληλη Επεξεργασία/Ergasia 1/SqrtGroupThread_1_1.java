class SqrtGroupThread_1_1 extends Thread
{
	private double[] table;
	private int index, myrank, block, size, i;
	private String[] a;
	private long start, elapsedTimeMillis;
	

	// Κατασκευαστής 1
	public SqrtGroupThread_1_1(double[] array, int rank, int ind, int chunk, String[] timeResult)
	{
		table = array;
		index = ind;
		myrank = rank;
		block = chunk;
		size = -1;
		a = timeResult;
	}

	// Κατασκευαστής 2
	public SqrtGroupThread_1_1(double[] array, int rank, int ind, int chunk, int problemSize, String[] timeResult)
	{
		table = array;
		index = ind;
		myrank = rank;
		block = chunk;
		size = problemSize;
		a = timeResult;
	}

	// Κάθε νήμα υπολογίζει την τετραγωνική ρίζα των "block" στοιχείων
	// πίνακα σειριακά
	public void run()
	{
		// Get current time
        	start = System.currentTimeMillis();

		if(size != -1) {
			for(i=index; i<size; i++) 
				table[i] = Math.sqrt(table[i]);
		} else {
			for(i=index; i<(myrank*block)+block; i++) 
				table[i] = Math.sqrt(table[i]);
		}
	
                // Get elapsed time in milliseconds
                elapsedTimeMillis = System.currentTimeMillis()-start;
	        a[myrank] = "Elapsed Time for " + getName() + " in " + elapsedTimeMillis + " milliseconds.";
	}
}
