import java.util.concurrent.Semaphore;

public class Askisi1Sem {
	
	static Semaphore semWest = new Semaphore(1);
	static Semaphore semEast = new Semaphore(0);
	
    public static void main(String[] args) {
		
        int numThreads = 2;  
		
        if (args.length != 1) {
	    System.out.println("Usage: java Askisi1Sem <number of threads>");
	    System.exit(1);
		}

		try {
			numThreads = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			System.out.println("Integer argument expected");
			System.exit(1);
		}
		
		String[] bridgeCars = new String[numThreads]; 
		
		westBound westThreads[] = new westBound[numThreads];
		eastBound eastThreads[] = new eastBound[numThreads];
		
		int westCounter = 0;
		int eastCounter = 0;
		
		for (int i = 0; i < numThreads; i++) {
			int randomThread = (int) ((Math.random() <= 0.5) ? 1 : 2);
			System.out.println(randomThread);
			if(randomThread == 1) {
				westThreads[westCounter] = new westBound(i, bridgeCars);
				westThreads[westCounter].start();
				westCounter++;
			} else if(randomThread == 2) {
				eastThreads[eastCounter] = new eastBound(i, bridgeCars);
				eastThreads[eastCounter].start();
				eastCounter++;
			}
		}
		
		for(int i = 0; i < westCounter; i++) {
			try {
				westThreads[i].join();
			} catch (InterruptedException e) {
				System.out.println("AAA");
			}
		}
		
		for(int i = 0; i < eastCounter; i++) {
			try {
				eastThreads[i].join();
			} catch (InterruptedException e) {
				System.out.println("BBB");
			}
		}
		
		
		
		for (int i = 0; i < numThreads; i++) {
			System.out.println(bridgeCars[i]);
		}
	}

	static class westBound extends Thread {
		
		private String[] bridgeCars;
		private int myID;
		
		public westBound(int myID, String[] bridgeCars) {
			this.myID = myID;
			this.bridgeCars = bridgeCars;
			//System.out.println("west");
		}
		
		public void run() {
			// before_critical_section
			try{ 
				//System.out.println("Thread-" + myID + " try to take semWest semaphore.");
				semWest.acquire(); 
			} catch (InterruptedException e) {}
			
			// critical_section
			bridgeCars[myID] = "West Car";
			
			//System.out.println("Thread-" + myID + " release semEast semaphore.");
			semEast.release();
			semWest.release();
			// after_critical_section
		}
	}	
	
	static class eastBound extends Thread {
		
		private String[] bridgeCars;
		private int myID;
		
		public eastBound(int myID, String[] bridgeCars) {
			this.myID = myID;
			this.bridgeCars = bridgeCars;

		}
		
		public void run() {
			// before_critical_section
			try{ 
				//System.out.println("Thread-" + myID + " try to take semEast semaphore.");
				semEast.acquire(); 
			} catch (InterruptedException e) {}
			
			// critical_section
			bridgeCars[myID] = "East Car";
			
			//System.out.println("Thread-" + myID + " release semWest semaphore.");
			semWest.release(); 
			semEast.release();
			// after_critical_section
		}
	}
	
}