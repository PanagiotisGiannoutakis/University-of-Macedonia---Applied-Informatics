import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Askisi1a {
	
	public static double tSum;
	public static Lock lock = new ReentrantLock();
	
    public static void main(String[] args) {

        int numThreads = 0;
        double sum = 0.0;
		
        /* parse command line */
        if (args.length != 1) {
			System.out.println("arguments:  number_of_threads");
            System.exit(1);
        }
        try {
			numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
			System.out.println("argument "+ args[0] +" must be int");
			System.exit(1);
        }
        /* start timing */
        long startTime = System.currentTimeMillis();

        double step = 1.0 / (double)numThreads;
        
		Askisi1aThread threads[] = new Askisi1aThread[numThreads];
		
		
		/* do computation */
        for (int i=0; i < numThreads; ++i) {
			threads[i] = new Askisi1aThread(step,i,sum);
			threads[i].start();
            //double x = ((double)i+0.5)*step;
            //sum += 4.0/(1.0+x*x);
        }
		
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
           	} catch (InterruptedException e) {}
		}
		
        double pi = tSum * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d threads\n", numThreads);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}