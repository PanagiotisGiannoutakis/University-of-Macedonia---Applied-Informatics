public class Askisi_3 {
	
	static int numThreads;
	
	public static void main(String[] args) {
	
		if(args.length != 1) { // Elegxos gia to an ta arxika orismata tou programmatos pou pername einai akrivos 1.
			System.out.println("Arguments must be 1 only !!!");
			System.exit(1);
		} else {
			try {
				numThreads = Integer.parseInt(args[0]); // To proto orisma pou dinoume einai o arithmos ton nimaton.
			} catch (NumberFormatException e) {
				System.out.println("Argument must be integer !!!");
				System.exit(1);
			}
		}
		
		if(numThreads <= 0) { // an dosoume arnitiko arithmo gia orisma tote o arithmos ton nimaton tha einai oi diathesimoi epeskergastes tou upologisti.
			numThreads = Runtime.getRuntime().availableProcessors();
		}
		
		ThreadPrint[] threads = new ThreadPrint[numThreads];
		
		for(int i=0; i<numThreads; i++) {
			System.out.println("In main: Started thread " + (i+1)); // Stin emfanisi ta apotelesmata ksekinane apo to 1 kai oxi apo to 0.
			threads[i] = new ThreadPrint((i+1),""); // To proto orisma sto nima pou ftiaxnoume, einai to ID tou, to deutero afora sto an einai anadromiko to nima pou dimiourgoume
			// opote stin arxiki periptosi pou den einai apla pername to keno String "".
			threads[i].start(); // Ksekiname ta nimata.
		}
		
		for(int i=0; i<numThreads; i++) {
			try {
				threads[i].join(); // Perimenoume na termatisoun ola ta nimata.
			} catch (InterruptedException e) {
					System.err.println("This should not happen.");
			}
		}
		
		System.out.println("Threads all done !!!");
		
	}
	
	static class ThreadPrint extends Thread {
	
		private int myID;
		private String noRecThread; // Metavliti pou dilonei poio anadromiko nima einai. Mporei na parei times a kai b gia ta anadromika nimata kai "" gia to mi anadromiko.
		
		public ThreadPrint(int myID, String noRecThread) {
			this.myID = myID;
			this.noRecThread = noRecThread;
		}
		
		public void run() {
			
			if(noRecThread.equals("")) {
				System.out.println("Hello from thread " + myID + " in total " + numThreads + " !!!");
				ThreadPrint thread1 = new ThreadPrint(myID,"a");
				ThreadPrint thread2 = new ThreadPrint(myID,"b");
				thread1.start();
				thread2.start();
				try {
					thread1.join();
					thread2.join();
				} catch (InterruptedException e) {
					System.err.println("This should not happen.");
				}
			} else {
				System.out.println("Hello from recursive thread " + noRecThread + ". I was created from thread " + myID + " !!!");
			}
		}
		
	}
	
}