import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Askisi3 {
	
	static Lock lock = new ReentrantLock();
	
    public static void main(String args[]) throws FileNotFoundException, IOException {
		
		int numThreads = 0;

        try {
			numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
			System.out.println("argument "+ args[0] +" must be int");
			System.exit(1);
        }
		
        if (args.length != 2) {
		System.out.println("Askisi3 <Number of Threads> <file name>");
                System.exit(1);
        }
        
        String fileString = new String(Files.readAllBytes(Paths.get(args[1])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");    
        
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        //HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		long startTime = System.currentTimeMillis();
		
		Askisi3Thread threads[] = new Askisi3Thread[numThreads];
		
		for(int i = 0; i < numThreads; i++) {
			threads[i] = new Askisi3Thread(i, numThreads, words, map);
			threads[i].start();
		}
		
		// wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
		
		long endTime = System.currentTimeMillis();
		

        for (String name: map.keySet()) {
                String key = name.toString();
                String value = map.get(name).toString();
    		System.out.println(key + "\t " + value);
        }
		
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
	
	static class Askisi3Thread extends Thread {
		
		private String[] words;
		private TreeMap<String, Integer> map;
		private int myID, numThreads, myStart, myStop;
		
		public Askisi3Thread(int myID, int numThreads, String[] words, TreeMap<String, Integer> map) {
			this.myID = myID;
			this.numThreads = numThreads;
			this.words = words;
			this.map = map;
			
			myStart = myID * (words.length / numThreads);
			myStop = myStart + (words.length / numThreads);
			if (myID == (numThreads - 1)) 
				myStop = words.length;
		}
		
		public void run() {
			for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
				lock.lock();
				try {
					String key = words[wordCounter].toLowerCase();
					if (key.length() > 0) {
						if (map.get(key) == null) {
							map.put(key, 1);
						} else {
							int value = map.get(key).intValue();
							value++;
							map.put(key, value);
						}
					}
				} finally {
					lock.unlock() ;
				}
			}
		}
	}
}
