public class Account {
	 private double balance;
	
	// Κατασκευαστής
	public Account(double initialDeposit) {
		balance = initialDeposit;
	}

	// Kρίσιμη μέθοδος για την επιστροφή υπολοίπου
	public synchronized double getBalance() {
		return balance;
	}
	
	// Κρίσιμη μέθοδος για την κατάθεση ενός ποσού στο λογ/σμό  
	public synchronized void deposit(double amount) { 
		balance += amount;
	}   
	
	// Κρίσιμη μέθοδος για την ανάληψη ενός ποσού από το λογ/σμό
	public synchronized void withdraw(double amount) { 
		 // Δεν επιτρέπεται αρνητικό υπόλοιπο
		if ( balance >= amount ) { balance -= amount; } 
	}   
}
