import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Askisi1Monitor {
	
		static Lock lock = new ReentrantLock();
	
    public static void main(String[] args) {
		
        int numThreads = 2;  
		
        if (args.length != 1) {
	    System.out.println("Usage: java Askisi1Monitor <number of threads>");
	    System.exit(1);
		}

		try {
			numThreads = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			System.out.println("Integer argument expected");
			System.exit(1);
		}
		
		String[] bridgeCars = new String[numThreads]; 
		
		rendezvous my_rendezvous = new rendezvous(bridgeCars);
		
		westBound westThreads[] = new westBound[numThreads];
		eastBound eastThreads[] = new eastBound[numThreads];
		
		int westCounter = 0;
		int eastCounter = 0;
		
		for (int i = 0; i < numThreads; i++) {
			int randomThread = (int) ((Math.random() <= 0.5) ? 1 : 2);
			System.out.println(randomThread);
			if(randomThread == 1) {
				westThreads[westCounter] = new westBound(i, my_rendezvous, bridgeCars);
				westThreads[westCounter].start();
				westCounter++;
			} else if(randomThread == 2) {
				eastThreads[eastCounter] = new eastBound(i, my_rendezvous, bridgeCars);
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
	
	static class rendezvous {
		
		private Condition cWest, cEast;
		private boolean westBound, eastBound;
		private String[] bridgeCars;
		
		public rendezvous(String[] bridgeCars) {
			cWest = lock.newCondition();
			cEast = lock.newCondition();
			westBound = true;
			eastBound = false;
			this.bridgeCars = bridgeCars;
		}
		
		public synchronized void goWest(int myID) {
			while(!westBound) {
				try {
                    cWest.await();
				} catch (InterruptedException e) { }
			}
			westBound = false;
			
			// critical section
			bridgeCars[myID] = "West Car";
			
			eastBound = true;
			//notify(cEast);
			notify();
		}
		
		public synchronized void goEast(int myID) {
			while(!eastBound) {
				try {
                    cEast.await();
				} catch (InterruptedException e) { }
			}
			eastBound = false;
			
			// critical section
			bridgeCars[myID] = "East Car";
			
			westBound = true;
			//notify(cWest);
			notify();
		}
	}

	static class westBound extends Thread {
		
		private String[] bridgeCars;
		private rendezvous my_rendezvous;
		private int myID;
		
		public westBound(int myID, rendezvous my_rendezvous, String[] bridgeCars) {
			this.bridgeCars = bridgeCars;
			this.my_rendezvous = my_rendezvous;
			this.myID = myID;
		}
		
		public void run() {
			
			while(true) {
				// before_critical_section
				my_rendezvous.goWest(myID);
				// after_critical_section
			}
		}
	}	
	
	static class eastBound extends Thread {
		
		private String[] bridgeCars;
		private rendezvous my_rendezvous;
		private int myID;
		
		public eastBound(int myID, rendezvous my_rendezvous, String[] bridgeCars) {
			this.my_rendezvous = my_rendezvous;
			this.bridgeCars = bridgeCars;
			this.myID = myID;
		}
		
		public void run() {
			while(true) {
				// before_critical_section
				my_rendezvous.goEast(myID);
				// after_critical_section
			}
		}
	}
	
}