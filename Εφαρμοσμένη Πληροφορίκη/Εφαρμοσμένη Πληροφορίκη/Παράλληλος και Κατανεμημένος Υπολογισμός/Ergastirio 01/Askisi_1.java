// Sta 100.000 nimata i xrisi tis CPU itan kata meso oro sto 75% eno prin tin dimiourgia nimaton itan sto 50%. Me ligotera nimata ta pososta tis CPU itan idia alla o xronos ektelesis tou programmatos 
// meiothike aisthita sta 10.000 kai 1.000 nimata. I xrisi tis CPU tis kartas grafikon auksithike apo to 0% sto 7% sta 100.000 nimata. I mnimi den epeireastike sxedon katholou apo tin ektelesi ton
// programmaton.


public class Askisi_1 {
	
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
			System.out.println("In main: Started thread " + i);
			threads[i] = new ThreadPrint(i); // Ftiaxnoume to nima kai pername san orisma ton arithmo seiras dimiourgias tou.
			threads[i].start(); // Ksekiname to nima.
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
		
		public ThreadPrint(int myID) {
			this.myID = myID;
		}
		
		public void run() {
			System.out.println("Hello from thread " + myID + " in total " + numThreads + " !!!"); // Tuponoume stin kosnola ton arithmo tou nimatos kai ton arithmo ton sunolikon nimaton.
		}
		
	}
	
}