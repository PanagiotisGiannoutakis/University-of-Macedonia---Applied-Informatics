public class SharedCounterArrayGlobalWhileNew1 {
  
    static volatile int end = 10000;
    static volatile int counter = 0;
    static volatile int[] array = new int[end];
    static volatile int numThreads = 4;

    public static void main(String[] args) {

		CounterThread threads[] = new CounterThread[SharedCounterArrayGlobalWhileNew1.numThreads];
		
		for (int i = 0; i < SharedCounterArrayGlobalWhileNew1.numThreads; i++) {
			threads[i] = new CounterThread();
			threads[i].start();
		}

		for (int i = 0; i < SharedCounterArrayGlobalWhileNew1.numThreads; i++) {
			try {
			threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
		
        check_array ();
    }
     
    static void check_array ()  {
		int i, errors = 0;

		System.out.println ("Checking...");

			for (i = 0; i < end; i++) {
				if (SharedCounterArrayGlobalWhileNew1.array[i] != 1) {
					errors++;
					System.out.printf("%d: %d should be 1\n", i, SharedCounterArrayGlobalWhileNew1.array[i]);
				}         
			}
		System.out.println (errors+" errors.");
	}


    static class CounterThread extends Thread {
  	
       public CounterThread() {
       }
  	
       public void run() {
  
            while (true) {
				if (SharedCounterArrayGlobalWhileNew1.counter >= SharedCounterArrayGlobalWhileNew1.end) 
					break;
				SharedCounterArrayGlobalWhileNew1.array[SharedCounterArrayGlobalWhileNew1.counter]++;
				SharedCounterArrayGlobalWhileNew1.counter++;		
			}
       }            	
    }
}