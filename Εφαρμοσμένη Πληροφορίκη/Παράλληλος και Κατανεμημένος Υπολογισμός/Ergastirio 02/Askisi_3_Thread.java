class Askisi_3_Thread extends Thread
{
	private double [][] a;
	private double [] x, y;
	private int myStart;
    private int myStop;

	// constructor
	public Askisi_3_Thread(int myId, int numThreads, double[][] arrayA, double[] arrayX, double[] arrayY, int size)
	{
		a = arrayA;
		x = arrayX;
		y = arrayY;
		myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) 
			myStop = size;
	}

	// thread code
	public void run()
	{
		for (int i= myStart; i < myStop; i++) {
			double sum = 0;
			for (int j = myStart; j < myStop; j++) {
				for (int k= myStart; k < myStop; k++) // EROTIMA 3.3 Edo epeleksa na min trexo olo to for-loop apo to 0 eos to size alla apo to myStart eos to myStop.
					sum = sum + a[i][j]*x[j]; 
			}
			y[i] = sum;
		}
	}
}