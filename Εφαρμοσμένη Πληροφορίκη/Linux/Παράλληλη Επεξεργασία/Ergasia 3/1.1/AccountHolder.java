class AccountHolder extends Thread
{
	private Account acc;
	int i;

	// Κατασκευαστής
	public AccountHolder(Account a, int i)
	{
		acc = a;
		this.i = i;
	}

	// Κάθε νήμα ή (κάτοχος λογαριασμού) να καταθέτει το
	// ποσό 100 Ευρώ στο λογ/σμό
	public void run()
	{
		if((i==2) || (i==5)) {
			acc.withdraw(200);
		} else {
			acc.deposit(100);
		}
	}
}
