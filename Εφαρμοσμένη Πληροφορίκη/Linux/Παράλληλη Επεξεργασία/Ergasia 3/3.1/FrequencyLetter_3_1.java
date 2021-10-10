import java.util.*;
import java.io.*;

public class FrequencyLetter_3_1 {

	public static int[] freqs = new int[26];
	public static int numOfThreads;

	public static int[] countLetters(String filename) throws IOException {
		
		int i;
		File f = new File(filename);
		int numberOfLines = countLines(f);
		ThreadSum[] threads = new ThreadSum[numOfThreads];

		int block = numberOfLines / numOfThreads;
		int remainingBlock = numberOfLines % numOfThreads;

		long start = System.currentTimeMillis();

		for(i=0; i<numOfThreads; i++) {
			threads[i] = new ThreadSum(block, i, filename, remainingBlock);
			threads[i].start();
		}

		for(i=0; i<numOfThreads; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		long elapsedTimeMillis = System.currentTimeMillis()-start;

		System.out.println("Elapses Time in milliseconds: " + elapsedTimeMillis);

		return freqs;
	}
 	
	public static int countLines(File aFile) throws IOException {
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(aFile));
			while ((reader.readLine()) != null);
			return reader.getLineNumber();
		} catch (Exception ex) {
			return -1;
		} finally { 
			if(reader != null) {
				reader.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner user_input = new Scanner(System.in);
		System.out.print("Give the name of the .txt file (include .txt): ");
		String filename = user_input.next();
		System.out.print("Give the number of threads: ");
		numOfThreads = Integer.parseInt(user_input.next());
		System.out.println(Arrays.toString(countLetters(filename)));
	}

	private static class ThreadSum extends Thread {
	
		private int block, id, remainingBlock;
		String filename;

		public ThreadSum(int block, int id, String filename, int remainingBlock) {
			this.block = block;
			this.id = id;
			this.filename = filename;
			this.remainingBlock = remainingBlock;
		}
	
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new FileReader(filename));
				String line;
				int currentLine = 0;
				while((line = in.readLine()) != null) {
					currentLine++;
					if(id == numOfThreads-1) {
						if((currentLine > id*block) && (currentLine <= (id*block+block)+remainingBlock)) {
							line = line.toUpperCase();
							for(char ch:line.toCharArray()){
								if(Character.isLetter(ch)){
									synchronized (this) {
										freqs[ch - 'A']++;
									}
								}
							}
						}
					} else {
						if((currentLine > id*block) && (currentLine <= id*block+block)) {
							line = line.toUpperCase();
							for(char ch:line.toCharArray()){
								if(Character.isLetter(ch)){
									synchronized (this) {
										freqs[ch - 'A']++;
									}
								}
							}
						}
					}
				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
