import java.util.Random;

class SequentialMatrixMultiplication {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]); // Size of problem

		int[][] a = new int[N][N];
		int[][] b = new int[N][N];
		int[][] c = new int[N][N];
		int[][] reverseB = new int[N][N];

		int i, j, k, sum;
		Random rand = new Random();

		for(i=0; i<N; i++) {
			for(j=0; j<N; j++) {
				a[i][j] = rand.nextInt(10);
				b[i][j] = rand.nextInt(10);
				c[i][j] = 0;
			}
		}
		
		// Question c
		for(i=0; i<N; i++) {
			for(j=0; j<N; j++) {
				reverseB[i][j] = b[j][i];
			}
		}

		long start = System.currentTimeMillis();

		for(i=0; i<N; i++) { // Question b
			for(j=0; j<N; j++) {
				sum = 0; // Question a
				for(k=0; k<N; k++) {
					sum += a[i][k] * reverseB[j][k];
				}
				c[i][j] = sum;
			}
		}

		long elapsedTimeMillis = System.currentTimeMillis()-start;

		// Show array results

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
