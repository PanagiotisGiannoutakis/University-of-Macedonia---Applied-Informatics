import java.io.*;

public class RaceCondition
{
	public static void main(String[] args)
	{
		int i;

		// Δημιουργία κοινού αντικειμένου - λογαριασμού
		Account accnt = new Account(150);
		System.out.println("Balance of shared account before deposit is " + accnt.getBalance());

		AccountHolder[] accountholders = new AccountHolder[10];

		// Δημιουργία δύο νημάτων ή κατόχων λογαριασμών
   		for(i=0; i<10; i++) {
			accountholders[i] = new AccountHolder(accnt, i);
			accountholders[i].start();
			if((i == 3) || (i == 6) || (i==8)) {
				try {
					accountholders[i].sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

   		for(i=0; i<10; i++) {
			try {
				accountholders[i].join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		System.out.println("Balance of shared account after deposit is " + accnt.getBalance());
	}
}
