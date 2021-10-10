/* Simple matrix vector multiplication ax = y */
/* Use -Xmx1024m for larger matrices          */

class Askisi_3
{
  public static void main(String args[])
  {
    int size = 0;
	int numThreads = 0;
	
    if (args.length != 2) {
		System.out.println("Usage: java Askisi_3 <size> <number of threads>");
		System.exit(1);
    }

    try {
		size = Integer.parseInt(args[0]);
		numThreads = Integer.parseInt(args[1]);
    } catch (NumberFormatException nfe) {
		System.out.println("Integer argument expected");
		System.exit(1);
    }
	
    if (size <= 0) {
		System.out.println("Attention: <size> should be >0");
		System.exit(1);
    }
	
	if (numThreads == 0) {
		numThreads = Runtime.getRuntime().availableProcessors();
	}
	
    /* initialization */
    double[][] a = new double[size][size];
    for(int i = 0; i < size; i++) {
    	for(int j = 0; j < size; j++)
	    a[i][j] = 1;
	}
    
    double[] x = new double[size];
    double[] y = new double[size];
    for(int i = 0; i < size; i++) {
		x[i] = 1;
        y[i] = 0;
    }

    // get current time
    long start = System.currentTimeMillis();
	
	Askisi_3_Thread threads[] = new Askisi_3_Thread[numThreads];
	
	for(int i = 0; i < numThreads; i++) {
		threads[i] = new Askisi_3_Thread(i, numThreads, a, x, y, size); // EROTIMA 3.1 : Oi moirazomenes metavlites einai ta orismata pou pernane sto kathe thread ektos apo to size. Diladi o 
		//arithmos nimatos i kai oi pinakes a[][], x[], y[]. Tis pername san orismata sto thread pou dimiourgoume kai meso kataskeuasti pairnoun tis katalliles times mesa sto nima.
		threads[i].start();
	}

	// wait for threads to terminate            
	for(int i = 0; i < numThreads; i++) {
		try {
			threads[i].join();
        } catch (InterruptedException e) {}
	}
 
    // get current time and calculate elapsed time
    long elapsedTimeMillis = System.currentTimeMillis()-start;
    System.out.println("time in ms = "+ elapsedTimeMillis);

     //for debugging 
    /*for(int i = 0; i < size; i++) {
        System.out.println(y[i]); 
	}*/
  }
}