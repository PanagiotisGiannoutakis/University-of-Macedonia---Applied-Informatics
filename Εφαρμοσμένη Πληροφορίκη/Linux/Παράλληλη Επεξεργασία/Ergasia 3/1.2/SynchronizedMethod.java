class SynchronizedMethod
{
	public static int sum = 0;
	public static int[] array;
	
	public static void main(String[] args)
	{
		// Δημιουργία και αρχικοποίηση πίνακα
		array = new int[1000];
		for(int i = 0; i < 1000; i++) array[i] = i;
		
		// Δημιουργία πίνακα νημάτων
		ThreadSum threads[] = new ThreadSum[10];
		
		// Δημιουργία και εκκίνηση νημάτων
		for(int i = 0; i < 10; i++)
		{
			threads[i] = new ThreadSum(array,i);
			threads[i].start();
		}
		
		// Αναμονή να τελειώσουν όλα τα νήματα
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {
				System.err.println("this should not happen");
			}
		}
		System.out.println("The sum of of the numbers is " + sum);
	}
	
	private static class ThreadSum extends Thread
	{
		int partial_sum=0;
		int [] array;
		int number;
	
		// Κατασκευαστής
		public ThreadSum(int [] array, int number)
		{
			this.array = array;
			this.number = number;
		}

		// Κάθε νήμα υπολογίζει το τοπικό άθροισμα μιας ομάδας 100 στοιχείων πίνακα
		public void run()
		{
			int size = number * 100;
			for(int i = size; i <= size + 99; i++)
				partial_sum  = partial_sum + array[i];
			System.out.println("Partial sum from thread " + number + " is " + partial_sum);
			Sum summary = new Sum(partial_sum);
			summary.addsum();
		}
	}

	private static class Sum {

		private int partial_sum;

		public Sum(int partial_sum) {
			this.partial_sum = partial_sum;
		}

		public synchronized void addsum() {
			System.out.println(partial_sum);
			sum = sum + partial_sum;
		}
	}
}
