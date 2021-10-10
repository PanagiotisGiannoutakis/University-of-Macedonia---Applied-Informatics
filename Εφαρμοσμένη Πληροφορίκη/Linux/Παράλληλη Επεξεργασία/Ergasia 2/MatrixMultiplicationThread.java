class MatrixMultiplicationThread extends Thread {

	private int id, block, i, j, k, N, sum;
	private int[][] a = new int[N][N];
	private	int[][] b = new int[N][N];
	private	int[][] c = new int[N][N];

	// Constructor
	public MatrixMultiplicationThread(int identifier, int slice, int[][] a, int[][] b, int[][] c, int N) {
		this.id = identifier;
		this.block = slice;
		this.a = a;
		this.b = b;
		this.c = c;
		this.N = N;
	}

	public void run() {
	
		for(i=id * block; i<(id * block) + block; i++) { // Question b - The sequence of for-loops stays as they are
			for(j=0; j<N; j++) {
				sum = 0; // Question a - Use a temporary variable for storage
				for(k=0; k<N; k++) {
					sum += a[i][k] * b[j][k]; // Question c - Scan array b in rows, so its b[j][k]
				}
				c[i][j] = sum;
			}
		}
	}
}
