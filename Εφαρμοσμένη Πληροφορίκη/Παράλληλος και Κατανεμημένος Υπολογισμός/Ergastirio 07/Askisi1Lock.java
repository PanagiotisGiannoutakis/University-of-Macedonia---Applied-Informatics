import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Askisi1Lock {
	
	static boolean westBound = true;
	static boolean eastBound = false;
	static Lock lock = new ReentrantLock();
	static Condition cWest = lock.newCondition();
	static Condition cEast = lock.newCondition();
	
    public static void main(String[] args) {
		
        int numThreads = 2;  
		
        if (args.length != 1) {
	    System.out.println("Usage: java Askisi1Lock <number of threads>");
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
			lock.lock();
			while(!westBound) {
				try {
                    cWest.await();
				} catch (InterruptedException e) { }
			}
			// critical_section
			try {
				bridgeCars[myID] = "West Car";
				eastBound = true;
				cEast.signal();
			} finally {
				lock.unlock() ;
			}
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
			lock.lock();
			while(!eastBound) {
				try {
                    cEast.await();
				} catch (InterruptedException e) { }
			}
			// critical_section
			try {
				bridgeCars[myID] = "East Car";
				westBound = true;
				cWest.signal();
			} finally {
				lock.unlock() ;
			}
			// after_critical_section
		}
	}
	
}