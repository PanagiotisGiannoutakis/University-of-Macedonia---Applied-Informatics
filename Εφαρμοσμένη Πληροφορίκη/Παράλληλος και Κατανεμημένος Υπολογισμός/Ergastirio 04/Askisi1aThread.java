

class Askisi1aThread extends Thread
{
	/*private double [] sums;
    private double [] table;
    private double mySum;
    private int myId;
	private int myStart;
    private int myStop;*/
	public static double step, sum;
	private int myID;

	// constructor
	public Askisi1aThread(double step, int i, double sum)
	{
		this.step = step;
		myID = i;
		this.sum = sum;
		
		/*sums = ts;
        table = array;
        mySum = 0.0;
        myId = id;
		myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) 
			myStop = size;*/
	}

	// thread code
	public void run()
	{	
		double x = ((double)myID+0.5)*step;
		Askisi1a.lock.lock();
        try {
			Askisi1a.tSum += 4.0/(1.0+x*x);
        } finally {
			Askisi1a.lock.unlock();
        }
		//System.out.println("Sum: " + Askisi1a.tSum);
	}
}