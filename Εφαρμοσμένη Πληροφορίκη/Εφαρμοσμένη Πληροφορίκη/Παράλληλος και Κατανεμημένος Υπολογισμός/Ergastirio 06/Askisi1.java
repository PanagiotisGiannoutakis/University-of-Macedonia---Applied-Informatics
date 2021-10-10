import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Askisi1 {
	
	static Lock lock = new ReentrantLock();
	
	public static int matchCount = 0;

    public static void main(String args[]) throws IOException {
    
		int numThreads = 0;

        try {
			numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
			System.out.println("argument "+ args[0] +" must be int");
			System.exit(1);
        }
		
        if (args.length != 3) {
			System.out.println("Askisi1 <Number of threads> <file name> <pattern>");
            System.exit(1);
        }
		
        String fileString = new String(Files.readAllBytes(Paths.get(args[1])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        }
 
        String patternString = new String(args[2]);                                                
        char[] pattern = new char[patternString.length()]; 
        int m = patternString.length(); 
        for (int i = 0; i < m; i++) { 
            pattern[i] = patternString.charAt(i); 
        }
		
        int matchLength = n - m;
        char[] match = new char[matchLength+1]; 
        for (int i = 0; i <= matchLength; i++) { 
            match[i] = '0'; 
        }
		
		long startTime = System.currentTimeMillis();
				
		Askisi1Thread threads[] = new Askisi1Thread[numThreads];
		
		for(int i = 0; i < numThreads; i++) {
			threads[i] = new Askisi1Thread(i,numThreads,n,m,matchLength,pattern,text,match);
			threads[i].start();
		}
		
		// wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
		
		long endTime = System.currentTimeMillis();
		
        System.out.println();
        System.out.println("Total matches " + matchCount);
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
        
   }
   
   static class Askisi1Thread extends Thread {
		
		private int myID, numThreads, n, m, matchLength, myStart, myStop;
		private char[] pattern, text, match;
		
		public Askisi1Thread(int myID, int numThreads, int n, int m, int matchLength, char[] pattern, char[] text, char[] match) {
			this.myID = myID;
			this.numThreads = numThreads;
			this.n = n;
			this.m = m;
			this.matchLength = matchLength;
			this.pattern = pattern;
			this.text = text;
			this.match = match;
			
			myStart = myID * ((n-m) / numThreads);
			myStop = myStart + ((n-m) / numThreads);
			if (myID == (numThreads - 1)) 
				myStop = n-m;
		}
		
		public void run() {
			
			for (int j = myStart; j < myStop; ++j) {
				int i;
				for (i = 0; i < m && pattern[i] == text[i + j]; ++i);
				if (i >= m) {
					match[j] = '1';
					lock.lock();
					try {
						matchCount++;
					} finally {
						lock.unlock() ;
					}
					//inc();
					
				}        
			}


			for (int i = 0; i < matchLength; i++) { 
				if (match[i] == '1') {
					System.out.print(i+" ");
				}
			}
			
			System.out.println("Thread done !!!");
		}
		
		/*public synchronized void inc() {
			matchCount++;
		}*/
		
   }
}



