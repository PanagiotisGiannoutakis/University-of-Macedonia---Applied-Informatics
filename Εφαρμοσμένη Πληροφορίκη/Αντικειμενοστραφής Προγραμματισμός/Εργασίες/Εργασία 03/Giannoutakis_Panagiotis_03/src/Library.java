public class Library {

	private Book[] books;
	private int bookCounter;
	private double sunolo;

	private final int MAX_SIZE = 30;

	public Library() {
		books = new Book[MAX_SIZE];
		bookCounter = 0;
	}

	public void addBook(Book book) {
		if (bookCounter < MAX_SIZE) {
			books[bookCounter] = book;
			bookCounter++;
		} else {
			System.out.println("Book list is Full");
		}
	}

	public Book checkIfBookExists(int isbn){
		for(int i=0;i<bookCounter;i++){
			if(books[i].getIsbn()==isbn){
				return books[i];
			}			
		}
		return null;
	}

	public void borrowBook(int kodikos) {
		for (int i = 0; i < bookCounter; i++) {
			if (books[i].getIsbn() == kodikos) {
				if ((checkIfBookExists(kodikos) != null) && (books[i].isBorrowed() == false)) {
					books[i].setBorrowed(true);
					books[i].setTimesRented(books[i].getTimesRented() + 1);
					System.out.println("To vivlio me onoma <<" +books[i].getTitle()+ ">> exei daneistei");
					return;

				}
			}
		}

	}

	public void returnBook(int kodikos) {
		for (int i = 0; i < bookCounter; i++) {
			if (books[i].getIsbn() == kodikos) {
				if (checkIfBookExists(kodikos) != null) {
					books[i].setBorrowed(false);
					System.out.println("To vivlio me onoma <<" + books[i].getTitle() + ">> exei epistrafei");
				}
			}
		}
	}

	public void calculateWriterProfits(int kodikos) {
		sunolo = 0;
		for (int i = 0; i < bookCounter; i++) {
			if (books[i].getMyWriter().getAfm() == kodikos) {
				double pliromi = books[i].getTimesRented() * books[i].getMyWriter().getPoso();
				sunolo = sunolo + pliromi;
			}
		}
		
	}

	public void printBorrowedBooks() {
		for (int i = 0; i < bookCounter; i++) {
			if (books[i].isBorrowed()) {
				System.out.println(books[i].getTitle() + ", (" + books[i].getMyWriter().getName() + ") , ISBN:" + books[i].getIsbn());
			}
		}
	}

	public double getSunolo() {
		return sunolo;
	}

	public void setSunolo(double sunolo) {
		this.sunolo = sunolo;
	}
}
