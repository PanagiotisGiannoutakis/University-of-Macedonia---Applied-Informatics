class Askisi1bThread extends Thread
{
	public static double step;
	public Askisi1bSumObj sum;
	private int myID;

	// constructor
	public Askisi1bThread(double step, int i, Askisi1bSumObj sum)
	{
		this.step = step;
		myID = i;
		this.sum = sum;
	}

	// thread code
	public void run()
	{	
		double mySum;
		sum.add(myID,step);
		//System.out.println("Sum: " + Askisi1b.tSum);
	}
}