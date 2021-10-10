/* Simple matrix vector multiplication ax = y */
/* Use -Xmx1024m for larger matrices          */

class MatVecNew
{
	
	private volatile static double[][] a;
	private volatile static double[] x;
    private volatile static double[] y;
	
  public static void main(String args[])
  {
    int size = 0;
	int numThreads = 4;

	
    if (args.length != 1) {
		System.out.println("Usage: java MatVecNew <size>");
		System.exit(1);
    }

    try {
		size = Integer.parseInt(args[0]);
    } catch (NumberFormatException nfe) {
		System.out.println("Integer argument expected");
		System.exit(1);
    }
	
    if (size <= 0) {
		System.out.println("Attention: <size> should be >0");
		System.exit(1);
    }
 
    /* initialization */
    a = new double[size][size];
    for(int i = 0; i < size; i++)
    	for(int j = 0; j < size; j++)
	    a[i][j] = 1;
    
    x = new double[size];
    y = new double[size];
    for(int i = 0; i < size; i++) {
		x[i] = 1;
        y[i] = 0;
    }

    // get current time
    long start = System.currentTimeMillis();
	
	MatVecNewThread threads[] = new MatVecNewThread[numThreads];
	
    for (int i= 0; i < numThreads; i++) {
        threads[i] = new MatVecNewThread(a, x, y, size);
			threads[i].start();
		}

	for (int i = 0; i < numThreads; i++) {
		try {
			threads[i].join();
		} catch (InterruptedException e) {}
	} 
 
    // get current time and calculate elapsed time
    long elapsedTimeMillis = System.currentTimeMillis()-start;
    System.out.println("time in ms = "+ elapsedTimeMillis);

     //for debugging
    for(int i = 0; i < size; i++) 
        System.out.println(y[i]); 
  }
}

class MatVecNewThread extends Thread {
	
	double[][] a;
	double[] x, y;
	int size;
	
	public MatVecNewThread(double[][] a, double[] x, double[] y, int size) {
		this.a = a;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void run() {
		for (int i= 0; i < size; i++) {
			double sum = 0;
			for (int j = 0; j < size; j++) {
				sum = sum + a[i][j]*x[j];
			}
			y[i] = sum;
		}
	}
}