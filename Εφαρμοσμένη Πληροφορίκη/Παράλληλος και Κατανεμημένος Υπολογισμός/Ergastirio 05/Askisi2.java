// O kodikas mporei na diavasei kai na tropopoiisei kai idia nimata kathe fora, oxi mono diaforetika. Me mia domi dedomenon tha mporousa na afairo to index tou account pou diavastike
// i tropopoiithike.

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Askisi2
{
	static int numAccounts = 10;
	static int numReadThreads = 1000;
	static int numWriteThreads = 100;	
	
	public static void main(String[] args)
	{
		BankAccountSynchronizedVolatile accounts[] = new BankAccountSynchronizedVolatile[numAccounts];
		
		for(int i=0; i<numAccounts; i++) {
			int balance = (int) (Math.random() * 1000000);
			//System.out.println("balance: " + balance);
			accounts[i] = new BankAccountSynchronizedVolatile(balance);
		}
		
		long startTime = System.currentTimeMillis();
		
		ReadThread rThreads[] = new ReadThread[numReadThreads];
		
		for(int i=0; i<numReadThreads; i++) {
			int accountToRead = (int) (Math.random() * 10);
			//System.out.println("accountToRead: " + accountToRead);
			rThreads[i] = new ReadThread(accounts, accountToRead);
			rThreads[i].start();
		}
		
		WriteThread wThreads[] = new WriteThread[numWriteThreads];
		
		for(int i=0; i<numWriteThreads; i++) {
			int accountToWrite = (int) (Math.random() * 10);
			//System.out.println("accountToWrite: " + accountToWrite);
			wThreads[i] = new WriteThread(accounts, accountToWrite);
			wThreads[i].start();
		}
		
		for(int i=0; i<numReadThreads; i++) {
			try {
				rThreads[i].join();
			} catch (InterruptedException e) {}
		}
		
		for(int i=0; i<numWriteThreads; i++) {
			try {
				wThreads[i].join();
			} catch (InterruptedException e) {}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("All threads done !!!");
		System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
	}
	
	static class ReadThread extends Thread {
		
		BankAccountSynchronizedVolatile accounts[] = new BankAccountSynchronizedVolatile[numAccounts];
		int accountToRead;
		
		public ReadThread(BankAccountSynchronizedVolatile[] accounts, int accountToRead) {
			this.accounts = accounts;
			this.accountToRead = accountToRead;
		}
		
		public void run() {
			try {
				Thread.sleep((int)(Math.random()*4000));
			} catch (InterruptedException e) { }
			System.out.println("Account[" + accountToRead + "]: " + accounts[accountToRead].getBalance());

		}
		
	}
	
	static class WriteThread extends Thread {
		
		BankAccountSynchronizedVolatile accounts[] = new BankAccountSynchronizedVolatile[numAccounts];
		int accountToWrite;
		
		public WriteThread(BankAccountSynchronizedVolatile[] accounts, int accountToWrite) {
			this.accounts = accounts;
			this.accountToWrite = accountToWrite;
		}
		
		public void run() {
			try {
				Thread.sleep((int)(Math.random()*100));
			} catch (InterruptedException e) { }
			int deposit = (int) (Math.random() * 999999);
			int withdraw = (int) (Math.random() * 999999);
			//System.out.println("deposit: " + deposit);
			//System.out.println("withdraw: " + withdraw);
			accounts[accountToWrite].deposit(deposit);
			accounts[accountToWrite].withdraw(withdraw);
			//System.out.println("WriteThread: Account[" + accountToWrite + "]: " + accounts[accountToWrite].getBalance());
		}
		
	}
	
}