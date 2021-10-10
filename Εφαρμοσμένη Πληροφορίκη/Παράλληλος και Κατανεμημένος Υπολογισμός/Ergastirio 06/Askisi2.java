// Me amoivaio apokleismo se oli tin domi tou pinaka se 16 nimata exo peripou 0.24 sec xrono ektelesis.

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Askisi2 {
	
	static Lock lock = new ReentrantLock();
	
    public static void main(String args[]) throws IOException {
		
		int numThreads = 0;

        try {
			numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
			System.out.println("argument "+ args[0] +" must be int");
			System.exit(1);
        }
		
        if (args.length != 2) {
			System.out.println("Askisi2 <Number of Threads> <file name>");
            System.exit(1);
        }
        
        String fileString = new String(Files.readAllBytes(Paths.get(args[1])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        int alphabetSize = 256;
        int[] histogram = new int[alphabetSize]; 
        for (int i = 0; i < alphabetSize; i++) { 
            histogram[i] = 0; 
        }
        
		long startTime = System.currentTimeMillis();
		
		Askisi2Thread threads[] = new Askisi2Thread[numThreads];
		
		for(int i = 0; i < numThreads; i++) {
			threads[i] = new Askisi2Thread(i, numThreads, n, alphabetSize, histogram, text);
			threads[i].start();
		}
		
		// wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
		
		long endTime = System.currentTimeMillis();
		
        for (int i = 0; i < alphabetSize; i++) { 
            System.out.println((char)i+": "+histogram[i]);
        }
		
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
   }
   
   static class Askisi2Thread extends Thread {
		
		private int myID, numThreads, n, alphabetSize, myStart, myStop;
		private int[] histogram;
		private char[] text;
		
		public Askisi2Thread(int myID, int numThreads, int n, int alphabetSize, int[] histogram, char[] text) {
			this.myID = myID;
			this.numThreads = numThreads;
			this.n = n;
			this.alphabetSize = alphabetSize;
			this.histogram = histogram;
			this.text = text;
			
			myStart = myID * (n / numThreads);
			myStop = myStart + (n / numThreads);
			if (myID == (numThreads - 1)) 
				myStop = n;
		}
		
		public void run() {
			for (int i = myStart; i < myStop; i++) {
				//for(int j = 0; j < alphabetSize; j++) {
					//System.out.println(histogram[j] == (int)text[i]);
					//if((char)histogram[j] == text[i]) {
						//histogram[j]++;
					//}
				lock.lock();
				try {
					histogram[(int)text[i]] ++;
				} finally {
					lock.unlock() ;
				}
			}
		}
	}
}
