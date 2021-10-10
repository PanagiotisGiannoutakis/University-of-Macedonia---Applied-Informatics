class RankSortThread2 extends Thread
{
	private int[] X, Y;
	private int index, block, j, i, N, threads, my_place, my_num, id;

	boolean semiThread;

	// Κατασκευαστής 
	public RankSortThread2(int[] input, int[] output, int chunk, int size, int numOfThreads, boolean typeOfThread)
	{
		X = input;
		Y = output;
		block = chunk;
		N = size;
		threads = numOfThreads;
		id = (int) (long) getId()%numOfThreads;
		semiThread = typeOfThread;
	}

	// Επεξεργασία συγκεκριμένων δεδομένων για το νήμα
	public void run()
	{
		if (semiThread) {
			for (j=N-block; j<N; j++) {
				my_num = X[j];
			     	my_place = 0;
			     	for (i=0; i<N; i++) {
					if ( my_num > X[i] ) {
						my_place++;
					}
				}
			     	Y[my_place] = my_num;
			}
		} else {
			for (j=id*block; j<(id*block)+block; j++) {
				my_num = X[j];
			     	my_place = 0;
			     	for (i=0; i<N; i++) {
					if ( my_num > X[i] ) {
						my_place++;
					}
				}
			     	Y[my_place] = my_num;
			}
		}
		
	}
}
