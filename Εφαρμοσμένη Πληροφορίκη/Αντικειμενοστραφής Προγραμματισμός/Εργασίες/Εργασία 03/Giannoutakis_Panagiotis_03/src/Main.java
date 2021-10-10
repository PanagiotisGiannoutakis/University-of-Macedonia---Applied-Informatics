
public class Main {

	public static void main(String[] args) {
		
		Writer w1 = new Writer("Simon Singh", 857485, 0.02);
		Writer w2 = new Writer("Doxiadis Apostolos", 635241, 0.04);
		
		Book b1 = new Book(1234, "The code book", w1);
		Book b2 = new Book(4321, "Big Bang", w1);
		Book b3 = new Book(5678, "The Science Book", w1);
		Book b4 = new Book(4152, "Fermat's Enigma", w1);
		Book b5 = new Book(8463, "Logicomix", w2);
		Book b6 = new Book(2254, "Uncle Petros & Goldbach's Conjecture ", w2);
		Book b7 = new Book(3321, "Circles Disturbed", w2);
		
		Library L1 = new Library();
		
		L1.addBook(b1);
		L1.addBook(b2);
		L1.addBook(b3);
		L1.addBook(b4);
		L1.addBook(b5);
		L1.addBook(b6);
		L1.addBook(b7);
		
		L1.borrowBook(1234);
		L1.borrowBook(2254);
		L1.borrowBook(3321);
		L1.returnBook(1234);
		L1.borrowBook(5678);
		L1.returnBook(3321);
		L1.borrowBook(1234);
		L1.borrowBook(8463);
		L1.borrowBook(4152);
		L1.returnBook(5678);
		L1.borrowBook(5678);
		
		L1.printBorrowedBooks();
		
		L1.calculateWriterProfits(857485);
		System.out.println("To poso pou prepei na plirothei ston suggrafea " +w1.getName()+ " einai " +L1.getSunolo());
		double sunolo1 = L1.getSunolo();
		L1.calculateWriterProfits(635241);
		System.out.println("To poso pou prepei na plirothei ston suggrafea " +w2.getName()+ " einai " +L1.getSunolo());
		double sunolo2 = L1.getSunolo();
		
		double Sunolo = sunolo1 + sunolo2;
		System.out.println("To kerdos kai ton duo suggrafeon apo tis enoikiaseis einai " +Sunolo);

	}

}
