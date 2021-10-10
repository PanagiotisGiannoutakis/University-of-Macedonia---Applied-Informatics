public class Askisi_4_ProcessToThread_InitThreads {
	
	static int numThreads;
	
	public static void main(String[] args) {
	
		numThreads = Runtime.getRuntime().availableProcessors();
		
		ThreadPrint[] threads = new ThreadPrint[numThreads];
		
		for(int i=0; i<numThreads; i++) {
			System.out.println("In main: Started thread " + i);
			threads[i] = new ThreadPrint(i);
			threads[i].start();
		}
		
		for(int i=0; i<numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
					System.err.println("This should not happen.");
			}
		}
		
		System.out.println("Threads all done !!!");
		
	}
	
	static class ThreadPrint extends Thread {
	
		private int myID;
		
		public ThreadPrint(int myID) {
			this.myID = myID;
		}
		
		public void run() {
			System.out.println("Hello from thread " + myID + " in total " + numThreads + " !!!");
		}
		
	}
	
}