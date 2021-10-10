public class Askisi_2 {
	
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
		
		Thread[] threads = new Thread[numThreads];
		
		for(int i=0; i<numThreads; i++) {
			System.out.println("In main: Started thread " + i);
			
			int myVariable = i;

			threads[i] = new Thread()
			{ // se auto to simeio dimiourgoume esoteriki kai anonumi klasi.
				private int id;
				
				public void run()
				{
					System.out.println("Hello from thread " + id + " in total " + numThreads + " !!!");
				}
				
				private Thread init(int var) { // se auto to simeio kanoume arxikopoiisi tou nimatos. Esoteriki methodos.
					id = var;
					return this;
				}
			}.init(myVariable); // se auto to simeio kaloume amesos tin init methodo gia na ginei i arxikopoiisi.
			
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
	
}