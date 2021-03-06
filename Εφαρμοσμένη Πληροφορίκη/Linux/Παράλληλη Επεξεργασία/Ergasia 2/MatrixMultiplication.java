import java.util.Random;

class MatrixMultiplication {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]); // Size of problem
		int numOfThreads = Integer.parseInt(args[1]); // Number of threads

		int[][] a = new int[N][N];
		int[][] b = new int[N][N];
		int[][] c = new int[N][N];
		int[][] reverseB = new int[N][N];

		int i, j, k, sum;
		Random rand = new Random(); 

		MatrixMultiplicationThread[] threads = new MatrixMultiplicationThread[numOfThreads];

		int block = N / numOfThreads;

		for(i=0; i<N; i++) {
			for(j=0; j<N; j++) {
				a[i][j] = rand.nextInt(10); // Generate random integers from 0-10 range
				b[i][j] = rand.nextInt(10);
				c[i][j] = 0;
			}
		}
		
		// Question c - Reverse the array b
		for(i=0; i<N; i++) {
			for(j=0; j<N; j++) {
				reverseB[i][j] = b[j][i];
			}
		}

		long start = System.currentTimeMillis(); // Start time
		
		for(i=0; i<numOfThreads; i++) {
			threads[i] = new MatrixMultiplicationThread(i, block, a, reverseB, c, N);
			threads[i].start();
		}

		for(i=0; i<numOfThreads; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				System.out.println(e); 
			}
		}

		long elapsedTimeMillis = System.currentTimeMillis()-start; // End time

		// Show results

		System.out.println("------------------------------");
		System.out.println("--------- Array A ------------");
		for(i=0; i<N; i++) {
			System.out.print("[");
			for(j=0; j<N; j++) {
				if(j == N-1) {
					System.out.println(a[i][j] + " ]");
				} else {
					System.out.print(a[i][j] + ", ");
				}
			}
		}
		
		System.out.println("------------------------------");
		System.out.println("--------- Array B ------------");
		for(i=0; i<N; i++) {
			System.out.print("[");
			for(j=0; j<N; j++) {
				if(j == N-1) {
					System.out.println(b[i][j] + " ]");
				} else {
					System.out.print(b[i][j] + ", ");
				}
			}
		}

		System.out.println("------------------------------");
		System.out.println("--------- Array C ------------");
		for(i=0; i<N; i++) {
			System.out.print("[");
			for(j=0; j<N; j++) {
				if(j == N-1) {
					System.out.println(c[i][j] + " ]");
				} else {
					System.out.print(c[i][j] + ", ");
				}
			}
		}
		
		System.out.println("Elapsed time in milliseconds: " + elapsedTimeMillis);
	}


}
